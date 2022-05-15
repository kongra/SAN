(ns fp1.core)

(defn foo [x]
  (+ x 3))

;; Przykład. Zdefiniuj procedurę silnia/factorial
;; Dla danego n == 0 zwróć 1
;; Dla danego n >  0 zwróć n * silnia (n-1)

(defn factorial [n]
  (if (= n 0)
    1

    (*' n (factorial (- n 1)))))

;; Wyrażenie warunkowe if
#_(if <predicate>
    <consequent>
    <alternative>)

;; Jeżeli <predicate> (predykat) jest spełniony, tj. ma wartość prawdy logicznej, to
;; wartością całego wyrażenia warunkowego jest wartość wyrażenia <consequent> (następnika)
;; w przeciwnym wypadku wartością całego wyrażenia warunkowego jest wartość
;; <alternative> (alternatywy).

;; <predicate> ? <consequent> : <alternative> - tzw. ternary conditional
;; np. int n = i == 10 ? 3 : 4;

;; Zadanie 1. Zrealizuj w Clojure procedurę obliczającą n-ty wyraz ciągu Fibonacciego
;; fib(0) => 0
;; fib(1) => 1
;; fib(n | n > 1) => fib(n-1) + fib(n-2)

(defn abs- [x]
  (if (< x 0)
    (- x)

    x))

(defn square [x] (* x x))

(defn good-enough? [G x]
  (< (abs- (- x (square G))) 0.00001))

(defn avg [x y]
  (/ (+ x y) 2))

(defn improve [G x]
  #_(println "improve...")
  (avg G (/ x G)))

(defn sqrt [G x]
  (if (good-enough? G x)
    G
    (sqrt (improve G x) x)))

;; Zadanie 2.
;; a. Przebieg procedury Herona uzależnić od parametru
;;    formalnego Epsilon
;; b. Przebieg procedury Herona uzależnić od parametru
;;    formalnego N (ilości kroków)

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(sum-of-squares 3 4)
(+ (square 3) (square 4))
(+ (* 3 3) (* 4 4))
(+ 9 16)
25

(+ 1 2 3 4 5)

(defn if- [pred consequent alternative])

(def x 0)
(if- (zero? x) :nothing (/ 1 x))

;; ARYTMETYKA PEANO
(defn p+ [x y]
  (if (= x 0)
    y
    (p+ (dec x) (inc y))))

(p+ 3 4)
(if (= 3 0) 4 (p+ (dec 3) (inc 4)))
(p+ (dec 3) (inc 4))
(p+ 2 5)
(if (= 2 0) 5 (p+ (dec 2) (inc 5)))
(p+ (dec 2) (inc 5))
(p+ 1 6)
(if (= 1 0) 6 (p+ (dec 1) (inc 6)))
(p+ (dec 1) (inc 6))
(p+ 0 7)
(if (= 0 0) 7 (p+ (dec 0) (inc 7)))
7

;; Zadanie 3.
;; Zmodyfikuj procedurę Herona tak, aby liczyła ona pierwiastek 3-go stopnia
;; Hint: SICP Exercise 1.8, page 33
