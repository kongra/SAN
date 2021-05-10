(ns fp1.core)

;; CORE
(defn abs
  [x]
  (if (< x 0)
    (- x)
    x))

(defn FP-close-enough?
  [x y] (< (abs (- x y)) 0.00001))

(defn FP-iter
  [f old current]
  (if (FP-close-enough? old current)
    current
    (recur f current (f current))))

(defn fixed-point
  [f start]
  (FP-iter f start (f start)))

;; EXAMPLES
(defn avg
  [x y]
  (/ (+ x y) 2))

(defn sqrt
  [x]
  (fixed-point
    (fn [y] (avg y (/ x y)))
    1 ;; the initial guess
    ))

(defn average-damp
  [f]
  (fn [x] (avg x (f x))))

(defn sqrt
  [x]
  (fixed-point
    (average-damp (fn [y] (/ x y)))
    1 ;; the initial guess
    ))

(def dx 0.00000001)

(defn deriv
  [g]
  (fn [x]
    (/ (- (g (+ x dx)) (g x))
      dx)))

(defn Newtons-transform
  [g]
  (let [g' (deriv g)]
    (fn [x]
      (- x (/ (g x) (g' x))))))

(defn Newtons-method
  ;; Metoda wyznaczania pierwiastka funkcji f
  [g y]
  (fixed-point (Newtons-transform g) y))

(defn square [x] (* x x))

(defn sqrt
  [x]
  (Newtons-method (fn [y] (- (square y) x)) ;; y → y^2 - x
    1))

(defn cubic-root
  [x]
  (Newtons-method (fn [y] (- (* y y y) x)) ;; y → y^3 - x
    1))

;; Zadanie 10. Jeśli f jest funkcją jednoargumentową określoną na liczbach a n
;; jest dowolną liczbą naturalną, to n-krotnym złożeniem funkcji f
;; nazywamy funkcję, której wartością jest wynik n-krotnego zastosowania
;; funkcji f:
;; x → f(f( ... (f(x)) ...))
;; Napisz procedurę realizującą n-krotne złożenie funkcji f.
