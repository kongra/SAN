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
