(ns fp1.core)

(defn square
  [x]
  (* x x))

;; (if <predicate>
;;   <consequent>
;;   <alternative>)

;; TERNARY CONDITIONAL:
;; <predicate> ? <consequent> : <alternative>
;; let x = s === 'aaa' ? 3 : 4;

(defn factorial
  [n]
  (if (zero? n)
    1

    (*' n (factorial (dec n)))))

;; Zadanie 1. Proszę napisać procedurę, która zwraca n-ty wyraz ciągu Fibonacciego.
