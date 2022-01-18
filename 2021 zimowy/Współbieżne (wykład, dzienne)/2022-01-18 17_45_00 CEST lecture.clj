(def account-atom-1 (atom 10000))

#_(defn- new-value-after-addition
    [old-value amount]
    (println "new-value-after-addition" old-value amount)
    (+ old-value amount))

#_(defn- add-money!
    [amount]
    (swap! account-atom-1
      ;; 1. Weź wartość z atomu oraz amount i przekaż do funkcji f
      ;;    (w naszym przypadku f to new-value-after-addition)
      ;; 2. Zapisz wynik wywołania f (nową wartość) do atomu
      ;; 3. Zwróć nową wartość
      new-value-after-addition
      amount))

(defn- +money!
  [amount]
  (swap! account-atom-1 + amount))

(defn- start-thread!
  [body]
  (let [thread (Thread. body)]
    (.start thread)

    thread))

(dotimes [_ 10]
  (start-thread!
    #(dotimes [_ 10]
       (+money! 1))))

@account-atom-1

(def registry-atom (atom []))

(defn register! [x]
  (swap! registry-atom conj x))

(conj [1 2 3] 5)

(register! 3)

;; WICKED EXAMPLE
(def account-atom-A (atom 1000000))
(def account-atom-B (atom 1000000))

(defn transfer-money!
  [amount]
  (swap! account-atom-A - amount)
  (swap! account-atom-B + amount))

(identity (+ @account-atom-A @account-atom-B))

(dotimes [_ 50]
  (start-thread!
    #(dotimes [_ 10000]
       (transfer-money! 1))))

(+ @account-atom-A @account-atom-B)

(identity @account-atom-A)
(identity @account-atom-B)

;; SOFTWARE-TRANSACTIONAL MEMORY
(def account-ref-A (ref 1000000))
(def account-ref-B (ref 1000000))

(identity (+ @account-ref-A @account-ref-B))

(defn safely-transfer-money!
  [amount]
  (dosync
    (alter account-ref-A - amount)
    (alter account-ref-B + amount)))

(safely-transfer-money! 1)

(time
  (dotimes [i 50]
    (start-thread!
      #(dotimes [_ 200000]
         (transfer-money! 1)))

    (println "DONE" i)))
