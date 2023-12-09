(ns atoms)

(def account-1 (ref 10000))
(def account-2 (ref  5000))

(println "BEFORE:"
         account-1 account-2
         (+ @account-1 @account-2))

(defn transfer [amount]
  (dosync
   (Thread/sleep (long (rand-int 30)))
   (alter account-1 - amount)
   (Thread/sleep (long (rand-int 30)))
   (alter account-2 + amount)))

(doseq [_ (range 1 101)]
  (.start (Thread. #(transfer 10))))

#_(Thread/sleep 1000)
(println "AFTER:"
         account-1 account-2
         (+ @account-1 @account-2))
