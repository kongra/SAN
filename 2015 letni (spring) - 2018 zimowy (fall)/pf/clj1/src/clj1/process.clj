(ns clj1.process)

;; Aksjomaty: inc, dec, 0

(defn plus [x y]
  (if (= x 0)
    y

    (recur (dec x) (inc y))))

(defn add [x y]
  (if (= x 0)
    y

    (inc (add (dec x) y))))

(defn fib [n]
  (if (or (= n 0) (= n 1))
    n

    (+ (fib (- n 1)) (fib (- n 2)))))

(defn factorial [n]
  (if (= n 0)
    1

    (*' n (factorial (dec n)))))

(defn factorialIt [n value]
  (if (= n 0)
    value

    (recur (dec n) (*' value n))))
