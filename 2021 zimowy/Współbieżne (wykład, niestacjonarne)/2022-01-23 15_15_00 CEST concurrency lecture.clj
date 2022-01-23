(set! *warn-on-reflection* true)

(defn- start-thread!
  [body]
  (doto (Thread. body)
    (.start)))

(start-thread!
  #(println "Działa wątek"
     (Thread/currentThread)))

(future
  (println "Działa wątek" (Thread/currentThread)))

(def p1 (promise))

(future
  (println "Próbuję odczytać p1")
  (println "Odczytałem" @p1))

(future
  (deliver p1 5))

(def account-A (atom 10000))

#_(defn add! [account amount]
    (let [value @account #_(deref account)
          new-value (+ value amount)]

      (reset! account new-value)))

(defn add! [account amount]
  (swap! account
    (fn [value]
      (+ value amount))))

(dotimes [i 10]
  (start-thread!
    #(dotimes [_ 10000]
       (add! account-A
         (if (even? i) 1 -1)))))

(def account-B (atom 10000))
(def account-C (atom 10000))

(defn transfer!
  [acc-1 acc-2 amount]
  (add! acc-1    amount)
  (add! acc-2 (- amount)))

(dotimes [i 10]
  (start-thread!
    #(dotimes [_ 10000]
       (transfer! account-B account-C
         (if (even? i) 1 -1)))))

(println "Suma" (+ @account-B @account-C))

;; Wykorzystamy STM (Software Transactional Memory)

(def account-D (ref 10000))
(def account-E (ref 10000))

(defn transfer-safely!
  [acc-1 acc-2 amount]
  (dosync
    (alter acc-1 + amount)
    (alter acc-2 - amount)))

(dotimes [i 10]
  (start-thread!
    #(dotimes [_ 10000]
       (transfer-safely! account-D account-E
         (if (even? i) 1 -1)))))

(println "Suma" (+ @account-D @account-E))
