(ns fp.fixed)

;; f : R -> R
;; FIXed(f)
;; FIXed    - operator punktu stałego
;; FIXed(f) - punkt stały odwzorowania f
;;            jest to takie y, że f(y) = y

(defn abs
  [x]
  (if (neg? x) (- x) x))

(defn avg
  [x y]
  (/ (+ x y) 2))

(defn FIXed-close-enough?
  [x y] (< (abs (- x y)) 0.00001))

(defn FIXed-iter
  [f prev curr]
  (if (FIXed-close-enough? prev curr)
    curr
    (FIXed-iter f curr (f curr))))

(defn FIXed
  [f start]
  (FIXed-iter f start (f start)))

(defn Heron-improve
  [x y]
  (avg y (/ x y)))

(defn Heron-sqrt
  [x]
  (FIXed
    ;; f
    (fn [y] (Heron-improve x y))

    ;; start
    1))

(double (Heron-sqrt 2))

;; Rozważmy funkcję
;; g : y -> x/y
;; Jeżeli jakieś y' jest punktem stałym odwzorowania g, to znaczy,
;; że y' = g(y')
;;    y' = x / y' / * y'
;;    y'^2 = x
;;
;; W takim razie, może należy iterować g:

(defn g [x y] (/ x y))

(defn Heron-sqrt-g
  [x]
  (FIXed
    ;; f
    (fn [y] (g x y))

    ;; start
    1))

;; (Heron-sqrt-g 2)
;; 1 -> 2 / 1 = 2
;; 2 -> 2 / 2 = 1
;; 1 -> 2 / 1 = 2
;; 2 -> 2 / 2 = 1
;; ...

;; Zastosujmy technikę tłumienia oscylacji przez uśrednianie

(defn average-damp
  [f]
  (fn [x] (avg x (f x))))

(defn Heron-sqrt-g'
  [x]
  (FIXed
    ;; f
    (average-damp (fn [y] (g x y)))

    ;; start
    1))

(double (Heron-sqrt-g' 2))

;; METODA NEWTONA
;; Jeśli x -> g(x) jest f-cją różniczkowalną (o niezerowej pochodnej
;; przynajmniej w interesujących nas punktach), to rozwiązanie równania

;; g(x) = 0

;; jest punktem stałym funkcji x -> f(x), gdzie
;; f(x) = x - g(x)/g'(x)
;; natomiast g'(x) jest pochodną g w punkcie x.

(def dx (/ 1 100000) #_0.0000001)

(defn deriv
  [g]
  (fn [x]
    (/ (- (g (+ x dx)) (g x))
      dx)))

(def g1  (fn [x] (+ (* 2 x) 1)))
(def g1' (deriv g1))

(defn Newtons-transform
  [g]
  (let [g' (deriv g)]
    (fn [x]
      (- x (/ (g x) (g' x))))))

(defn Newtons-method
  [g start]
  (FIXed (Newtons-transform g) start))

(Newtons-method g1 200)

(defn square [x] (* x x))

(defn Heron-Newtons-sqrt
  [x]
  (Newtons-method (fn [y] (- (square y) x)) ;; y -> y^2 - x
    1))

(double (Heron-Newtons-sqrt 2))
