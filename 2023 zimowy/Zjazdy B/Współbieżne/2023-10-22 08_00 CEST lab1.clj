(in-ns 'user)

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(defn foo ^long
  [^long n]
  (println "--6" (.getName (Thread/currentThread)))
  (Thread/sleep 1000)
  (+ n 3))

(defn -main []
  (println "--1" "Startujemy")

  (let [i (foo 4)] ;; Wywołanie synchroniczne
    (println "--2" i))

  (println "--3" (.getName (Thread/currentThread)))

  (let [f (future (foo 4))] ;; Wywołanie asynchroniczne
    (println "--4" @f))

  (println "--5" "Koniec"))

(-main)
