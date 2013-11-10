;;; -*- Mode: Clojure; coding: utf-8 -*-
;;;
;;; Project LAN
;;; Copyright (c) Konrad Grzanek. All rights reserved.
;;; Created 2009-08-31
;;;

(ns kongra.core
  (:require [clojure.set	   :as CSET]
            [clojure.string	   :as CSTR]
            [clojure.contrib.graph :as CCGRAPH]
	    [clojure.contrib.math  :as CCMATH]
	    [clojure.walk]
	    [clojure.repl]

	    [kongra.log		   :as LOG])

  (:import  [java.util List Date Calendar GregorianCalendar]
	    [java.sql Timestamp]

	    [java.io InputStream OutputStream]

	    [org.joda.time DateTime
             DateTime$Property
             DateTimeZone
             Minutes Hours Period Interval]

	    [org.joda.time.format ISODateTimeFormat
             DateTimeFormatter]
	    [ec.util MersenneTwister]

	    [kongra.core Doclean Pair Memo Synchronized Bytes]))


(defn truth
  "Maps the obj to itself if it is not nil, to true otherwise."
  [obj]
  (or obj true))


(defmacro ignoring-exceptions
  [exceptions & body]
  `(try ~@body
        ~@(map (fn [ex] `(catch ~ex ~'_ nil)) exceptions)))


(defmacro ignoring-all-exceptions
  "Executes the body of expressions ignoring any throwables."
  [& body]
  `(ignoring-exceptions [Throwable] ~@body))


(defmacro try-times
  "Executes the body. If an exception of the given class is thrown, will
  retry. At most n retries are done. If still some exception is thrown
  it is bubbled upwards in the call chain.

  Source: http://stackoverflow.com/questions/1879885/
                 clojure-how-to-to-recur-upon-exception"
  [exception-class n & body]
  `(loop [n# (dec (long ~n))]
     (if-let [result# (try [(do ~@body)]
                           (catch ~exception-class e#
                             (when (zero? n#)
                               (throw e#))))]
       (result# 0)
      
       (recur (dec n#)))))


(defmacro warn-on-reflection
  ([on?] `(ignoring-all-exceptions (set! *warn-on-reflection* ~on?)))

  ([] `(warn-on-reflection true)))


(def DEFAULT-PRINT-LENGTH 500)

(defmacro set-print-length
  [value] `(ignoring-all-exceptions (set! *print-length* ~value)))


(defmacro LAN-header
  "Sets a proper standard environment for LAN project source files."
  []
  `(do (warn-on-reflection)
       (set-print-length 500)))


(LAN-header)
(LOG/on)


(def ^{:doc "The encoding setting for the whole system."}
     ENCODING "UTF-8")


;; DYNAMIC VARIABLES UTILITY

(deftype ^:private DynVar
  [value binder original?]
  
  clojure.lang.IDeref
  (deref [this] (deref value)))


(defmacro dyndef
  "Defines a new dynamic variable."
  ([name value]
     `(do (def ~(vary-meta name assoc :dynamic true :tag DynVar)
            (DynVar. (atom ~value) (promise) true))

          ;; register the binder
          (deliver
           (.binder ~name)

           (fn [value# body#]
             (let [new-dyn# (DynVar. (atom value#) (.binder ~name) false)]
               (binding [~name new-dyn#]
                 (body#)))))))  

  ([name]
     `(dyndef ~name nil)))


(defn dynbinding*
  "Dynamically (on the run-time) executes the body (no-arg) function
  within a binding context. The argument bindings is a collection of
  [dyn value] pairs."
  [bindings body]
  (if-not (seq bindings)
    (body)

    (let [[^DynVar dyn value :as b] (first bindings)
          _ (assert (= 2 (count b)) (str "Illegal binding " b))
          binder @(.binder dyn)]
      
      (binder value #(dynbinding* (rest bindings) body)))))


(defmacro dynbinding
  [bindings & body]
  (assert (vector? bindings) "bindings must be a vector")
  (assert (even? (count bindings))
          "bindings must contain an even number of elements")

  (let [make-clause
        (fn [[dyn value]]
          (vector dyn
                  `(DynVar. (atom ~value) (.binder ~dyn) false)))

        clauses (->> bindings
                     (partition 2)
                     (map make-clause)
                     (apply concat)
                     vec)]

    `(binding ~clauses ~@body)))


(defn dynset!
  "Sets the value of a dynamic variable."
  [^DynVar dyn value]
  (assert (.original? dyn) "Dynamically re-bound dynvar cannot be set.")
  (reset! (.value dyn) value))


(defprotocol WithDynval
  (dynval [dyn] "Returns the value of a dynamic variable."))

(extend-protocol WithDynval
  DynVar
  (dynval [dyn] (deref (.value dyn)))

  java.lang.Object
  (dynval [dyn] dyn)

  nil
  (dynval [_] nil))


(defmacro with-dynval
  "An anaphoric macro that binds the symbol it to the current value of
  a dynamic variable of the given name."
  [name & body]
  `(let [~'it (dynval ~name)]
     ~@body))


;; (dyndef SWT-1 123)
;; (dyndef SWT-2 123)

;; (defn dyntest-01
;;   []
;;   (dynbinding* [[SWT-1 456] [SWT-2 567]]               
;;                #(+ (dynval SWT-1) (dynval SWT-2))))


;; (defn dyntest-02
;;   []
;;   (dynbinding [SWT-1 456
;;                SWT-2 567]
;;     (+ (dynval SWT-1) (dynval SWT-2))))


;; DESTRUCTURING KEYVALS AND MAPS ARGUMENTS

(declare sequentialize pair) 

(defmacro with-keyargs
  "An anaphoric macro that uses the map-destructurable collection
  (either a map or a keyvals sequence) to perform a keyword-based
  destructuring to symbols mentioned in the clauses part and to
  execute the body of expressions within these new bindings.

  clause := symbol | (symbol default-value)"
  
  [coll clauses & body]
  (assert (vector? clauses) "Clauses must be a vector.")
  (assert (seq clauses) "Clauses must not be empty.")

  (let [clauses (map sequentialize clauses)
        symbols (map first clauses)

        destruct {:keys (vec symbols)}
        
        default (->> clauses
                     (filter #(= 2 (count %)))
                     (map (fn [[s v]] `(~s (or ~s ~v))))
                     (reduce concat))]

    `(let [coll# ~coll
           ~destruct (cond (map? coll#) coll#
                           (set? coll#) (seq-to-map (return true) coll#)
                           :else (apply hash-map coll#))
           ~@default]       
       
       ~@body)))


(defn keywordize
  "Transforms the argument to a keyword if possible."
  [obj]
  (if (keyword? obj) obj (keyword (str obj))))


(defmacro validate-legal-keys
  [legal-keys map]
  (assert (seq legal-keys) "The legal-keys must not be empty.")
  (let [legal-keys (->> legal-keys
                        (clojure.core/map keywordize)
                        set)]
    
    `(let [legal-keys# ~legal-keys
           map# ~map]
       (doseq [k# (keys map#)]
         (when-not (legal-keys# k#)
           (terror "The key " k# " in " ~map " is illegal. "
                   "The legal ones are " legal-keys#)))
       
       map#)))


(defn MapEntry-to
  ([f ^java.util.Map$Entry entry]
     (f (.getKey entry) (.getValue entry)))

  ([f]
     (fn [entry]
       (MapEntry-to f entry))))


;; ESSENTIAL PREDICATES

(defn not-nil?
  "If x is not nil, returns it. Nil otherwise. Trivial yet
  convenient predicate for tassert. An alias for identity."
  [x] x)


(defn blank?
  "Convenience wrapper around clojure.contrib.str-utils2/blank?."
  [s] (when (CSTR/blank? s) (truth s)))


(defn not-blank?
  "If s is not blank, it's returned, nil otherwise."
  [s]
  (when-not (blank? s)
    s))


;; FLAG ARGUMENTS

(defmacro flag-value
  [flag]
  (assert (symbol? flag) "The flag must be a symbol.")
  (let [sf (str flag)
        sf (if (.endsWith sf "?")
             (.substring sf 0 (- (.length sf) 1))
             sf)
        _ (assert (not-blank? sf)
                  "The flag symbol must not be empty nor be ?.")

        pred #{(keyword sf) `'~(symbol sf) sf true}]

    `(if (~pred ~flag) true false)))


(defmacro with-flag-value
  [flag & body]
  `(let [~flag (flag-value ~flag)]
     ~@body))


(defmacro if-flag
  ([flag then else]
     `(if (flag-value ~flag) ~then ~else))

  ([flag then]
     `(if (flag-value ~flag) ~then)))


(defmacro if-not-flag
  ([flag then else]
     `(if-not (flag-value ~flag) ~then ~else))

  ([flag then]
     `(if-not (flag-value ~flag) ~then)))


(defmacro when-flag
  [flag & body]
  `(when (flag-value ~flag) ~@body))


(defmacro when-not-flag
  [flag & body]
  `(when-not (flag-value ~flag) ~@body))


;; LABELS

(defn label
  "Returns an interned symbol for a label described by it's name and
  (optionally) namespace."
  ([name]
     (kongra.core.Labels/intern name))

  ([ns name]
     (symbol ns (clojure.core/name (label name)))))


(defn- eval-label-name
  [name]
  (cond (string? name) name
        (symbol? name) (str name)
        :else (str (eval name))))


(defmacro defl
  "Defines a value for the given label"
  ([name value]
     `(def ~(label (eval-label-name name)) ~value))

  ([attr-map name value]
     `(def ~(with-meta (label (eval-label-name name)) attr-map) ~value)))


(defmacro lv
  ([name]
     (with-meta (label (eval-label-name name))
       (meta &form)))

  ([ns name]
     (with-meta (label (eval-label-name ns)
                       (eval-label-name name))
       (meta &form))))


(defmacro the
  [& args]
  (->> args
       (map eval-label-name)
       (interpose " ")
       (apply str)))


(defmacro The
  [& args]
  (str *ns* "/"
       (->> args
            (map eval-label-name)
            (interpose " ")
            (apply str))))


(defmacro a
  [& args]
  `(the ~@args))


(defmacro A
  [& args]
  `(The ~@args))


(defmacro an
  [& args]
  `(the ~@args))


(defmacro An
  [& args]
  `(The ~@args))


(defmacro l
  [& args]
  `(the ~@args))


(defmacro L
  [& args]
  `(The ~@args))


;; TO-STRING PROTOCOL

(defprotocol With-to-string
  "A protocol that defines a Clojure to-string mechanism for objects
  in an analogy to the toString method."

  (^String to-string [obj] "Returns a String representation of the obj."))


(defn ^String tstr
  "Works like the clojure.core/str, but uses to-string for the
  serialization of a single object into a String. By default
  transforms lazy sequences into lists for the purpose of transforming
  to string."
  ([] (str))

  ([x] (to-string x))

  ([x & ys]
     (let [builder (new StringBuilder ^String (tstr x))]
       (loop [sb builder
              more ys]
         (if more
           (recur (. sb  (append (tstr (first more)))) (next more))
           (str sb))))))


(defn- safe-str
  [coll]
  (let [plen (if *print-length* *print-length* DEFAULT-PRINT-LENGTH)
	dots? (seq (drop plen coll))
	coll (take plen coll)
	coll (if dots? (concat coll [(symbol "...")]) coll)]

    (tstr (apply list coll))))


(extend-protocol With-to-string
  Object
  (to-string [s] (str s))

  nil
  (to-string [_] "nil")

  String
  (to-string [s] s)

  clojure.lang.LazySeq
  (to-string [coll] (safe-str coll))

  clojure.lang.Cons
  (to-string [coll] (safe-str coll))

  clojure.lang.PersistentList$EmptyList
  (to-string [coll] "()")

  kongra.core.Pair
  (to-string [p]
    (tstr "(" (tstr (.first p)) " . "(tstr (.second p)) ")")))


;; CONFIGURE REPL PRINTING TO USE tstr

(defn register-print-dup
  "Accepts the function f that is a transformer of objects into
  strings.  Causes every object of type to be print-dupped with
  (f obj)."
  [type f]
  (defmethod clojure.core/print-dup type
    [x writer]
    (when (and writer (instance? java.io.Writer writer))
      (. ^java.io.Writer writer (write ^String (f x)))))

  (defmethod clojure.core/print-method type
    [x writer]
    (when (and writer (instance? java.io.Writer writer))
      (. ^java.io.Writer writer (write ^String (f x))))))


(register-print-dup java.lang.Object tstr)


;; AS/ORIGINAL PROTOCOL

(defprotocol Asable
  (as [type] [type obj]
    "Returns the obj wrapped with type instance. When called with type
    only returns an unary procedure that wraps it's argument with a
    type instance."))


(defprotocol Originable
  (original [obj] "An operator complementary to as."))


(extend-protocol Originable
  nil
  (original [_] nil)

  java.lang.Object
  (original [obj] obj))


;; GARBAGE COLLECTION AND HEAP MONITORING

(defn room
  "Prints the current JVM memory status (in MB)."
  []
  (let [free-memory  (.. Runtime getRuntime freeMemory)
	total-memory (.. Runtime getRuntime totalMemory)
	max-memory   (.. Runtime getRuntime maxMemory)
	used-memory  (- total-memory free-memory)
        
	scale (fn [arg] (double (/ arg (* 1024 1024))))]
    
    (printf "Used memory  : %f [MB]\n" (scale used-memory))
    (printf "Free memory  : %f [MB]\n" (scale free-memory))
    (printf "Total memory : %f [MB]\n" (scale total-memory))
    (printf "Max memory   : %f [MB]\n" (scale max-memory))
    nil))


(defn gc
  "Runs the JVM garbage collector. When not silent, runs room."
  ([] (gc :verbose))
  
  ([silent?]
     (System/gc)
     
     (when-not-flag silent?
       (room))))


(defmacro TODO
  [& description]
  (let [description (interpose " " (map str description))]
    `(throw
     (kongra.NotImplementedException.
      (str "The functionality was not implemented yet. TODO: "
           ~@description)))))


;; EXCEPTIONS/STACK-TRACES

(defn ^String stack-trace-string
  [^Throwable t]
  (kongra.utils.StringUtils/getStackTraceString t))


(defn stack-trace
  [^Throwable t] (seq (.getStackTrace t)))


(defmacro current-stack-trace
  "Returns a sequence of StackTraceElements representing the
  current stack trace."
  []
  `(seq (. (new Exception) getStackTrace)))


(defmacro current-stack-frame
  "Returns nth (0th by default) StackTraceElement of the current
  stack trace. "
  ([n] `(nth (current-stack-trace) ~n))

  ([] `(current-stack-frame 0)))


(declare with-termcolor)

(defn pst
  "Prints the throwable stack trace using the passed printing
  function. When printing the dependencies (causes) does not
  filter out the repeating stack trace elements, like the
  t.printStackTrace() does.

  By default System.err.println-s the current *e value."

  ([printfn ^Throwable t]
     (printfn (with-termcolor 'REDB (str t)))
     (doseq [^String st (map str (stack-trace t))]
       (let [color (if (.contains st "kongra.") 'REDB ' RESET)]
	 (printfn (str "\t" (with-termcolor color
			      (str "at " st))))))

     (when-let [cause (.getCause t)]
       (printfn "Caused by: ")
       (recur printfn cause)))

  ([printfn] (when *e (pst printfn *e)))

  ([] (pst #(.. System err (println %)))))


;; PAIRS

(defn ^kongra.core.Pair pair
  "Returns a pair of elements."
  [x y] (Pair/of x y))


(defn pair?
  [x]
  (when (instance? Pair x)
    x))


(defn pair-first
  [^Pair p] (.first p))


(defn pair-second
  [^Pair p] (.second p))


;; CLEANUP CONTEXT

(dyndef *doclean* nil)

(defn ^Doclean doclean-create
  [] (Doclean.))


(defn doclean-close!
  [^Doclean d]
  (.close d))


(defmacro with-doclean
  [d & body]
  `(binding [*doclean* ~d] ~@body))


(defn ^Doclean doclean-assert
  []
  (with-dynval *doclean*
    (assert it "No doclean context.")
    it))


(defn doclean-register!
  "Registeres a function as a closeable in the current cleanup
  context."
  ([^Doclean doclean f]
     (.register doclean (reify java.io.Closeable
                          (close [this] (f)))))

  ([f]
     (doclean-register! (doclean-assert) f)))


(defmacro docleaned
  "Asserts a doclean, evaluates the expression and registeres it's
  value within the doclean. Finally returns the value. The cleanup is
  simply calling .close on the value."
  [expr]
  `(let [doclean# (doclean-assert)
         value#   ~expr]
     
     (doclean-register! doclean# (fn [] (.close value#)))
     value#))


(defmacro docleaned-with
  "An anaphoric macro. Asserts a doclean, evaluates the expression and
  binds it's value to a symbol it. Registeres it in doclean and
  returns it. The first argument is a cleaning expression - one is
  allowed to make references to it within the expression."
  [cleanup-expr expr]
  (let [it 'it]
    `(let [doclean# (doclean-assert)
           ~it       ~expr]
     
       (doclean-register! doclean# (fn [] ~cleanup-expr))
       ~it)))


(defmacro doclean
  "Executes the body of expressions in a new Doclean cleanup
  context.

  WARNING. It's highly recommended to wrap all lazy expressions
  in a (doall ...), force Delays etc."
  [& body]
  `(let [doclean# (doclean-create)]
     (with-doclean doclean#
       (with-open [dc# doclean#]
         ~@body))))


;; THREADING

(defmacro in-thread
  "Executes the set of expressions in a newly created thread with
  the default priority."
  [& exprs]
  `(.. (new Thread (fn [] ~@exprs)) (start)))


(defmacro in-thread-with-priority
  "Executes the set of expressions in a newly created thread with
  the specified priority."
  [priority & exprs]
  `(let [thread# (new Thread (fn [] ~@exprs))]
     (.. thread# (setPriority ~priority))
     (.. thread# (start))))


;; THE PROTOCOL OF OBJECTS CONVERTIBLE TO File

(defprotocol FileConvertible
  (^java.io.File to-file [obj]
    "Returns the obj converted to java.io.File."))


(extend-protocol FileConvertible
  java.io.File
  (to-file [f] f)

  java.lang.String
  (to-file [s] (java.io.File. s)))


(defn ^java.io.File ensuring-parent-directory
  "If the file does not exist, creates the required directory
  structure based on the parent pathname. Returns the argument."
  [^java.io.File f]
  (when-not (.exists f)
    (when-let [parent (.getParentFile f)]
      (when-not (.exists parent)
        (.mkdirs parent))))
  f)


(defn ^java.io.File ensuring-file-exists
  "If the file f does not exist creates the file ensuring the required
  directory structure. Returns the argument."
  [^java.io.File f]
  (when-not (.exists f)
    (when-let [parent (.getParentFile f)]
      (when-not (.exists parent)
        (.mkdirs parent)))

    (.createNewFile f))

  f)


;; EXECUTING SYSTEM PROCESSES (COMMANDS)

(defn exec
  "Wrapper around Runtime.exec. Executes the specified command
  and waits for it to finish. Returns the exit code. The envp
  parameter is a sequence of strings 'name=value', or null where
  name is the environment variable name.

  out-f and err-f are unary functions that take a consecutive
  output and error lines."

  ([cmd dir envp out-f err-f]
     (let [cmd (if (sequential? cmd)
		 (into-array String (map tstr cmd))
		 (tstr cmd))

	   dir  (when dir (to-file (tstr dir)))
	   envp (when (seq envp) (into-array String (map tstr envp)))]

       (kongra.core.Tools/exec cmd dir envp out-f err-f)))

  ([cmd dir out-f err-f]
     (exec cmd dir nil out-f err-f))

  ([cmd dir out-f]
     (exec cmd dir nil out-f nil))

  ([cmd out-f]
     (exec cmd nil nil out-f nil))

  ([cmd]
     (exec cmd nil nil nil nil)))


(defn exec-nowait
  "Works like exec, but does not handle output and errors from
  the process, neither does wait for it to end."
  ([cmd dir envp]
     (let [cmd (if (sequential? cmd)
		 (into-array String (map tstr cmd))
		 (tstr cmd))

	   dir  (when dir (to-file (tstr dir)))
	   envp (when (seq envp) (into-array String (map tstr envp)))]

       (kongra.core.Tools/execNowait (tstr cmd) dir envp)))

  ([cmd dir]
     (exec-nowait cmd dir nil))

  ([cmd]
     (exec-nowait cmd nil nil)))


;; REFLECTION/BEANS

(defn methods-of
  "Returns the collection of methods belonging to the class. By
  default returns all methods (declared inside and inherited)."
  ([class]
     (methods-of class false))

  ([class only-declared?]
     (seq (when class
	    (if only-declared?
	      (:declaredMethods (bean class))
	      (:methods (bean class)))))))


(defn method-name
  "Returns the name of the method"
  [^java.lang.reflect.Method method] (.. method (getName)))


(defn- define-builtins
  [symbol-strings]
  (let [symbol-strings
	(concat symbol-strings
		(map (fn [s] (str "clojure.core/" s))
		     symbol-strings))]
    (apply hash-set
	   (map symbol
		symbol-strings))))


(def clojure-specials
     (define-builtins
       '("def" "loop*" "recur" "if" "let*" "letfn*" "do" "quote"
	 "var" "import*" "." "set!" "try" "throw" "catch"
	 "finally" "new" "&" "monitor-enter" "monitor-exit"
	 "implement" "floor")))


(defn clojure-special?
  "Verifies if the symbol is a special Clojure symbol."
  [symbol] (clojure-specials symbol))


(def clojure-builtins
     (CSET/union
      clojure-specials
      (define-builtins
	'("+" "-" "*" "/" "let" "letfn" "do" "cond" "condp" "for"
	  "loop" "recur" "when" "when-not" "when-let"
	  "when-first" "if" "if-let" "if-not" "." ".." "->"
	  "doto" "and" "or" "new" "dosync" "doseq" "dotimes"
	  "dorun" "doall" "load" "import" "unimport" "ns" "in-ns"
	  "refer" "try" "catch" "finally" "throw" "with-open"
	  "with-local-vars" "binding" "gen-class"
	  "gen-and-load-class" "gen-and-save-class" "doclean"
	  "implement" "proxy" "with-meta" "struct" "struct-map"
	  "delay" "locking" "sync" "time" "apply" "remove"
	  "merge" "interleave" "interpose" "distinct" "cons"
	  "concat" "lazy-cat" "cycle" "rest" "frest" "drop"
	  "drop-while" "nthrest" "take" "take-while" "take-nth"
	  "butlast" "reverse" "sort" "sort-by" "split-at"
	  "partition" "split-with" "first" "ffirst" "rfirst"
	  "zipmap" "into" "set" "vec" "to-array-2d" "not-empty"
	  "seq?" "not-every?" "every?" "not-any?"  "map" "mapcat"
	  "vector?" "list?" "hash-map" "reduce" "filter" "vals"
	  "keys" "rseq" "subseq" "rsubseq" "count" "empty?"
	  "fnseq" "lazy-cons" "repeatedly" "iterate" "drop-last"
	  "repeat" "replicate" "range" "into-array" "line-seq"
	  "resultset-seq" "re-seq" "re-find" "tree-seq"
	  "file-seq" "iterator-seq" "enumeration-seq" "declare"
	  "xml-seq" "symbol?" "string?" "vector" "conj" "str"
	  "pos?" "neg?" "zero?" "nil?" "inc" "format" "alter"
	  "commute" "ref-set" "floor" "assoc" "send" "send-off"))))


(defn clojure-builtin?
  [symbol]
  (clojure-builtins symbol))


(defn clojure-builtin?
  [symbol]
  (clojure-builtins symbol))


(defn symbol-value
  "Resolves and returns the value of the symbol or nil if unable
  to bind."
  [symbol namespace]
  (try (.. clojure.lang.Compiler
	   (resolveIn namespace symbol false))

       (catch java.lang.Exception e nil)))


(defn macro?
  "Answers true if the symbol binds to a macro."
  [symbol]
  (let [ns (.. clojure.lang.RT CURRENT_NS (deref))]
    (when-let [obj (symbol-value symbol ns)]
      (when (instance? clojure.lang.Var obj)
	(.. clojure.lang.Compiler (isMacro obj))))))


(defn atomic?
  "Returns true if x is not sequential. (atomic? nil) ==> true.
  An analog to Common Lisp atom function, that returns false only
  for conses."
  [x] (not (sequential? x)))


(defn singleton?
  "If the coll is a 1-element collection, it's returned, nil
  otherwise."
  [coll]
  (when (and (seq coll) (not (next coll)))
    coll))


(defn singleton-or-empty?
  "If the coll is an empty or 1-element collection, it's
  returned, nil otherwise."
  [coll]
  (if-not (seq coll)
    (truth coll)

    (when (not (next coll))
      coll)))


(defn lazy?
  "Answers true if the given object is a lazy collection."
  [obj]
  (when (instance? clojure.lang.LazySeq obj)
    obj))


(defn array?
  [obj]
  (when-let [c (class obj)]
    (when (.isArray c)
      obj)))


(defn Byte?
  "Returns b iff it's a Byte instance."
  [b]
  (when (instance? Byte b)
    b))


(defn Integer?
  "Returns n iff is's an Integer instance"
  [n]
  (when (instance? Integer n)
    n))


(defn ^Integer to-Integer
  "Converts the passed argument to an Integer."
  [n]
  (if (Integer? n)
    n

    (Integer. (int n))))


(defn system-hashcode
  [obj]
  (System/identityHashCode obj))


(defn iseqable?
  "Returns truth of obj iff the obj may be coerced to a sequence
  (clojure.lang.ISeq) by (seq obj)."
  [obj]
  (when (or (instance? clojure.lang.ASeq obj)
            (instance? clojure.lang.LazySeq obj)
            (instance? clojure.lang.Seqable obj)
            (nil? obj)
            (instance? java.lang.Iterable obj)
            (.isArray (class obj))
            (instance? java.lang.CharSequence obj)
            (instance? java.util.Map obj))
    
    (truth obj)))


(defn ideref?
  [obj]
  (instance? clojure.lang.IDeref obj))


(defn ensure-deref
  [obj]
  (if (ideref? obj) (deref obj) obj))


(defn sequentialize
  "If the object is a sequence, return seq on it, (obj)
  otherwise. Fast wrapper of objects into a sequence. For nil
  returns an empty sequence."
  [obj]
  (cond (nil? obj) '()
	(iseqable? obj) (seq obj)
	:else (list obj)))


(defn with-atoms-to-symbols
  "Converts all atomic elements of the object (possibly a
  collection) to symbols."
  [obj]
  (clojure.walk/postwalk
   (fn [x] (if (atomic? x)
	     (if-not (symbol? x) (symbol (tstr x)) x)
	     x))
   obj))


(defn with-ns-name
  "Assumes a named object. Returns a symbol consisting of (name
  named) / and the name of the namespace. If no namespace present
  in the named, the name of the current namespace is taken."
  [^clojure.lang.Named named]
  (if (namespace named)
    named

    (let [cnstr (if (symbol? named) symbol keyword)]
      (cnstr (str *ns* "/" (name named))))))


(defn intern-ns
  "Interns all interned elements of interned-ns into ns (*ns* by
  default).  The strategy describes the way of refering to
  interned-ns elements, e.g.  ns-interns, ns-publics (default)."
  ([ns interned-ns strategy]
     (let [assert-ns
	   (fn [nsname]
	     (if-let [n (find-ns (symbol (tstr nsname)))]
	       n
	       (throw
		(new IllegalArgumentException
		     (tstr "Unrecognized namespace "
                           nsname ".")))))

	   ns (assert-ns (tstr ns))
	   interned-ns (assert-ns (tstr interned-ns))]

       (doseq [[sym v] (strategy interned-ns)]
	 (intern ns (with-meta sym (meta v)) (deref v)))))

  ([interned-ns] (intern-ns *ns* interned-ns ns-publics)))


(declare not-nil?)

(defn var-root
  "Returns a raw root of the clojure.lang.Var v, nil if none present."
  [^clojure.lang.Var v]
  (when (and v (.hasRoot v))
    (.getRawRoot v)))


(defn ns-entries
  "Returns a sequence of entries [symbol var] for the given obj in the
  namespace ns. The entries-accessor is the source of mappings
  (clojure.core/ns-interns by default)."
  ([ns obj entries-accessor]
     (->> (entries-accessor ns)
          (map (fn [[s v :as p]]
                 (when (= obj (if (var? v) (var-root v) v))
                   p)))

          (filter not-nil?)))

  ([ns obj] (ns-entries ns obj ns-interns))

  ([obj] (ns-entries *ns* obj)))


(defn ns-entry
  "Returns an entry [symbol var] for the given obj in the namespace
  ns. Nil if none found. The entries-accessor is the source of
  mappings (clojure.core/ns-interns by default)."
  {:arglists '([ns obj entries-accessor]
                 [ns obj]
                   [obj])}
  [& args]
  (let [ents (apply ns-entries args)]
    (assert (singleton-or-empty? ents)
            (str "The obj has multiple ns-entries. Unable to select one."))
    
    (first ents)))


;; ASSERTIONS

(defmacro tassert
  "When called with a single parameter pred returns a function
  that takes an argument and asserts the result of calling pred
  on this argument, then returns the argument.

  When called with additional argument, simply calls the
  assertion on pred on the argument."
  ([pred]
     (when *assert*
       `(fn [x#]
	  (when-not (~pred x#)
	    (throw (AssertionError.
                    (tstr "Assert failed: "
                          '~pred " on " x#))))
	  x#)))

  ([pred x]
     (when *assert*
       `(let [x# ~x]
	  (when-not (~pred x#)
	    (throw (new AssertionError
			(tstr "Assert failed: "
			      '~pred " on " x#))))
	  x#)))

  ([pred x msg]
     (when *assert*
       `(let [x# ~x]
	  (when-not (~pred x#)
	    (throw (new AssertionError
			(tstr ~msg))))
	  x#))))


(defn tpred
  "Takes a predicate function and returns a tassert-capable
  predicate, i.e. a predicate that takes an argument and returns
  this argument when pred on this argument is true."
  [pred]
  (fn [arg] (when (pred arg) (truth arg))))


(defn declare-tpred
  "Declares the named function to be a tpred one. "
  [f]
  (let [[_ v] (ns-entry f)
	m (meta v)
	name (with-meta (:name m) m)]

    (intern *ns* name (tpred (var-root v)))))


;; ERROR MANAGEMENT

(def ValueError kongra.ValueError)
(def TypeError  kongra.TypeError)
(def StateError kongra.StateError)

(defmacro terror
  "Causes the system to throw an exception with the given args. If the
  first argument is a Throwable derivative class, uses the specific
  class as the exception type."
  [& args]
  (let [[eclass & rest] args]
    (if-not (symbol? eclass)
      `(do (throw (RuntimeException. (tstr ~@args))) nil)

      (let [eveclass (eval eclass)]
        (if-not (isa? eveclass Throwable)
          `(do (throw (RuntimeException. (tstr ~@args))) nil)

          (let [econstr (symbol (str (.getName ^Class eveclass) "."))]
            `(do (throw (~econstr ~@rest)) nil)))))))


(def ^:dynamic *trecoveries* {})

(let [valid-trecovery-spec?
      (tpred #(and (vector? %)
		   (seq %)
		   (<= (count %) 2)))

      valid-bindings-shape?
      (tpred #(and (vector? %) (even? (count %))))

      process-spec
      (fn [spec]
	(let [[where location] (tassert valid-trecovery-spec?
					spec)]
	  (if location
	    `(pair ~where '~location)

	    where)))

      process-bindings
      (fn [bindings]
	(->> (partition 2 bindings)
	     (map (fn [[spec handler]]
		    (list (process-spec spec) handler)))
	     (reduce concat)))]

  (defmacro terror-recovering
    "Executes the body of expressions within the set of bindings
    for terror recoveries. Sample usage:

    (terror-recovering [[where location] handler] body)
    (terror-recovering [[where] handler] body).

    where is a name of a valid procedure in which we want to use
    the provided error recovery handler. Location is an
    additional symbolic (e.g. keyword) tag within where. handler
    is a name of the handler procedure.

    Empty bindings means inhibiting all previously defined
    recovery handlers."

    [bindings & body]
    (tassert valid-bindings-shape? bindings)
    (let [assoc-args (process-bindings bindings)]
      (if (seq assoc-args)
	`(binding [*trecoveries* (assoc *trecoveries*
				   ~@assoc-args)]
	   ~@body)

	`(binding [*trecoveries* {}]
	   ~@body))))


  (defmacro terror-at
    "Works like terror but provides location specification and
    parameters for potential terror recovery."
    {:arglists '([[where location] params & args]
		   [[where] params & args])}

    [spec params & args]
    (let [spec (process-spec spec)]
      (if (sequential? spec)
	`(let [spec# ~spec]
	   (if-let [handler# (*trecoveries* spec#)]
	     (handler# ~@params)

	     (let [where# (pair-first spec#)]
	       (if-let [handler# (*trecoveries* where#)]
		 (handler# ~@params)

		 (terror ~@args)))))

	`(let [spec# ~spec]
	   (if-let [handler# (*trecoveries* spec#)]
	     (handler# ~@params)

	     (terror ~@args)))))))


;; TURNABLE SWITCHES

(defmacro defswitch
  ([name on?]
     `(dyndef ~name (boolean ~on?)))

  ([name]
     `(defswitch ~name true)))


(defn switch-on!
  [switch]
  (dynset! switch true))


(defn switch-off!
  [switch]
  (dynset! switch false))


(defn on?
  [switch]
  (dynval switch))


(defn off?
  [switch]
  (not (on? switch)))


(defmacro switching-on
  [switches & body]
  (assert (vector? switches) "The switches must be a vector form.")
  (assert (seq switches)) "The switches must not be empty."
  (let [clause (fn [switch] `(~switch true))
        clauses (->> (map clause switches)
                     (apply concat)
                     vec)]
    
    `(dynbinding ~clauses ~@body)))


(defmacro switching-off
  [switches & body]
  (assert (vector? switches) "The switches must be a vector form.")
  (assert (seq switches)) "The switches must not be empty."
  (let [clause (fn [switch] `(~switch false))
        clauses (->> (map clause switches)
                     (apply concat)
                     vec)]
    
    `(dynbinding ~clauses ~@body)))


;; REPL/DEBUGGING UTILITIES

(defn expression-info
  "Uses the Clojure compiler to analyze the given s-expr.  Returns
  a map with keys :class and :primitive? indicating what the compiler
  concluded about the return value of the expression.  Returns nil if
  not type info can be determined at compile-time.
  
  Example: (expression-info '(+ (int 5) (float 10)))
  Returns: {:class float, :primitive? true}

  TAKEN LITERALLY FROM THE ORIGINAL clojure.contrib.repl-utils"
  [expr]
  (let [^clojure.lang.Compiler$FnExpr
        fn-ast (clojure.lang.Compiler/analyze
                clojure.lang.Compiler$C/EXPRESSION `(fn [] ~expr))

        ^clojure.lang.Compiler$BodyExpr
        expr-ast (.body ^clojure.lang.Compiler$FnMethod
                        (first (.methods fn-ast)))]
    
    (if (.hasJavaClass expr-ast)
      {:class (.getJavaClass expr-ast)
       :primitive? (.isPrimitive (.getJavaClass expr-ast))}

      {:class nil :primitive? nil})))


(defmacro expinf
  "Convenience wrapper around
  clojure.contrib.repl-utils/expression-info"
  [expr]
  `(expression-info '~expr))


(defmacro swank-break
  "Breaks the execution and goes down to the SWANK diagnostic REPL."
  []
  `(swank.core/break))


;; DISPATCH ROUTINES

(defn first-type-dispatch
  [x & more]
  (class x))


(defn multiarg-class-dispatch
  "An effective multi-argument dispatcher function."
  ([] :noarg)

  ([x] (class x))

  ([x y] (vector (class x) (class y)))

  ([x y & args] (vec (map class (cons x (cons y args))))))


(defn keyword-comparator
  "Returns the comparator based on the predicate applied to
  keyword values of it's arguments."
  [k pred]
  (comparator (fn [x y] (pred (k x) (k y)))))


;; AN EXTENSIBLE seq

(defprotocol TSeqable
  "An extensible version of clojure.core/seq. The procedure tseq may
  be called with and additional argument (e.g. a comparator comp when
  tseqing maps)."

  (tseq [this] [this arg]))


(defn- map-to-seq
  "Transforms a map to a sequence sorting keys with the comp
  (clojure.core/compare by default)."
  ([comp m]
     (->> (keys m)
          (sort comp)
          (map #(vector % (m %)))))

  ([m] (map-to-seq clojure.core/compare m)))


(extend-protocol TSeqable
  java.util.Iterator
  (tseq [it] (clojure.lang.IteratorSeq/create it))

  java.util.Enumeration
  (tseq [e] (clojure.lang.IteratorSeq/create
             (kongra.utils.CollectionUtils/iterator e)))

  java.lang.Object
  (tseq [obj] (seq obj))

  nil
  (tseq [& _] nil)

  clojure.lang.PersistentTreeMap
  (tseq
    ([m] (seq m)) ;; assume sorted here

    ([m comp] (map-to-seq comp m)))

  java.util.SortedMap
  (tseq
    ([m] (seq m)) ;; assume sorted here

    ([m comp] (map-to-seq comp m)))

  java.util.Map
  (tseq
    ([m] (map-to-seq m))

    ([m comp] (map-to-seq comp m))))


;; MATHS, ALGORITHMS AND UTILS

(defn ^double ln
  "Returns the natural logarithm (base e) of x."
  {:inline (fn [x] `(. Math (log ~x)))
   :inline-arities #{1}}

  [x] (. Math (log x)))


(defn ^double lg
  "Returns the logarithm of x to an arbitrary passed base"
  {:inline (fn [base x] `(/ (ln ~x) (ln ~base)))
   :inline-arities #{2}}

  [base x] (/ (ln x) (ln base)))


(defn ^double lg2
  "Returns the logarithm of x to base 2."
  {:inline (fn [x] `(lg 2 ~x))
   :inline-arities #{1}}

  [x] (lg 2 x))


(defn ^double lg10
  "Returns the logarithm of x to base 10."
  {:inline (fn [x] `(. Math (log10 ~x)))
   :inline-arities #{1}}

  [x] (. Math (log10 x)))


(defn ^double tanh
  "Returns the hyperbolic tangent of x."
  {:inline (fn [x] `(. Math (tanh ~x)))
   :inline-arities #{1}}

  [x] (. Math (tanh x)))


(defn positive?
  ([x] (when (> x 0) x))

  ([x & ys] (and (positive? x) (every? positive? ys))))


(defn negative?
  ([x] (when (< x 0) x))

  ([x & ys] (and (negative? x) (every? negative? ys))))


(defn non-negative?
  ([x] (when (>= x 0) x))

  ([x & ys] (and (non-negative? x) (every? non-negative? ys))))


(defn factors
  "Returns a sequence (1 2 3 ... n) or an empty sequence if n < 1."
  [n]
  (assert (integer? n))
  (range 1 (inc n)))


(defn factorial
  ([multop n]
     (assert (non-negative? n))
     (reduce multop (factors n)))

  ([n] (factorial *' n)))


(defn divisible-by?
  [x n] (zero? (mod x n)))


;; (defn mean
;;   [& args] (/ (apply +' args) (count args)))

(defn square
  ([*op x] (*op x x))

  ([x] (square * x)))

;; (defn heron-sqrt
;;   [x steps]
;;   (letfn [(better [val] (mean val (/ x val)))]

;;     (loop [val (Integer. 1)
;; 	   n steps]
;;       (if (zero? n)
;; 	val
;; 	(recur (better val) (dec n))))))


(defn ^:dynamic **
  "The exponentiation operator. Raises base to pow. It's a wrapper
  around @see clojure.contrib.ccmath/expt."
  [base pow] (CCMATH/expt base pow))


(defn fast-expt
  [a n]
  (assert (>= n 0))
  (loop [a a
	 n (long n)
	 b (Integer. 1)]
    (cond (zero? n) b
	  (even? n) (recur (square a) (long (/ n 2)) b)
	  :else (recur a (dec n) (*' a b)))))


(declare bits)
(defn frankow-expt
  [a n]
  (loop [bits-coll (bits n)
	 a a
	 val (Integer. 1)]
    (cond (empty? bits-coll)
	  val

	  (zero? (first bits-coll))
	  (recur (rest bits-coll) (*' a a) val)

	  :else
	  (recur (rest bits-coll) (*' a a) (*' a val)))))


(defn bits
  "Returns the bits in n, where n is a positive
  integer. Lazy. Starts with the least significant bit."
  [n]
  (assert (non-negative? n))
  (if (zero? n)
    (list 0)

    (map (fn [x] (if (even? x) 0 1))
	 (take-while (complement zero?)
		     (iterate (fn [n] (bit-shift-right n 1)) n)))))


(defn abs
  "Returns an absolute value of x"
  [x]
  (if (< x 0) (- x) x))


(defn ε
  "Returns a binary predicate λ x,y. |x - y| < value"
  [value]
  (fn [x y] (< (abs (- x y)) value)))


(defn fixed-point
  "Fixed point operator for f with x0 := start and the close-enough?
  binary predicate for xn and xn-1."
  [f start close-enough?]
  (loop [xn-1 start
         xn   (f start)]
    (if (close-enough? xn xn-1)
      xn

      (recur xn (f xn)))))


(defn Newtons-method
  "Solves the equation f(x) = 0 using Newton's method with x0 := start
  and using the the close-enough? binary predicate for xn and xn-1."
  [f f' start close-enough?]
  (fixed-point (fn [x] (- x (/ (f x) (f' x)))) start close-enough?))


;; (defn root-3
;;   [x]
;;   (Newtons-method (fn [^double y] (- (* y y y) x)) ;; f
;;                   (fn [^double y] (* 2 y y))       ;; f'
;;                   1                      ;; start
;;                   (ε 0.00000001)           ;;close-enough?
;;                   ))


(defmulti ∀
  "A generalized universal quantification operator"
  {:arglists '([& args])}

  multiarg-class-dispatch)


(defmulti ∃
  "A generalized existential quantification operator"
  {:arglists '([& args])}

  multiarg-class-dispatch)


(defmethod ∃ :noarg
  []
  ;; Returns a predicate that checks for for the "existence" of
  ;; the argument, i.e. it's not nil-ness or non-emptiness
  (fn [arg]
    (if (iseqable? arg)
      (seq arg)

      arg)))


(defmacro return
  "Creates a function that called returns the obj"
  [obj]
  `(kongra.core.Return. (fn [] ~obj)))


(defmacro return-seq
  "Works like (return ...), but requires the arguments to be an
  iseqable? collection. The returned object is iseqable? itself."
  [coll]
  `(kongra.core.ReturnSeq. (fn [] (tassert iseqable? ~coll))))


(defmacro unsafe-return-seq
  [coll]
  `(kongra.core.ReturnSeq. (fn [] ~coll)))


(defmacro returns
  "Works like a list constructor but wraps every item with (return ...)."
  [& items]
  (let [items (map (fn [it] `(return ~it)) items)]
    `(kongra.core.Returns. (list ~@items))))


(extend-protocol TSeqable
  kongra.core.Returns
  (tseq [rs] (map (fn [r] (r)) (.items rs))))


(defmacro delay-seq
  "Works like (delay ...), but requires the arguments to be an
  iseqable? collection. The returned object is iseqable? itself."
  [coll]
  `(kongra.core.DelaySeq. (fn [] (tassert iseqable? ~coll))))


(defmacro unsafe-delay-seq
  [coll]
  `(kongra.core.DelaySeq. (fn [] ~coll)))


(defmacro delays
  "Works like a list constructor but wraps every item with (delay ...)."
  [& items]
  (let [items (map (fn [it] `(delay ~it)) items)]
    `(kongra.core.Delays. (list ~@items))))


(extend-protocol TSeqable
  kongra.core.Delays
  (tseq [ds] (map force (.items ds))))


(defmulti non
  "A multi-purpose multimethod to generate negative predicates.
  When called with no arguments returns a function that returns
  nil for any arguments."
  {:arglists '([]
		 [npred]
		   [npred pred])}

  multiarg-class-dispatch)


(defmethod non [clojure.lang.IFn clojure.lang.IFn]
  [npred pred]
  (fn [obj]
    (when (and (not (npred obj))
	       (pred obj))
      (truth obj))))


(defmethod non clojure.lang.IFn
  [npred]
  (fn [arg]
    (when-not (npred arg)
      (truth arg))))


(defmethod non :noarg
  []
  (fn [& _] nil))


;; INDEXED COLLECTIONS PROTOCOL

(defprotocol Indexable
  (indexed [coll]
    "Returns a sequence of pairs ((index-1 value-1) ... (index-n
   value-n)) for the (not neccessarily) sequential collection."))


(extend-protocol Indexable
  clojure.lang.APersistentMap
  (indexed [m] (seq m))

  kongra.utils.LinkedSeq
  (indexed [ls] (map pair (iterate inc 0) ls))
  
  java.util.List
  (indexed [l] (map pair (iterate inc 0) l))

  java.util.Map
  (indexed [m] (map #(pair % (get m %)) (keys m)))

  java.util.Set
  (indexed [s] (map pair s s))

  java.lang.String
  (indexed [s] (indexed (seq s)))

  nil
  (indexed [_] '()))


(defn bits-to-decimal
  "Returns the decimal value of the collection of bits starting
  with the least significant bit."
  ([coll +op *op]
     (->> (indexed coll)
          (map (fn [[i val]] (*op val (** 2 i))))
          (reduce +)))

  ([coll] (bits-to-decimal coll + *)))


(defn congruent?
  "Answers true if a = b (mod n)."
  [^long a ^long b ^long n]
  (assert (positive? n))
  (zero? (mod (- a b) n)))


(defmacro in-situ
  "Invokes print to print object, then returns the object."
  [print obj]
  `(let [obj# ~obj]
     (do (~print obj#) obj#)))


(defn ref=
  "Reference equality check."
  {:tag Boolean
   :inline (fn [x y] `(. kongra.core.Tools refEqual ~x ~y))
   :inline-arities #{2}}
  ([x] true)
  ([x y] (kongra.core.Tools/refEqual x y))
  ([x y & more]
     (if (ref= x y)
       (if (next more)
	 (recur y (first more) (next more))
	 (ref= y (first more)))

       false)))


(defn seq-member?
  "Tests for a membership of a given value to a specified
  sequential collection. Optionally uses a comparator function
  (defaults to =)."
  ([coll value]
     (seq-member? coll value =))
  ([coll value comp]
     (some #(comp value %) coll)))


(defprotocol WithMembers
  (member? [coll value] [coll value comp]
    "Returns the value iff it belongs to a collection. Optionally uses
    a comparator where possible"))


(extend-protocol WithMembers
  nil
  (member?
    ([coll value] nil)
    ([coll value comp] nil))
  
  java.util.Set
  (member?
    ([s value] (when (.contains s value) (truth value)))
    ([s value comp] (when (seq-member? s value comp) (truth value))))

  kongra.core.ReturnSeq
  (member?
    ([rs value] (member? (rs) value))
    ([rs value comp] (member? (rs) value comp)))

  kongra.core.DelaySeq
  (member?
    ([ds value] (member? (ds) value))
    ([ds value comp] (member? (ds) value comp)))

  
  ;; ALL OTHER (ASSUMED SEQUENTIAL COLLECTIONS)
  java.lang.Object
  (member?
    ([coll value] (when (seq-member? coll value) (truth value)))
    ([coll value comp] (when (seq-member? coll value comp) (truth value)))))


(defn comp+
  "An extended version of composition function. Allows no args to
  be passed to it (returns identity on that occassion). Based on:
  http://blog.fogus.me/2010/08/18/
                           monkeying-with-clojures-comp-function/"
  ([] identity)

  ([f & fs] (apply comp f fs)))


(defn mapconcat
  "Behaves like a Common Lisp mappend function. Concatenates the
   results of mapping f over coll. Generated sequence is lazy."
  [f coll] (apply concat (map f coll)))


(defn mapfn
  "Returns a lazy sequence of functions call values on x."
  [x & fs] (map (fn [f] (f x)) fs))


(defn dissoc-nils
  "Dissociates any keys for which m is nil."
  [m]
  (loop [new-map m
	 ks (keys m)]
    (if-let [k (first ks)]
      (recur (if (m k) new-map (dissoc new-map k)) (rest ks))

      new-map)))


(defn seq-to-map
  "Transforms the sequence coll into a map where the i-th mapping is
  (keyf coll[i]) → (valf coll[i]). The default keyf and valf are
  clojure.core/identity."
  ([keyf valf coll]
     (when (seq coll)
       (->> coll
            (reduce (fn [m obj] (assoc! m (keyf obj) (valf obj)))
                    (transient {}))
            (persistent!))))

  ([valf coll] (seq-to-map clojure.core/identity valf coll))

  ([coll] (seq-to-map clojure.core/identity coll)))


(defn map-to-map
  "Transforms the map m into a new map with keys transformed by keyf
  and values transformed by valf. The default keyf and valf are
  clojure.core/identity."
  ([keyf valf m]
     (->> m
          (reduce-kv (fn [m k v] (assoc! m (keyf k) (valf v)))
                     (transient {}))
          (persistent!)))

  ([valf m] (map-to-map clojure.core/identity valf m))

  ([m] (map-to-map clojure.core/identity m)))


(defn freqdist-inc
  "Takes a frequency distribution and increases it's frequency for the
  given item."
  ([fdist item]
     (assoc fdist item (inc (get fdist item 0))))

  ([fdist item & items]
     (reduce freqdist-inc (freqdist-inc fdist item) items)))


(defn freqdist
  "Takes a sequence of elements and returns a frequency distribution
  for it's elements. The keys nor values in the distribution are not
  sorted."
  [coll]
  (reduce freqdist-inc {} coll))


(defn freqdist-keys
  "Returns the frequency distribution keys, sorted by their related
  frequency."
  [fdist]
  (sort (comparator (fn [k1 k2] (> (fdist k1) (fdist k2))))
        (keys fdist)))


(defn freqdist-entries
  "Returns a sequence of 2-element vectors representing the entries in
  the frequency distribution. The sequence is sorted descending by the
  frequencies."
  [fdist]
  (->> fdist
       freqdist-keys
       (map #(vector % (fdist %)))))


(defn freqdist-total-count
  "Returns the summary count of all entries in the frequency
  distribution."
  [fdist]
  (reduce + (vals fdist)))


(defn reduce-by
  "@See Clojure Programming pp. 119"
  [key-fn f init coll]
  (reduce
   (fn [summaries x]
     (let [k (key-fn x)]
       (assoc summaries k (f (summaries k init) x))))

   {} coll))


(defn reduce-by-in
  "@See Clojure Programming pp. 121"
  [keys-fn f init coll]
  (reduce (fn [summaries x]
            (let [ks (keys-fn x)]
              (assoc-in summaries ks
                        (f (get-in summaries ks init) x))))
          {} coll))


(let [GET*-NOTFOUND (gensym 'GET)]
  (defn get*
    "An iterative version of clojure.core/get.

    Treats the given map as a directed graph with a maximum number of
    outgoing edges equal to 1. Iteratively visits keys (nodes) of the
    map (graph) until either the unary key-pred is false for key
    (node) or a cycle is found. Returns the last node (key)."
    ([map key key-pred not-found]
       (if-not (key-pred key)
         not-found

         (let [last-value (get map key GET*-NOTFOUND)]
           (if (= last-value GET*-NOTFOUND)
             not-found

             (loop [key last-value
                    visited #{}]

               (if-not (key-pred key)
                 key

                 (let [last-value (get map key GET*-NOTFOUND)]
                   (if (or (= last-value GET*-NOTFOUND)
                           (visited last-value))
                     key

                     (recur last-value (conj visited key))))))))))

    ([map key not-found]
       (get* map key (return true) not-found))

    ([map key]
       (get* map key nil))))

;; (def mapa '{A :B, :B :C, :C :D, :D :G, :G :C, :E :F})

;; (defn enumerate
;;   "Enumerates the given sequential coll like Python's
;;   enumerate. Returns lazy ((i val) ... ) collection. The initial
;;   idx may be specified, it's 0 by default."
;;   ([coll]
;;      (enumerate coll 0))

;;   ([coll start-idx]
;;      (map (fn [& args] args) (iterate inc start-idx) coll)))


(defn positions [pred coll]
  "Returns the sequence of position (indices) of the collection
  elements for which pred is true."
  (for [[i v] (indexed coll) :when (pred v)] i))


(defn subsequence
  "Returns a subsequence between 0-based indices start
  (inclusive) and end (exclusive)."
  ([coll start]
     (drop start coll))
  ([coll start end]
     (take (- end start) (subsequence coll start))))


(defn find-idx
  "Returns the index of the first occurence of a value in the
  sequential collection. Uses a specified predicate to match the
  value. Nil means the search failure.

  Optional parameters start and end narrow the search to a subset
  of the sequence. However the returned index is relative to the
  entire sequence (like in CL position function)."
  ([pred coll]
     (let [pred (if (fn? pred) pred #(= pred %))]
       (loop [i 0 coll coll]
         (if (seq coll)
           (if (pred (first coll))
             i

             (recur (inc i) (next coll)))

           nil))))

  ([pred coll start]
     (when-let [idx (find-idx pred (subsequence coll start))]
       (+ idx start)))

  ([pred coll start end]
     (when-let [idx (find-idx pred (subsequence coll start end))]
       (+ idx start))))


(defmacro dorange
  "Assumes bindings to be a vector [i start end]. Executes body with i
   carrying subsequent longs from start (inclusively) to end
   (exclusively)."
  [bindings & body]
  (assert (vector? bindings))
  (assert (= 3 (count bindings)))
  (let [[i start end] bindings]
    `(let [n# (long ~end)]
       (loop [~i (long ~start)]
         (when (< ~i n#)
           ~@body
           (recur (unchecked-inc ~i)))))))


(defn rotate
  "Takes a sequence and left rotates it n steps. If n is
  negative, the sequence is rotated right. Executes in O(n) time.

  The implementation was created by Sean Devlin, @see
  http://vimeo.com/11446902"
  [n coll]
  (if (empty? coll)
    '()
    (let [shift (mod n (count coll))]
      (concat (drop shift coll) (take shift coll)))))


(defn asc
  "Returns a comparator that uses the consecutive key functions (like
  in clojure.core/sort-by) to sort the coll ascending."
  [& keyfns]
  (fn [x y]
    (or (->> keyfns
             (map (fn [f] (compare (f x) (f y))))
             (filter (complement zero?))
             first)
        0)))


(defn desc
  "Returns a comparator that uses the consecutive key functions (like
  in clojure.core/sort-by) to sort the coll descending."
  [& keyfns]
  (fn [x y]
    (or (->> keyfns
             (map (fn [f] (compare (f y) (f x))))
             (filter (complement zero?))
             first)
        0)))


(defn ^java.util.Random mersenne-twister
  "Returns a Mersenne twister random number generator."
  ([] (new MersenneTwister))

  ([seed] (new MersenneTwister (long seed))))


(defn shuffle-with-random
  "Shuffles randomly the passed collection. Optionally uses a
  source of randomness."
  ([coll]
     (shuffle-with-random coll (mersenne-twister)))

  ([^java.util.Collection coll rnd]
     (let [al (java.util.ArrayList. coll)]
       (java.util.Collections/shuffle al rnd)
       (clojure.lang.RT/vector (.toArray al)))))


(defn iterate-with
  "Works like clojure.core/iterate, but uses additional arguments
  from colls when calling f."
  ([f x coll & colls]
     (letfn [(iw [f x coll & colls]
		 (when (and (seq coll) (every? seq colls))
		   (let [x (apply f x (first coll)
				  (map first colls))]
		     (cons x
			   (lazy-seq (apply iw f x (next coll)
					    (map next colls)))))))]

       (cons x (apply iw f x coll colls))))

  ([f x coll]
     (letfn [(iw [f x coll]
		 (when (seq coll)
		   (let [x (f x (first coll))]
		     (cons x
			   (lazy-seq (iw f x (next coll)))))))]

       (cons x (iw f x coll)))))


(defmacro doreduce
  "Iterates with i from 0 to n-1 (inclusively). In every step
  performs expr and sets it's value to the result. The initial
  result value is init."
  [[i n result init] expr]
  `(let [n# (int ~n)]
     (loop [~i (int 0) ~result ~init]
       (if (< ~i n#)
	 (recur (unchecked-inc ~i) ~expr)

	 ~result))))


(defn ^kongra.core.Box box
  "Creates and returns a boxed value."
  [value] (kongra.core.Box/of value))


(defn unbox
  "Unboxes a boxed value."
  [^kongra.core.Box box] (.get box))


(defn reuse-cons
  "An implementation of a canonical reuse-cons Common Lisp
  function."
  ([x y x-y]
     (reuse-cons x y x-y =))
  ([x y x-y equality-test]
     (if (and (equality-test x (first x-y))
	      (equality-test y (rest x-y)))
       x-y
       (cons x y))))


(defn replace-tree
  "Works like clojure.core/replace but treats the second argument
  like a tree rather than a flat collection. An analog to Common
  Lisp sublis."
  [smap tree]
  (map (fn [element]
	 (if (sequential? element)
	   (replace-tree smap element)
	   element))
       (replace smap tree)))


;; TREE SEARCH ROUTINES INSPIRED BY PAIP , CHAPTER 6.4
;; FOR MORE SEE kongra.ai.search

(defn tree-search
  "Searches state-spaces that have the form of trees. Starts with
  a sequence of states and performs the search according to the
  goal? predicate, generator of nodes adjacent do a given node
  and combiner responsible of adding nodes to the search
  collection of nodes."
  [states goal? adjacent combiner]
  (when (seq states)
    (let [obj (first states)]
      ;; (println "states: " states ", obj: " obj)
      (if (goal? obj)
        obj

        (recur (combiner (adjacent obj) (rest states))
               goal?
               adjacent
               combiner)))))


(defn depth-first-combiner
  "The combiner for the depth-first-search."
  [new-nodes states]
  (lazy-cat new-nodes states))


(defn breadth-first-combiner
  "The combiner for the breadth-first-search."
  [new-nodes states]
  (lazy-cat states new-nodes))


(defn breadth-first-tree-levels
  "Returns a lazy collection of lazy sequences of nodes belonging
  to subsequent tree levels."
  [start adjacent]
  (->> (list start)
       (iterate #(reduce concat (map adjacent %)))
       (take-while seq)))


(defn breadth-first-tree-seq
  "Returns a lazy sequence of tree nodes starting with the passed
  start node where adjacent is a function generating nodes
  adjacent to it's argument.

  Goes on infinitely unless the limiting depth specified."
  ([start adjacent]
     (apply concat (breadth-first-tree-levels start adjacent)))

  ([start adjacent depth]
     (assert (> depth 0))
     (->> (breadth-first-tree-levels start adjacent)
          (take depth)
          (reduce concat))))


(defn breadth-first-search
  "Performs a breadth first search. Goes on infinitely unless a
  maximum depth specified."
  ([start goal? adjacent]
     (first (filter goal? (breadth-first-tree-seq start adjacent))))

  ([start goal? adjacent depth]
     (first
      (filter goal?
	      (breadth-first-tree-seq start adjacent depth)))))


(defn depth-first-tree-slices
  "Returns a lazy collection of tree slices when traversing the
  tree in start node with a function adjacent that generates
  nodes adjacent to it's arguments. A slice is an information
  about the nodes to visit on current traversing stage.

  The process is infinite in depth unless the depth is specified
  explicitly."
  ([start adjacent]
     (->> (list start)
          (iterate #(lazy-cat (adjacent (first %)) (rest %)))
          (take-while seq)))

  ([start adjacent depth]
     (assert (> depth 0))

     (let [node       #(pair %1 %2)
	   node-depth #(pair-first %)
	   node-value #(pair-second %)]
       (map #(map node-value %)
	    (take-while seq
			(iterate
			 #(lazy-cat
			   (let [n  (first %) nd (node-depth n)]
			     (when (< nd (dec depth))
			       (map (partial node (inc nd))
				    (adjacent (node-value n)))))

			   (rest %))

			 (list (node 0 start))))))))


(defn depth-first-tree-seq
  "Returns a lazy sequence of the nodes in a tree, via a
  depth-first walk.  Starts with a node called start. The
  function adjacent generates adjacent nodes (children) for a
  passed node."
  ([start adjacent]
     (map first (depth-first-tree-slices start adjacent)))

  ([start adjacent depth]
     (map first (depth-first-tree-slices start adjacent depth))))


(defn depth-first-search
  ([start goal? adjacent]
     (first (filter goal? (depth-first-tree-seq start adjacent))))

  ([start goal? adjacent depth]
     (first (filter goal?
		    (depth-first-tree-seq start adjacent depth)))))


;; VARIOUS UTILS

(defmacro synchronized
  "Executes the body inside the synchronization block set on the
  monitor."
  [monitor & body]
  `(Synchronized/invoke ~monitor (fn [] ~@body)))


(defn files-in
  "Returns a lazy sequence of files in the specified
  location. Goes with breadth-first-tree-seq by default, but
  allows to pass an optional strategy where strategy
  e.g. depth-first-tree-seq."
  ([dir] (files-in dir breadth-first-tree-seq))

  ([dir strategy]
     (strategy (to-file dir)

	       (fn [^java.io.File d]
		 (when (.isDirectory d)
		   (seq (.listFiles d)))))))


(defn zip-entries-in
  "Returns a lazy sequence of ZipEntry objects in the given ZIP
  file."
  [file]
  (let [file (cond (instance? java.util.zip.ZipFile file)
		   file

		   (instance? java.io.File file)
		   (new java.util.zip.ZipFile ^java.io.File file)

		   :else
		   (new java.util.zip.ZipFile
			(new java.io.File (tstr file))))]

    (tseq (.entries ^java.util.zip.ZipFile file))))


(defn file-extension
  "Returns the extension (with dot, e.g. .java) of the passed
  file."
  [f]
  (let [f (tstr f)
	dot-idx (. f (lastIndexOf (int \.)))]
    (when (> dot-idx -1)
      (. f (substring dot-idx)))))


(defn with-extensions
  "Creates a filtering predicate for files having the extension
  out of a given set of exts."
  [ext & exts]
  (let [exts (conj (set exts) ext)]
    (fn [f]
      (let [name (cond (instance? java.io.File f)
		       (.getPath ^java.io.File f)

		       (instance? java.util.zip.ZipEntry f)
		       (.getName ^java.util.zip.ZipEntry f)

		       :else
		       (terror "The argument " f
			       " is not a File nor a ZipEntry."))]

	(member? exts (file-extension name))))))


(defn rmdir
  "Recursively deletes a directory and all of it's contents."
  [^java.io.File dir]
  (when dir
    (if-not (.exists dir)
      true

      (when (.isDirectory dir)
        (doseq [^String l (.list dir)]
          (let [entry (java.io.File. dir l)]
            (if (.isDirectory entry)
              (rmdir entry)

              (.delete entry))))

        (.delete dir)))))


;; STM TOOLS

(defn transaction-running?
  "Answers the question of a presence of a (dosync ...) locking
  transaction."
  []
  (clojure.lang.LockingTransaction/isRunning))


;; RESETTABLE MEMOIZATION

(def NO-MEMO-PRED (return true))

(defn memo
  "A resettable version of clojure.core/memoize. @see memo-reset!
  Takes an optional unary predicate that says whether or not the
  result is to be cached. Allows tracing the memo cache/non-cache
  hits."
  ([f {:keys [pred trace-hits]
       :or   {pred       NO-MEMO-PRED
              trace-hits false}
       :as   options}]
     (validate-legal-keys [pred trace-hits] options)
     (Memo. f pred (boolean trace-hits)))

  ([f]
     (memo f {})))


(defn memo-reset!
  "Resets the cache of the memoized function. @see memo."
  ([^Memo memo clear-hits?]
     (.resetAll memo (flag-value clear-hits?)))

  ([memo]
     (memo-reset! memo :clear-hits)))


(defn memo-reset-key!
  [^Memo memo key]
  (.reset memo key))


(defn memo-size
  "Returns the number items (keys) stored in the cache"
  [^Memo memo]
  (.cacheSize memo))


(defn redef-as-memo
  "Redefines the named function to be a memoized one."
  {:arglists '([f {:keys [pred trace-hits]
                   :or   {pred       NO-MEMO-PRED
                          trace-hits false}
                   :as   options}]
                 
                 [f])}
  ([f options]
     (let [[_ v] (tassert not-nil? (ns-entry f)
                          (tstr "Can't find namespace Var for " f))

           m (meta v)
           name (with-meta (:name m) m)]

       (intern *ns* name (memo (.getRawRoot ^clojure.lang.Var v)
                               options))))

  ([f]
     (redef-as-memo f {})))


(def dynamic-memos (atom {}))

(defn redef-as-dynamic-memo
  "Redefines the named function to be a memoized one.  The function
  MUST be defined as the dynamic one."
  {:arglists '([f {:keys [pred trace-hits]
                   :or   {pred       NO-MEMO-PRED
                          trace-hits false}
                   :as   options}]
                 
                 [f])}
  ([f options]
     (let [[s v] (tassert not-nil? (ns-entry f)
                          (tstr "Can't find namespace Var for " f))

           _ (assert (.isDynamic ^clojure.lang.Var v)
                     (tstr "The function " s " MUST be dynamic."))

           raw (.getRawRoot ^clojure.lang.Var v)]

       (swap! dynamic-memos assoc v (return (memo raw options)))))

  ([f]
     (redef-as-dynamic-memo f {})))


(defmacro memoizing
  "Executes the body within a dynamic memoization context for the
  given functions."
  [fs & body]
  (assert (vector? fs))
  (assert (seq fs))

  (let [clause
        (fn [f]
          `(~f ((tassert not-nil? (@dynamic-memos (ns-resolve *ns* '~f))
                         (tstr "The procedure " '~f
                               " was not redefined as a dynamic memo.")))))

        clauses (apply concat (map clause fs))]

    `(binding [~@clauses] ~@body)))


;; (defn ^:dynamic fib
;;   [n]
;;   (if (< n 2) n (+' (fib (dec n)) (fib (- n 2)))))

;; (redef-as-dynamic-memo fib)


;; (defn local-memo
;;   "Returns a non-resettable, thread-unsafe memo function to be used in
;;   local contexts."
;;   ([f pred]
;;      (let [mem (java.util.HashMap.)]
;;        (fn [& args]
;;          (if (.containsKey mem args)
;;            (.get mem args)

;;            (let [ret (apply f args)]
;;              (when (pred ret)
;;                (.put mem args ret))

;;              ret)))))

;;   ([f] (local-memo f no-memo-pred)))


;; TERMINAL COLORS

(def
 ^{:doc "Returns a terminal color marker for the specified color
 symbol.  Thanks to:
 http://ubuntuforums.org/showthread.php?t=341144"
   :arglists '([color])}

 termcolor-marker '{REDB     "\033[1;41m"
		    REDF     "\033[31m"
		    GREENB   "\033[1;42m"
		    GREENF   "\033[1;32m"
		    YELLOWB  "\033[1;43m"
		    YELLOWF  "\033[1;33m"
		    BLUEB    "\033[1;44m"
		    BLUEF    "\033[1;34m"
		    MAGENTAB "\033[1;45m"
		    MAGENTAF "\033[1;35m"
		    CYANB    "\033[1;46m"
		    CYANF    "\033[1;36m"
		    WHITEB   "\033[1;47m"
		    WHITEF   "\033[1;37m"
		    RESET    "\033[0m"})


(defn with-termcolor
  "Returns the string s wrapped with the terminal color marker
  for the given color."
  [color s]
  (tstr (termcolor-marker color) s (termcolor-marker 'RESET)))


;; TYPE HINTING

(defn with-hint
  "Adds a :tag type hint to the passed obj. If type is a symbol
  quoted multiple number of times, unquotes it
  first. E.g. '''''String and 'String mean the same."
  [type obj]
  (let [t (or (last (flatten type)) type)]
    (when-not (symbol? t)
      (LOG/warn (tstr "Suspiciously hint type " type
		      " is not a symbol but "
		      (class type)
		      " that cannot be unquoted to a symbol.\n"
		      "Called from "(current-stack-frame 1) ".")))

    (vary-meta obj assoc :tag t)))


(def primitive-singular
     '{bytes byte
       chars char
       ints int
       longs long
       booleans boolean
       floats float
       doubles double})


(def primitive-plural
  (seq-to-map primitive-singular identity (keys primitive-singular)))


;; HINTED FAST ARRAY ACCESS

(defmacro deep-aget
  "Works like clojure.core/aget but uses type hints to speed
  thing up.  Thanks to Christophe Grand:
  http://clj-me.cgrand.net/category/interop/"

  ([type array idx]
     `(aget ~(with-hint type array) ~idx))

  ([type array idx & idxs]
     `(let [a# (aget ~(with-hint 'objects array) ~idx)]
	(deep-aget ~type a# ~@idxs))))


(defmacro deep-aset
  "Works like clojure.core/aget but uses type hints to speed
  thing up.  Thanks to Christophe Grand:
  http://clj-me.cgrand.net/category/interop/"
  [type array & idxsv]
  (let [type (last (flatten type))
	[v idx & sxdi] (reverse idxsv)
        idxs (reverse sxdi)
        v (if-let [h (primitive-singular type)] (list h v) v)
        nested-array (if (seq idxs)
                       `(deep-aget ~'objects ~array ~@idxs)
		       array)
        a-sym (with-meta (gensym "a") {:tag type})]
    `(let [~a-sym ~nested-array]
       (aset ~a-sym ~idx ~v))))


;; META PRESERVING ON COLLECTIONS

(defmacro preserving-meta
  "Executes the body and returns the result with meta equal to (meta obj)."
  [obj & body]
  `(with-meta (do ~@body) (meta ~obj)))


(defmacro merging-meta
  "Executes the body and returns the result with meta being a result
  of merging (meta result) and (meta obj)."
  [obj & body]
  `(let [result# (do ~@body)]
     (with-meta result# (merge (meta result#) (meta ~obj)))))


;; DATE-TIME UTILITY FUNCTIONS BASED ON SEAN DEVLIN'S ABSTRACTION
;; GRAFTING http://vimeo.com/8801325 and
;; http://blip.tv/clojure/
;;    sean-devlin-protocol-xiii-clojure-protocols-explained-4540688

(defprotocol Timeable
  (^Long to-ms [t] "Converts the argument to milliseconds."))


(extend-protocol Timeable
  java.lang.Number
  (to-ms [x] x)

  java.util.Date
  (to-ms [d] (.getTime d))

  java.util.Calendar
  (to-ms [c] (.getTimeInMillis c))

  java.sql.Timestamp
  (to-ms [t] (.getTime t))

  nil
  (to-ms [_] nil)

  DateTime
  (to-ms [dt] (.getMillis dt))

  clojure.lang.IPersistentMap
  (to-ms [obj]
    (let [default-map {:year 1970
                       :month 1
                       :day 1
                       :hour 1
                       :minute 0
                       :second 0
                       :ms 0}

          resulting-map (merge default-map obj)
          [y mo d h mi s ms] ((juxt :year :month :day :hour
                                    :minute :second :ms)
                              resulting-map)]
      (to-ms (new DateTime y mo d h mi s ms)))))


(defn ^Date date
  ([]
     (Date.))
  ([time]
     (Date. (to-ms time))))


(defn greg-cal
  ([]
     (new GregorianCalendar))
  ([time]
     (doto (new GregorianCalendar)
       (.setTime (date time)))))


(defn sql-timestamp
  ([]
     (Timestamp. (to-ms (date))))
  ([time]
     (Timestamp. (to-ms time))))


(defn compare-time
  [a b]
  (.compareTo (date a) (date b)))


(defn time-before?
  "Tests to determine if time a is before time b"
  [a b]
  (= (compare-time a b) -1))


(defn time-after?
  "Tests to determine if time a is after time b"
  [a b]
  (= (compare-time a b) 1))


(defn joda
  ([] (DateTime.))

  ([time] (DateTime. (to-ms time))))


;; BENCHMARKING

(defmacro time*
  "Evaluates expr n times and returns a collection of times every
  evaluation took (in ms)."
  [n expr]
  `(doall (map (fn [param#]
		 (let [start# (. System (nanoTime))
		       ret# ~expr]
		   (/ (double (- (. System (nanoTime)) start#))
		      1000000.0)))

               (range ~n))))


(defmacro time-repeat*
  "Executes (time* n (dotimes [_ repeats] expr))."
  [n repeats expr]
  `(time* ~n (dotimes [i# ~repeats] ~expr)))


(defmacro microbench*
  "Evaluates the expression n number of times, returning the
  average time spent in computation, removing highest and lowest
  values.

  If the body of expr returns nil, only the timing is returned
  otherwise the result is printed - does not affect timing.

  Before timings begin, a warmup is performed lasting either 1
  minute or 1 full computational cycle, depending on which comes
  first.

  Thanks to Lau B. Jensen:
  http://www.bestinclass.dk/index.clj/2010/02/
                            benchmarking-jvm-languages.html"
  ([n expr] `(microbench ~n 30 ~expr))

  ([n wormup expr] {:pre [(> n 2)]}
     `(let [warm-up#  (let [start# (System/currentTimeMillis)]
			(println "Warming up for" ~wormup "[s] ...")
			(while (< (System/currentTimeMillis)
				  (+ start# (* ~wormup 1000)))
			       (with-out-str ~expr)
			       (System/gc))
			(println "Benchmarking..."))
	    timings#  (doall
		       (for [pass# (range ~n)]
			 (let [start#    (System/nanoTime)
			       retr#     ~expr
			       timing#   (/ (double
					     (- (System/nanoTime)
						start#))

					    1000000.0)]
			   (when retr# (println retr#))
			   (System/gc)
			   timing#)))
	    runtime#  (reduce + timings#)
	    highest#  (apply max timings#)
	    lowest#   (apply min timings#)]

	(println "Total runtime: " runtime# " msecs")
	(println "Highest time : " highest# " msecs")
	(println "Lowest time  : " lowest# " msecs")
	(println "Average      : " (/ (- runtime#
					 (+ highest# lowest#))
				      (- (count timings#) 2))
		 " msecs")
	timings#)))


(defmacro microbench-repeat*
  ([n repeats wormup expr]
     `(microbench* ~n ~wormup (dotimes [i# ~repeats] ~expr)))

  ([n repeats expr]
     `(microbench* ~n (dotimes [i# ~repeats] ~expr))))


(defn ^kongra.core.Stopwatch stopwatch
  "Creates, starts and returns a new Stopwatch instance."
  []
  (kongra.core.Stopwatch/start))


(extend-protocol Timeable
  kongra.core.Stopwatch
  (to-ms [st] (.elapsedTime st)))


;; SOME USEFUL STRING OPERATIONS

(defmacro wft
  "Returns a macroexpanded-1 form."
  [form]
  `(macroexpand-1 '~form))


(defmacro wft-all
  "Returns a macroexpanded-all form."
  [form]
  `(clojure.walk/macroexpand-all '~form))


(defn ^String indent-string
  "Generates an indentation string of n indent-with elements
  (space character by default)."
  ([n] (indent-string n \space))

  ([n indent-with]
     (let [sb (new StringBuilder)]
       (dotimes [i n] (. sb (append indent-with)))
       (str sb))))


(defn ^String force-minimal-string-length
  [len s]
  (let [len (int len)
	s (str s)
	c (int (count s))
	diff (int (clojure.core/- len c))]

    (if (> diff (int 0)) (str (indent-string diff) s) s)))


(defn ^String unescape-unicode
  "Finds all occurences of a UnicodeEscape sequence \\ u HD HD HD
   HD in the source s and converts them into chars in the
   resulting string. HD here is
   HexDigit := {0 .. 9 | a .. f | A .. F}."

  [s]
  (kongra.utils.StringUtils/unescapeUnicode s))


(defn ^String pluralize
  "Returns a pluralized (inflected) form of the word."
  [word]
  (org.jactiveresource.Inflector/pluralize word))


(defn ^String singularize
  "Returns a singularized form of the word."
  [word]
  (org.jactiveresource.Inflector/singularize word))


(defn ^String dasherize
  "Replaces underscores with dashes in the string s."
  [s]
  (org.jactiveresource.Inflector/dasherize s))


(defn ^String underscorize
  "Replaces dashes with underscores in a string."
  [s]
  (org.jactiveresource.Inflector/underscorize s))


(defn ^String underscore
  "Makes an underscored form from the expression in the
  string. Changes '::' to '/' to convert namespaces to paths."
  [s]
  (org.jactiveresource.Inflector/underscore s))


(defn ^String decamelize
  "Takes a camel-cased string and a separator (space by default) and
  returns a string containing the s components separated with the
  separator."
  ([s separator]
     (let [replacement (str "$1" separator "$2")]
       (-> s
           (CSTR/replace #"([A-Z]+)([A-Z][a-z])" replacement)
           (CSTR/replace #"([a-z\\d])([A-Z])"    replacement))))  

  ([s] (decamelize s " ")))


(defn ^String undasherize
  "Takes a dash-separated string and a separator (space by default)
  and returns a string containing the s components separated with the
  separator."
  ([s separator]
     (CSTR/replace s #"-" separator))  

  ([s] (undasherize s " ")))


(defn ^String deunderscorize
  "Takes an underscore-separated string and a separator (space by
  default) and returns a string containing the s components separated
  with the separator."
  ([s separator]
     (CSTR/replace s #"_" separator))  

  ([s] (deunderscorize s " ")))


;; QUALIFIED NAMES

(def
  ^{:private true :dynamic true}
  *qname-delimiter* (atom (pair #"\." ".")))


(defn set-qname-delimiter
  [re s] (reset! *qname-delimiter* (pair re s)))


(defn ^Pair qname-delimiter
  [] @*qname-delimiter*)


(defmacro with-qname-delimiter
  [re s & body]
  `(binding [*qname-delimiter* (atom (pair ~re ~s))] ~@body))


(defprotocol Qname
  (qname-seq [qname]
    "Returns the sequence of qualified name segments according to the
  current delimiter.")

  (^Pair qname-pair [qname]
    "Returns a pair of a qualified name and the simple name
  representing the argument.")

  (^String qname-str [qname]
    "Returns the string form of the qualified name."))


(extend-type java.util.List
  Qname
  (qname-seq  [coll] coll)

  (qname-pair [coll] (pair (butlast coll) (last coll)))

  (qname-str  [coll]
    (CSTR/join (.second (qname-delimiter))
               (filter not-blank? coll))))


(extend-type Pair
  Qname
  (qname-seq  [p] (concat (qname-seq (.first p)) (list (.second p))))

  (qname-pair [p] p)

  (qname-str  [p]
    (if-let [pfx (.first p)]
      (str (qname-str pfx) (.second (qname-delimiter)) (.second p))

      (.second p))))


(extend-type String
  Qname
  (qname-seq  [s] (CSTR/split s (.first (qname-delimiter))))

  (qname-pair [s] (qname-pair (qname-seq s)))

  (qname-str  [s] s))


;; PERCENTAGE PRINTING

(def ^{:tag java.text.DecimalFormat}
  DEFAULT-PERCENTAGE-FORMAT (java.text.DecimalFormat. "0.00"))


(defn percentages
  "Takes a collection of numbers (series) and returns a corresponding
  collection of percentages. Beware the percentages may not total 100
  due to rounding. The default formatter (DEFAULT-PERCENTAGE-FORMAT)
  does not generate % symbol.

  e.g. (percentages [1 2 3]) → (\"16,67\" \"33,33\" \"50,00\")."
  ([^java.text.NumberFormat formatter coll]
     (let [Σ (reduce + coll)]
       (->> coll
            (map #(.format formatter (* 100 (/ % Σ)))))))

  ([coll] (percentages DEFAULT-PERCENTAGE-FORMAT coll)))


;; MISC. ALGORITHMS

(defn histogram
  "For the collection coll returns a histogram in a form of
  function of value → frequency."
  [coll]
  (loop [h {} coll coll]
    (if-not (seq coll)
      h

      (let [v (first coll)]
	(recur (assoc h v (inc (get h v 0))) (rest coll))))))


(defn powerset
  "Returns a powerset (set of all subsets) of the given set s. The s
  argument may be either a set or a coll, but it must not be
  infinite. The result is lazy."
  [s]
  (let [s (seq s)
        n (count s)
        m (Math/pow 2 n)
	senum (indexed s)
	gen (fn [i]
              (->> senum
                   (filter (fn [p] (bit-test i (pair-first p))))
                   (map pair-second)))]

    (map gen (range m))))


(defn graph-nodes
  "Returns a lazy collection of nodes of a directed graph staring
  with the initial node. Uses adjacent as a function that takes a
  node and returns a collection of all nodes adjacent to it."
  [initnode adjacent]
  (CCGRAPH/lazy-walk (struct-map CCGRAPH/directed-graph
                       :neighbors adjacent)
		     initnode))


(defn breadth-first-Σ*
  "Takes a finite alphabet Σ and returns a lazy infinite Σ*
  collection of strings over the alphabet (including ε) using a
  breadth-first strategy.
  E.g. for Σ = {a, b} returns
  (() (a) (b) (a a) (a b) (b a) (b b) (a a a) (a a b) (a b a)
         (a b b) ...).

  Beware a non-optimistic breadth-first memory consumption."
  [Σ]
  (breadth-first-tree-seq '()
			  (fn [node]
			    (map #(lazy-cat node (list %)) Σ))))


(def SMALL-LETTERS
     '[a b c d e f g h i j k l m n o p q r s t u v w x y z])

(def SMALL-LETTERS-POLISH
  '[a ą b c ć d e ę f g h i j k l ł m n ń o ó p r s t u w x y z])


;; PRIMES AND OTHER NUMBERSTH

(def KNOWN-PRIMES
  "A predefined sequence of first 10000 prime numbers."

  (->> (CSTR/split (slurp "lib/10000-primes.txt") #"\s+")
       (filter not-blank?)
       (map #(Long/parseLong %))))


(def ^:private KNOWN-PRIMES-SET
  (set KNOWN-PRIMES))


(def ^:private LARGEST-KNOWN-PRIME
  (last KNOWN-PRIMES))


(defn known-prime?
  [x]
  (assert (<= x LARGEST-KNOWN-PRIME)
          (str "The value " x " exceeds the maximum known prime. "
               "Unable to tell whether it's a prime or not.") )

  (KNOWN-PRIMES-SET x))


(defn ^:dynamic prime-factors
  "Performs a naive prime factorization of a number."
  [x]
  (if (or (= x 0) (= x 1))
    []

    (let [first-primediv (first (filter #(= 0 (mod x %)) KNOWN-PRIMES))]
      (assert first-primediv
              (str "The factorized number " x " lays beyond the range"
                   " of known primes."))

      (conj (prime-factors (/ x first-primediv)) first-primediv))))

(redef-as-dynamic-memo prime-factors)


(defn number-of-divisors
  [x]
  (->> x
       prime-factors
       freqdist
       vals
       (map inc)
       (reduce *)))


;; (defn triangular-numbers
;;   []
;;   (map #(/ (* % (inc %)) 2) (iterate inc 1)))


;; (defn euler-12
;;   []
;;   (->> (triangular-numbers)
;;        (filter #(> (number-of-divisors %) 500))
;;        first
;;        (memoizing [prime-factors])))


;; UPDATE JAVA BINDINGS

(defmacro update-java-binding
  [class obj]
  `(~(symbol (str class "/setVar")) (second (ns-entry ~obj))))


(update-java-binding kongra.core.tseq tseq)


;; ADD kongra.reflect/bean-tree HERE

(defn bean-tree
  "Displays the tree representation of the given object. Uses the
  following options:
  * max-depth         - see kongra.pprint/tree
  * printer           - see kongra.pprint/tree
  * printers          - see kongra.pprint/tree
  * fold-pred         - when true on obj the obj will not be expanded
  * properties-reader - reads the collection of properties of the given object
  * obj-reader        - (fn [property obj] ...) reads the property value of
                                                the obj
  * property-to-str   - converts the property to a String
  * obj-to-str        - converts the obj to a String
  * obj-type-to-str   - converts the type of the obj to a String."
  {:arglists '([obj] [obj {:keys [max-depth
                                  printer
                                  printers
                                  fold-pred
                                  properties-reader
                                  obj-reader
                                  property-to-str
                                  obj-to-str
                                  obj-type-to-str] :as options}])}
  ([obj options]
     (validate-legal-keys [:max-depth
                           :printer
                           :printers
                           :fold-pred
                           :properties-reader
                           :obj-reader
                           :property-to-str
                           :obj-to-str
                           :obj-type-to-str] options)
     
     (let [f (clojure.lang.RT/var "kongra.reflect" "bean-tree")]
       (f obj options)))

  ([obj] (bean-tree obj nil)))


(defn detailed-bean-tree
  "Works like bean-tree but displays the internalst of some objects
  complex in nature, e.g. collections."
  {:arglists '([obj] [obj {:keys [max-depth
                                  printer
                                  printers
                                  fold-pred
                                  properties-reader
                                  obj-reader
                                  property-to-str
                                  obj-to-str
                                  obj-type-to-str] :as options}])}
  ([obj options]
     (validate-legal-keys [:max-depth
                           :printer
                           :printers
                           :fold-pred
                           :properties-reader
                           :obj-reader
                           :property-to-str
                           :obj-to-str
                           :obj-type-to-str] options)
     
     (let [f (clojure.lang.RT/var "kongra.reflect" "detailed-bean-tree")]
       (f obj options)))

  ([obj] (detailed-bean-tree obj nil)))


;; DELEGATE ALL kongra.bootstrap PUBLIC INTERNALS

(defn f6
  []
  ((clojure.lang.RT/var "kongra.bootstrap" "LAN-sync")))

(defn f7
  []
  ((clojure.lang.RT/var "kongra.bootstrap" "LAN-boot")))


;; SAFE RESOURCE MANAGEMENT

(defrecord ^:private
    Resource [value cleanup ^Thread hook]

    clojure.lang.IDeref
    (deref [this] value))

(prefer-method print-method clojure.lang.IDeref clojure.lang.IRecord)
(prefer-method print-method clojure.lang.IDeref java.util.Map)
(prefer-method print-method clojure.lang.IDeref clojure.lang.IPersistentMap)


(defn Resource?
  [obj]
  (and (map? obj)
       (:value obj) (:cleanup obj) (instance? Thread (:hook obj))))


(defn create-resource
  "Creates a resource and performs all the instrumentation. An
  explicit usage highly discouraged."
  [value cleanup]
  (let [runnable (fn []
                   (try (cleanup value)
                        (catch Throwable e
                          (.printStackTrace e))))
        
        hook (Thread. ^Runnable runnable)]
    (.. Runtime getRuntime (addShutdownHook hook))
    (Resource. value cleanup hook)))


(defn close-resource
  "Closes the resource. An explicit usage highly discouraged."
  [resource]
  (try ((:cleanup resource) (:value resource))
       (.. Runtime getRuntime (removeShutdownHook (:hook resource)))

       (catch Throwable e
         (.printStackTrace e))))


(defmacro defresource
  [name expr cleanup]
  `(let [v# (def ~name)]
     (when (.hasRoot v#)
       ;; ALREADY DEFINED
       (let [r# (.get v#)]
         (when (Resource? r#)
           (close-resource r#))))

     (def ~name (create-resource ~expr ~cleanup))))


(defn ns-unmap-safely
  "Works like clojure.core/ns-unmap but is Resource-aware. Closes a
  Resource if it is a mapping value for sym in ns."
  [ns sym]
  (let [value (var-root (ns-resolve ns sym))]
    (when (Resource? value)
      (close-resource value))

    (ns-unmap ns sym)))


(defn remove-ns-safely
  "Works like clojure.core/remove-ns but is Resource-aware. Closes any
  Resource that is a value in the mapping of the namespace depicted by
  sym."
  [sym]
  (when (find-ns sym)
    (doseq [v (vals (ns-map sym))]
      (when (instance? clojure.lang.Var v)
        (let [value (var-root v)]
          (when (Resource? value)
            (close-resource value)))))

    (remove-ns sym)))


;; SOME PRIMITIVE ARRAYS ABSTRACTIONS

(defn ^Bytes bytes-of
  [^bytes value]
  (Bytes/valueOf value))


(defn ^Bytes bytes-of-size
  [size]
  (Bytes/ofSize (int size)))


(defn ^bytes bytes-value
  [^Bytes bytes]
  (.getBytes bytes))


(defn ^bytes byte-array-of-size
  [size]
  (Bytes/arrayOfSize (int size)))