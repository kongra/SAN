(ns conc.core
  (:import [java.util.concurrent.atomic AtomicLong]))

(defn foo
  [x]
  (.. System out (println "foo ..."))
  (Thread/sleep 20000)
  (.. System out (println "... done."))
  (+ x 2))

(def x1 (delay (foo 5)))

(deftype ResetableDelay [delayAtom body]
  clojure.lang.IDeref (deref [this] @@delayAtom))

(defn makeResetableDelay
  [body]
  (ResetableDelay. (atom (delay (body))) body))

(defn resetDelay!
  [^ResetableDelay rd]
  (reset! (.delayAtom rd) (delay ((.body rd))))
  nil)

(defmacro resetableDelay
  [& body]
  `(makeResetableDelay (fn [] ~@body)))

(def rd1 (resetableDelay (foo 5)))

(macroexpand '(resetableDelay (foo 5)))

;; (resetDelay! rd1)
;; (resetDelay! rd1)
;; (resetDelay! rd1)
;; (deref rd1)

;; (def f1 (future (delay (foo 5))))

;; (def p1 (promise))
;; (def f2
;;   (future
;;     (println "f2 próbuje odczytać...")
;;     (println @p1)
;;     (println "f2 happy.")))

;; (deliver p1 3)

(use 'criterium.core)
(def s1 (AtomicLong. -1))
(defn nextS
  [^AtomicLong s]
  (.incrementAndGet s))
#_(quick-bench (nextS s1))

(def s2 (atom 0))

(reset! s2 1)
;; (quick-bench (reset! s2 1))

#_(defn nextA
  [a]
  (let [value @a]
    (reset! a (inc value))
    value))

#_(defn nextA
  [a]
  (swap! a (fn [value]
             (inc value))))

(defn nextA
  [a]
  (swap! a inc))

(defrecord Multi [s1 s2 current])

(def multi1 (atom (Multi. 0 0 :s1)))

(defn nextMulti
  [{:keys [s1 s2 current] :as m}]
  (let [k (if (= :s1 current) :s2 :s1)]
    (-> m
        (assoc :current k)
        (assoc current (inc (current m))))))

;; (->> {:s1 0 :s2 0 :current :s1}
;;      nextMulti
;;      nextMulti
;;      nextMulti
;;      nextMulti)

(defn nextMulti!
  [a]
  (swap! a nextMulti))

(nextMulti! multi1)
(nextMulti! multi1)
(nextMulti! multi1)
(nextMulti! multi1)

(quick-bench (nextMulti! multi1))
