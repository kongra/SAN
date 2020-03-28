(ns fp.heron)

;; Zadanie. Napisz procedurę obliczającą wartość pierwiastka kwadratowego z liczby x

;; Problem sformułowany deklaratywnie:
;; sqrt(x) dla x >= 0
;; to jest takie y >= 0, że y*y = x

;; Wiedza o rozwiązaniu problemu (imperatwna):
;; 1. Zgadnij (przyjmij początkowe) y
;; 2. Ulepsz y
;; 3. Ulepszaj y tak długo, aż y stanie się wystarczająco dobre

;; def heronSqrt(y, x):
;;   if goodEnough(y, x):
;;     return y
;;   else
;;     return heronSqrt(improve(y, x), x)

(defn square
  [x]
  (* x x))

;; (if <predicate>
;;   <consequent>
;;   <alternative>)

(defn abs
  [x]
  (if (< x 0)
    (- x)
    x))

(defn good-enough?
  [y x]
  (< (abs (- x (square y))) 0.00001))

(defn avg
  [x y]
  (/ (+ x y) 2))

(defn improve
  [y x]
  (avg y (/ x y)))

(defn heron-sqrt
  [y x]
  (if (good-enough? y x)
    y

    (heron-sqrt (improve y x) x)))

(println (double (heron-sqrt 1 25)))
