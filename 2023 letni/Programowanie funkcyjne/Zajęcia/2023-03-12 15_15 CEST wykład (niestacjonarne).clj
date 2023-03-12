(def x 5.0)
(+ x 4)

((fn [y] (+ y 3)) 4)

(+ 4 3)

(def foo (fn [y] (+ y 3)))
(foo 4)

(defn goo [y]
  (+ y 3))

(goo 4)

(defn moo [a b]
  (+ a b 3))

(moo 4 5)

;; (if <predicate>
;;    <consequent>
;;    <alternative>)

(if (zero? x)
  :zero
  :other)

(def l1 (list 1 2 3 4 5 6))
(def l2 (cons 10 l1))

(apply + l1)
(doseq [e l1] (println e))

(def ages {"John" 34
           "Michael" 25
           "Ann" 27})

(ages "Johnny" 12)
