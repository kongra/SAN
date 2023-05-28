(in-ns 'user)

(require '[clj.telsos.core.macros :refer :all])
(require '[cljc.telsos.core       :refer :all])

(set! *warn-on-reflection* true)

(Class/forName "org.postgresql.Driver")

(defn jee1-connection! ;; ^java.sql.Connection
  []
  (java.sql.DriverManager/getConnection
   "jdbc:postgresql://localhost/JEE1"
   "jee1"
   "jee12345"))

(defn do-with-jee1-connection
  [body]
  (let [conn1 (jee1-connection!)]
    (try
      (body conn1)
      (finally (.close conn1)))))

(do-with-jee1-connection
 #(do (println %)
      :done))

(do-with-jee1-connection
 (fn [conn1]
   (println conn1)
   :done))

(defmacro with-jee1-connection
  [[conn :as head] & body]
  (assert (vector?    head))
  (assert (= 1 (count head)))
  (assert (symbol?    conn))
  `(let [~conn (jee1-connection!)]
     (try
       ~@body
       (finally (.close ~conn)))))

(with-jee1-connection [conn2]
  (println conn2)
  :done)

(defn autocloseable-close!
  [^java.lang.AutoCloseable ac]
  (.close ac))

(defmacro with-open
  [[expr as s :as head] & body]
  (assert (vector?    head))
  (assert (= 3 (count head)))
  (assert (= :as as))
  (assert (symbol? s))
  (assert (some? expr))

  `(let [~s ~expr]
     (try
       ~@body
       (finally (autocloseable-close! ~s)))))

(with-open [(jee1-connection!) :as conn1]
  (println conn1)
  :done)

(defmacro with-open
  [expr & body]
  (assert (some? expr))
  (let [it (symbol "it")]
    `(let [~it ~expr]
       (try
         ~@body
         (finally (autocloseable-close! ~it))))))

(with-open (jee1-connection!)
  (println it)
  :done)

(symbol "it")

;; PORTS AND ADAPTERS (HEXAGONAL ARCHITECTURE)
(defprotocol DeviceDataFinder
  (find-alarms-count-in-last-24-hours [ctx device-id]))

(def ^:dynamic *ctx* nil)
(defn ctx
  []
  (let [ctx- *ctx*]
    (assert ctx- "*ctx* is nil")
    ctx-))

(ctx)

(defmacro with-ctx [ctx- & body]
  `(binding [*ctx* ~ctx-]
     ~@body))

(with-ctx :myctx
  (ctx))


(defrecord TransientCtx [])

(extend-protocol DeviceDataFinder
  TransientCtx
  (find-alarms-count-in-last-24-hours [_ device-id]
    (println "Searching in-memory for the alarms for device id" device-id)
    15))

#_(defn device-status
    [device find-alarms-count-in-last-24-hours]
    (let [alarms-count (find-alarms-count-in-last-24-hours (:id device))]
      (cond (<     alarms-count  10) :LOW
            (<= 10 alarms-count 100) :MEDIUM
            :else                    :HIGH)))

;; Open-Closed Principle

;; def device-status(device):
;;     return ...

;; def device-status(device: Device) -> Status:
;;     return ...

(defn device-status
  [device]
  (let [alarms-count (find-alarms-count-in-last-24-hours (ctx) (:id device))]
    (cond (<     alarms-count  10) :LOW
          (<= 10 alarms-count 100) :MEDIUM
          :else                    :HIGH)))

(with-ctx (->TransientCtx)
  (device-status {:id 145}))
