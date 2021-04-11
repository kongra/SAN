(ns fp1.process)

(defn square [x]
  (* x x))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

;; Ewaluacja - wyznaczanie wartości wyrażenia
;; Jak przebiega proces ewaluacji wyrażenia:
(sum-of-squares 2 3)

;; Korzystamy z tzw. modelu podstawieniowego:
(sum-of-squares 2 3)
(+ (square 2) (square 3))
(+ (* 2 2)    (* 3 3))
(+ 4 9)
13

;; RODZAJE WYRAŻEŃ W LISPIE:
;; - LICZBY (LITERAŁY NUMERYCZNE)
;; - SYMBOLE
;; - lambda-WYRAŻENIA* (fn [x] ...)
;; - DEFINICJE* (def ...)
;; - WYRAŻENIA WARUNKOWE COND/IF*
;; - KOMBINACJE (<op> <arg 1> ... <arg N>)
;; [*] FORMY SPECJALNE

;; REGUŁY EWALUACJI:
;; LICZBY, SYMBOLE - W SPOSÓB "NATURALNY"

;; KOMBINACJE (REGUŁA PODSTAWIENIOWA, MODEL PODSTAWIENIOWY):
;; Aby wyznaczyć wartość KOMBINACJI (<op> <arg1> ... <arg N>)
;; a. Poddaj ewaluacji operator <op> uzyskując procedurę
;; b. Poddaj ewaluacji operandy <arg 1>, ..., <arg N> aby uzyskać wartości
;;    aktualne parametrów formalnych (argumenty wywołania procedury)
;; c. Zastosuj procedurę do argumentów
;;    ca. Skopiuj ciało procedury podstawiając dostarczone argumenty w miejsce
;;        odpowiednich parametrów fomalnych procedury
;;    cb. Wyznacz wartość uzyskanego w ten sposób nowego ciała.

;; JEST TO APLIKATYWNY PORZĄDEK EWALUACJI

;; SUMOWANIE LICZB W ARYTMETYCE PEANO
;; Mamy liczby 0, 1, 2, ... oraz operatory inc, dec
(defn peano+ [x y]
  (if (= x 0)
    y
    (peano+ (dec x) (inc y)))) ;; Procedura jest REKURENCYJNA w sensie definicji

;; Przeanalizujmy ewaluację wyrażenia:
(peano+ 4 5)

(if (= 4 0)
  5
  (peano+ (dec 4) (inc 5)))

(peano+ (dec 4) (inc 5))
(peano+ 3 6)
(peano+ (dec 3) (inc 6))
(peano+ 2 7)
(peano+ (dec 2) (inc 7))
(peano+ 1 8)
(peano+ (dec 1) (inc 8))
(peano+ 0 9)

(if (= 0 0)
  9
  (peano+ (dec 0) (inc 9)))

9

;; Od czego zależy ilość operacji wykonywanych przez procedurę peano+?
;; Od wartości x.
;; Algorytm ma klasę złożoności O[x]
;; Od czego zależy ilość pamięci używanej przez peano+?

;; Zastosujmy OPTYMALIZACJĘ REKURENCJI KRAŃCOWEJ
(defn peano+ [x y]
  (if (= x 0)
    y
    (recur (dec x) (inc y)))) ;; Procedura jest NADAL REKURENCYJNA w sensie definicji

;; Do wykonania powyższej procedury wystarczy 1 ramka stosu (rekord aktywacji).
;; W tej ramce interesują nas pola służące do składowania wartości symboli x i y.
;; Symbole x i y nazywamy ZMIENNYMI STANU (ang. state variables).

;; Ilość pamięci używanej przez proces jest STAŁA. To oznacza, że RAM jest O[1].

;; TAKIE PROCESY, JAK POWYŻSZY NAZWAMY ITERACYJNYMI.

;; Przeanalizujmy procedurę:
(defn peano+ [x y]
  (if (= x 0)
    y
    (inc (peano+ (dec x) y)))) ;; Procedura jest REKURENCYJNA w sensie definicji

(peano+ 4 5)
(inc (peano+ (dec 4) 5))
(inc (peano+      3  5))
(inc (inc (peano+ (dec 3) 5)))
(inc (inc (inc (peano+ (dec 2) 5))))
(inc (inc (inc (inc (peano+ (dec 1) 5)))))
(inc (inc (inc (inc (peano+      0  5)))))
(inc (inc (inc (inc 5))))
(inc (inc (inc 6)))
(inc (inc 7))
(inc 8)
9

;; Algorytm powyższy jest O[x] w sensie czasu (CPU).
;; Pamięć wykorzystywana zależy od x. RAM JEST O[x].
;; Taki proces nazywamy REKURENCYJNYM.

;; Rekurencja (łac. RECURRERE - przybiec z powrotem)

;; SILNIA
(defn silnia [n]
  (if (= n 0)
    1
    (*' n (silnia (dec n)))))

;; Jak doprowadzić procedurę do postaci pozwalającej na zastosowanie
;; optymalizacji rekurencji krańcowej?

(defn factorial [n result]
  (if (= n 0)
    result

    (recur (dec n) (*' n result))))

(factorial 5 1)
(factorial (dec 5) (*' 5 1))
(factorial 4 5)
(factorial (dec 4) (*' 4 5))
(factorial 3 20)
(factorial (dec 3) (*' 3 20))
(factorial 2 60)
(factorial (dec 2) (*' 2 60))
(factorial 1 120)
(factorial (dec 1) (*' 1 120))
(factorial 0 120)
120
