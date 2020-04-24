(ns fp.core)

;; * WARTOŚCI PROSTE

;; * KOMBINACJA
;; S-EXP
;; (<f> <a1> <a2> <a3> ... <an>)
;; Sposób realizacji tzw. kombinacji (składania)
;; Notacja prefiksowa
;; 1 + 2
;; (+ 1 2)
;; sin(5)
;; (sin 5)
;; 1 + 2 * 4 * (5 + 6)
(+ 1 (* 2 4 (+ 5 6)))

;; * ABSTRAKCJA
(def pi 3.14159)
(* pi 3)
(def square (fn [x] (* x x)))

(def l1 (list 1 2 3 4 5 6 7 8 9 10))

;; ((0 1) (1 1) (1 2) (2 3) (3 5) (5 8) ...)

(defn fib-gen
  [l1]
  (list (second l1) (+' (first l1) (second l1))))

(defn fib-list
  [n]
  (map first (take n (iterate fib-gen (list 0 1)))))
