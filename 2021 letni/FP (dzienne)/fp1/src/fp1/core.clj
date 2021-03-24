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

;; FUNKCJE W CLOJURE

;; Definicja funkcji:
(fn [<parameter-1> <parameter-2> ... <parameter-n>]
  <body> := <s-expression-1> <s-expression-2> ... <s-expression-m>
  )

;; Wartość wywołania funkcji tak zdefiniowanej z pewnymi argumentami równa jest wartości
;; <s-expression-m> (inaczej, ostatniego wyrażenia)

((fn [x y]
   (println "Wywołano funkcję anonimową z argumentami" x y)
   (+ x y))
 1
 2)

(def foo 4)

(def goo (fn [x y]
           (println "Działa goo z argumentami" x y)
           (+ x y 1)))

(<f> <argument-1> <argument-2> ... <argument-n>)

(defn goo
  [x y]
  (println "Działa goo z argumentami" x y)
  (+ x y 1))

;; PRZECIĄŻANIE NAZW
(defn moo
  ;; Wariant o arności 1
  ([x]
   (println "Działa goo z argumentem" x)
   (+ x 1))

  ;; Wariant o arności 2
  ([x y]
   (println "Działa goo z argumentami" x y)
   (+ x y 1)))

;; Przykład:

(defn sum-naturals
  "It sums up numbers 1,2,3,...,n"
  ([n]
   (sum-naturals n 1 0))

  ([n i result]
   ;; (println "sum-naturals wywołane z argumentami" n i result)
   (if (> i n)
     result

     (recur n (inc i) (+ result i)))))

;; n, i, oraz result - tzw. zmienne stanu (ang. state-variables)
;; Optymalizacja rekurencji krańcowej (ang. tail-call optimization)

;; Zadanie 2. Proszę napisać funkcję, która zwraca iloczyn liczb parzystych od 2 do 10000.
;; Zadanie 3. https://projecteuler.net/problem=2
