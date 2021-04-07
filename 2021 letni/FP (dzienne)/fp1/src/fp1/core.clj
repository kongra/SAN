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
;; (fn [<parameter-1> <parameter-2> ... <parameter-n>]
;;   <body> := <s-expression-1> <s-expression-2> ... <s-expression-m>
;;   )

;; Wartość wywołania funkcji tak zdefiniowanej z pewnymi argumentami równa jest wartości
;; <s-expression-m> (inaczej, ostatniego wyrażenia)

#_((fn [x y]
     (println "Wywołano funkcję anonimową z argumentami" x y)
     (+ x y))
   1
   2)

(def foo 4)

(def goo (fn [x y]
           (println "Działa goo z argumentami" x y)
           (+ x y 1)))

;; (<f> <argument-1> <argument-2> ... <argument-n>)

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

;; Problem: Wyznaczmy procedurę obliczającą pierwiastek kwadratowy z liczby x.
;; Deklaratwnie: sqrt(x) dla x >= 0 jest to taka liczba y, że y^2 = x.
;; Imperatywnie:
;; - Zgadnij G
;; - Ulepsz G ← (avg G (/ x G))
;; - Nadal usprawniaj G aż będzie WYSTARCZAJĄCO DOBRE

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

(defn good-enough?
  [G x]
  (<= (abs (- x (square G))) 0.00000001))

(defn avg [x y]
  (/ (+ x y) 2))

(defn improve
  [G x]
  (avg G (/ x G)))

(defn heron-sqrt
  [G x]
  (println "heron-sqrt " G x)
  (if (good-enough? G x)
    ;; Nie ma potrzeby kontynuowania, G jest ok.
    G

    ;; W przeciwnym wypadku
    (heron-sqrt (improve G x) x)))

;; Zadanie 4. Proszę uzależnić przebieg procesu obliczania heron-sqrt od
;;            parametru epsilon a nie od stałej (jak to ma miejsce obecnie).

;; Zadanie 5. Proszę zrealizować procedurę obliczającą pierwiastek sześcienny z x.
;;            Stosujemy wzorzec postępowania jak przy heron-sqrt. Hint: SICP.

;; Model podstawieniowy na przykładzie:
;; Aby wyznaczyć wartość KOMBINACJI (<op> <arg1> ... <arg N>)
;; a. Poddaj ewaluacji operator <op> uzyskując procedurę
;; b. Poddaj ewaluacji operandy <arg 1>, ..., <arg N> aby uzyskać wartości
;;    aktualne parametrów formalnych (argumenty wywołania procedury)
;; c. Zastosuj procedurę do argumentów
;;    ca. Skopiuj ciało procedury podstawiając dostarczone argumenty w miejsce
;;        odpowiednich parametrów fomalnych procedury
;;    cb. Wyznacz wartość uzyskanego w ten sposób nowego ciała.

(defn sum-of-squares
  [x y]
  (+ (square x) (square y)))

;; (sum-of-squares 3 4)      =>
;; (+ (square 3) (square 4)) =>
;; (+ (*    3 3) (*    4 4)) =>
;; (+ 9 16)                  =>
;; 25

;; Przykład: Arytmetyka Peano
#_(defn peano+ [x y]
    (println "peano+" x y)
    (if (= x 0)
      y
      (peano+ (dec x) (inc y))))

(defn peano+ [x y]
  (println "peano+" x y)
  (if (= x 0)
    y
    (recur (dec x) (inc y))))

;; (peano+ 3 4)
;; (peano+ (dec 3) (inc 4)) =>
;; (peano+ 2 5)             =>
;; (peano+ (dec 2) (inc 5)) =>
;; (peano+ 1 6)             =>
;; (peano+ (dec 1) (inc 6)) =>
;; (peano+ 0 7)             =>
;; 7

;; W sensie czasu   (CPU) peano+ jest O[x].
;; W sensie pamięci (RAM) peano+ jest O[1].

;; TAKI PROCES OBLICZENIOWY JAK POWYŻEJ, KTÓRY MA STAŁĄ PAMIĘĆ
;; (ROZMIAR PAMIĘCI JEST NIEZALEŻNY OD ROZMIARU PROBLEMU),
;; NAZYWAMY ITERACJĄ.

(defn peano+ [x y]
  (if (= x 0)
    y
    (inc (peano+ (dec x) y))))

;; (peano+ 3 4)                   =>
;; (inc (peano+      2  4))       =>
;; (inc (inc (peano+ 1  4)))      =>
;; (inc (inc (inc (peano+ 0 4)))) =>
;; (inc (inc (inc 4)))            =>
;; (inc (inc 5))                  =>
;; (inc 6)                        =>
;; 7

;; W sensie pamięci powyższa procedura jest O[x].
;; TAKI PROCES NAZYWAMY REKURENCYJNYM.

;; LIST/VECTOR
;; Zadanie 6. Zrealizuj procedurę, która zwraca (w zależności od parametru) listę lub wektor
;;            n pierwszych elementów ciągu Fibonacciego.

;; fp1.core=> (def s1 (hash-set 1 2 3 4 5))
;; #'fp1.core/s1
;; fp1.core=> s1
;; #{1 4 3 2 5}
;; fp1.core=> (def s1 #{1 2 3 4 5})
;; #'fp1.core/s1
;; fp1.core=> s1
;; #{1 4 3 2 5}
;; fp1.core=> (conj s1 6)
;; #{1 4 6 3 2 5}
;; fp1.core=> (conj s1 "abcd")
;; #{"abcd" 1 4 3 2 5}
;; fp1.core=> s1
;; #{1 4 3 2 5}
;; fp1.core=> (disj s1 2)
;; #{1 4 3 5}
;; fp1.core=> (contains? s1 4)
;; true
;; fp1.core=> (contains? s1 55)
;; false
;; fp1.core=> s1
;; #{1 4 3 2 5}
;; fp1.core=> (s1 4)
;; 4
;; fp1.core=> (s1 55)
;; nil
;; fp1.core=> (require '[clojure.set :as set])
;; nil
;; fp1.core=> (set/union s1 s1)
;; #{1 4 3 2 5}
;; fp1.core=>

;; Zadanie 7.
;; Proszę przejść iteracyjnie po elementach ciągu Fibonacciego mniejszych niż 1000
;; oraz zwrócić zbiór elementów parzystych.
