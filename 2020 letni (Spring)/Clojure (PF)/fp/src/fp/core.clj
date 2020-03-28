(ns fp.core)

;; * WARTOŚCI PROSTE

;; * KOMBINACJA
;; S-EXP
(<f> <a1> <a2> <a3> ... <an>)
;; Sposób realizacji tzw. kombinacji (składania)
;; Notacja prefiksowa
1 + 2
(+ 1 2)
sin(5)
(sin 5)
;; 1 + 2 * 4 * (5 + 6)
(+ 1 (* 2 4 (+ 5 6)))

;; * ABSTRAKCJA
(def pi 3.14159)
(* pi 3)

(def square (fn [x] (* x x)))
