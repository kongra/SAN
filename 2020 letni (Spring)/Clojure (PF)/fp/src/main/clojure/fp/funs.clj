(ns fp.core)

(defn sum-ints
  [a b]
  (if (> a b)
    0
    (+ a
      (sum-ints (inc a) b))))

;; (sum-ints 2 5)
;; (+ 2 (sum-ints 3 5))
;; (+ 2 (+ 3 (sum-ints 4 5)))
;; (+ 2 (+ 3 (+ 4 (sum-ints 5 5))))
;; (+ 2 (+ 3 (+ 4 (+ 5 (sum-ints 6 5)))))
;; (+ 2 (+ 3 (+ 4 (+ 5 0))))
;; (+ 2 (+ 3 (+ 4 5)))
;; (+ 2 (+ 3 9))
;; (+ 2 12)
;; 14

(defn square [x] (* x x))

(defn sum-squares
  [a b]
  (if (> a b)
    0
    (+ (square a)
      (sum-squares (inc a) b))))

(defn pi-sum
  [a b]
  (if (> a b)
    0
    (+ (/ 1 (* a (+ a 2)))
      (pi-sum (+ a 4) b))))

(defn sum
  [term a next b]
  (if (> a b)
    0
    (+ (term a)
      (sum term (next a) next b))))

(defn sum
  ([term a next b]
   (sum term a next b 0))

  ([term a next b result]
   (if (> a b)
     result
     (recur term
       (next a)
       next
       b
       (+ result (term a))))))

(defn sum-int
  [a b]
  (sum identity a inc b))

(defn sum-squares
  [a b]
  (sum square a inc b))

(defn pi-term
  [x]
  (/ 1 (* x (+ x 2))))

(defn pi-next
  [i]
  (+ i 4))

(defn pi-sum
  [a b]
  (sum pi-term a pi-next b))
