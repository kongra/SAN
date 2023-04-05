(in-ns 'user)

(require '[clj.telsos.core.macros :refer :all])
(require '[cljc.telsos.core       :refer :all])

(set! *warn-on-reflection*       true)
;; (set! *unchecked-math* :warn-on-boxed)

(defn sum-ints
  [a b]
  (if (> a b)
    0
    (+ a
       (sum-ints (inc a) b))))

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

;; (defn <name>
;;   [a b]
;;   (if (> a b)
;;     0
;;     (+ (<term> a)
;;        (<name> (<next> a) b))))

(defn sum
  [term a next- b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next- a) next- b))))

(defn sum'
  ([term next- a b]
   (sum' term next- a b 0))

  ([term next- a b result]
   (if (> a b)
     result

     (recur term next- (next- a) b (+ result (term a))))))

(defn sum-ints'
  [a b]
  (sum' identity inc a b))

(def sum-ints'' (partial sum' identity inc))

(sum-ints'  1 10)
(sum-ints'' 1 10)

;; g := λ x y z v. (+ x y z v)
;; g := λ x. λ y. λ z. λ v. (+ x y z v)

;; (partial g 1 2)
;; λ z. λ v. (+ 1 2 z v)

(defn sum-squares'
  [a b]
  (sum' square inc a b))

(defn pi-sum-term [i] (/ 1 (* i (+ i 2))))
(defn inc-by [n] (fn [i] (+ i n)))

(defn pi-sum'
  [a b]
  (sum' pi-sum-term (inc-by 4) a b))

;; (sum-ints 1 5)
;; (+ 1 (sum-ints 2 5))
;; (+ 1 (+ 2 (sum-ints 3 5)))
;; (+ 1 (+ 2 (+ 3 (sum-ints 4 5))))
;; (+ 1 (+ 2 (+ 3 (+ 4 (sum-ints 5 5)))))
;; (+ 1 (+ 2 (+ 3 (+ 4 (+ 5 (sum-ints 6 5))))))
;; (+ 1 (+ 2 (+ 3 (+ 4 (+ 5 0)))))
;; (+ 1 (+ 2 (+ 3 (+ 4 5))))
;; (+ 1 (+ 2 (+ 3 9)))
;; (+ 1 (+ 2 12))
;; (+ 1 14)
;; 15

(defn avg [a b] (/ (+ a b) 2))

(defn heron-trans [x]
  (fn [y]
    (avg y (/ x y))))

(defn fixed-point-close-enough?
  [x y] (< (abs (- x y)) 0.00001))

(defn fixed-point-iter
  [f x1 x2]
  (if (fixed-point-close-enough? x1 x2)
    x2
    (recur f x2 (f x2))))

(defn fixed-point
  [f start]
  (fixed-point-iter f start (f start)))

(defn sqrt
  [x]
  (fixed-point (heron-trans x) 1))

;; y = x / y ;; * y
;; y^2 = x

(defn heron-trans' [x]
  (fn [y]
    (/ x y)))

(defn average-damp
  [f]
  (fn [x] (avg x (f x))))

(defn sqrt'
  [x]
  (fixed-point (average-damp (heron-trans' x)) 1))


(def dx 0.00001)

(defn deriv
  [g]
  (fn [x]
    (/ (- (g (+ x dx))
          (g    x   ))

       dx)))

(def f1 (fn [x] (* x x)))
;; d (x^2) / dx = 2x

(map        f1  [1 2 3 4 5 6 7 8 9 10])
(map (deriv f1) [1 2 3 4 5 6 7 8 9 10])

;; f(x) = x - g(x)/g'(x)

(defn newtons-trans [g]
  (let [g' (deriv g)]
    (fn [x]
      (- x (/ (g x) (g' x))))))

(defn newtons-method
  [f y]
  (fixed-point (newtons-trans f) y))

(defn sqrt''
  [x]
  (newtons-method (fn [y] (- (square y) x)) 1))

;; y^2 = x
;; y^2 - x = 0

(* (sqrt'' 2) (sqrt'' 2))

;; UŁAMKI
(double (rationalize 45.2735765))

(defn make-pair [x y]
  (fn [selector]
    (cond (= selector 0) x
          (= selector 1) y

          :else
          (throw (ex-info "Illegal pair selector" {:selector selector})))))

(defn pair-first  [p] (p 0))
(defn pair-second [p] (p 1))

(pair-first  (make-pair 3 4))
(pair-second (make-pair 3 4))

(defn gcd
  [m n]
  (let [r (mod m n)]
    (if (zero? r)
      n
      (recur n r))))

(defn make-rat [n d]
  (let [g (gcd n d)]
    (make-pair (/ n g) (/ d g))))

(defn numer [rat]
  (pair-first rat))

(defn denom [rat]
  (pair-second rat))

(defn rat->str [rat]
  (str (numer rat) "/" (denom rat)))

(defn rat+ [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn rat* [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(rat->str (rat+ (make-rat 3 4) (make-rat 5 6)))

#_(let [symbol1 wartość1
        symbol2 wartość2
        ...
        symbolN wartośćN]

    ciało)

;; function foo (x) {
;;   const y = x + 4;
;;   return y * 6;
;; }

(defn foo [x]
  (let [y (+ x 4)]
    (* y 6)))
