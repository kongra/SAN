(set! *warn-on-reflection* true)

(defrecord CloseableSemaphore [semaphore permits]
  java.lang.AutoCloseable
  (close [_]
    (println "Release" permits)
    (.release ^java.util.concurrent.Semaphore semaphore
      permits)))

(defn acquired ^CloseableSemaphore
  [^java.util.concurrent.Semaphore semaphore permits]
  (.acquire semaphore permits)
  (->CloseableSemaphore semaphore permits))

(def delay1 (object-array 1))
(def s1 (java.util.concurrent.Semaphore. 1 true))

(identity (seq delay1))

(defn foo []
  (println "Liczę...")
  (Thread/sleep 1000)
  42)

(defn get-foo []
  (with-open [_ (acquired s1 1)]
   (if-let [value (aget ^objects delay1 0)]
     value

     (let [value (foo)]
       (aset ^objects delay1 0 value)
       value))))

(dotimes [_ 5]
  (future
    (println (get-foo))))
