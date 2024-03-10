(in-ns 'user)

;; (defn foo [n]
;;   (+' n 1))

;; (class 3.14M)
;; (foo 3.14M)
;; (class (foo Integer/MAX_VALUE))

;; (class (foo Long/MAX_VALUE))

;; (defn sum-numbers
;;   [start end]
;;   (reduce +' (range start (inc end))))

;; (sum-numbers 0 100000000)

;; ;; Integer/MAX_VALUE

;; Long/MAX_VALUE

(defn foo [x]
  (* 2 x))

(defn goo [y]
  (+ y 3))

(foo 3)
(goo 4)

(goo (foo 3))

(def f1 (comp goo foo))
(f1 40)

[23 45 67]

;; 1 + 2 * 3 => 7
;; 1 + 2 * 3 => 9???

(+ 1 (* 2 3 6 7 8))

(class 123)
(class "xyz")

(class false)

(class 3.14)
