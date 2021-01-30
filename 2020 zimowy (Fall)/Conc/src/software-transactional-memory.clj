;; (def account-1 (atom 5000))
;; (def account-2 (atom 5000))

(def account-1 (ref 5000))
(def account-2 (ref 5000))

(set! *warn-on-reflection* true)

;; (defn add-subtract!
;;   [delta]
;;   (swap! account-1 #(+ % delta))
;;   (swap! account-2 #(- % delta)))

(declare dump!)
(defn add-subtract!
  [delta]
  (dosync
    (alter account-1 #(+ % delta))
    (alter account-2 #(- % delta))

    #_(dump!)))

(defn dump!
  []
  (dosync
    (let [x1 (ensure account-1)
          x2 (ensure account-2)]

      (println
        "Suma:" (+ x1 x2)
        "account-1:" x1
        "account-2:" x2))))

(defn test-1
  []
  (let [threads (repeatedly 100
                  (fn [] (Thread.
                           #(add-subtract! 3))))]

    (doseq [th threads]
      (.start ^Thread th))

    (println "Czekam na zakończenie wątków")

    (doseq [th threads]
      (.join ^Thread th))

    (println "Po wszystkim:")
    (dump!)))

(test-1)
