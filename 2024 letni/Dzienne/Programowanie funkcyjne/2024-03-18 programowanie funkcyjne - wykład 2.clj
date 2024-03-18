(in-ns 'user)

(def n 5)
(+ n 4)

(def s1 "abcd")
;; (+ s1 4)

;; 2 + 3 * 4 =  2 + (3  * 4) = 2 + 12 = 14
;; 2 + 3 * 4 = (2 +  3) * 4  = 5 *  4 = 20

(+ 2 (* 3 4))
(+ 2 3 4 5 6 7 8 9)

;; 3 * 4 = (* 3 4)

(def foo
  (fn [x y]
    (+ x y 3)))

(defn moo
  [x y]
  ( + x y 3)
  )

(moo 3 4)

(def goo #(+ %1 %2 3))

(goo 3 4)

;; RODZAJE ZDAŃ W JĘZYKACH PROGRAMOWANIA
;; * Wyrażenia  (ang. expressions) - mają wartość
;; * Instrukcje (ang.  statements) - nie mają wartości

;; W lispach nie ma instrukcji, są WYŁĄCZNIE wyrażenia. Oznacza to, że
;; każde zdanie (konstrukcja składniowa) posiada wartość.

;; int n = if (x == 3) { 5 } else { 6 }; BŁĄD

;; ternary conditional:
;; int n = x == 3 ? 5 : 6; OK

;; WZÓR HERONA
#_(if <predicate>
    <consequent>
    <alternative>)

(defn abs' [x]
  (if (< x 0)
    (- x)
    x))

(def π 3.14159)
#_(def square  (fn [x] (* x x)))

(defn square [x]
  (* x x))

(square 11)

(defn avg
  [x y]
  (/ (+ x y) 2))

(defn good-enough?
  [g x]
  (< (abs (- x (* g g))) 0.00001))

(defn improve
  [g x]
  (avg g (/ x g)))

#_(defn sqrt [g x]
    (if (good-enough? g x)
      g
      (sqrt (improve g x) x)))

(defn sqrt [g x]
  (if (good-enough? g x)
    g
    (recur (improve g x) x)))


(double (sqrt 1 2))

;; Zadanie 1.
;; a. Wzór na pierwiastek sześcienny - zaimplementować
;; b. Przebieg procedury Herona uzależnić od parametru formalnego (epsilon) procedury sqrt
;; c. Przebieg procedury Herona uzależnić od N (ilości kroków)
