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
#_(if- (zero? x) :nothing (/ 1 x))

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

;; Ćwiczenie.
;; https://projecteuler.net/problem=8

(def NUM-8
  "73167176531330624919225119674426574742355349194934
   96983520312774506326239578318016984801869478851843
   85861560789112949495459501737958331952853208805511
   12540698747158523863050715693290963295227443043557
   66896648950445244523161731856403098711121722383113
   62229893423380308135336276614282806444486645238749
   30358907296290491560440772390713810515859307960866
   70172427121883998797908792274921901699720888093776
   65727333001053367881220235421809751254540594752243
   52584907711670556013604839586446706324415722155397
   53697817977846174064955149290862569321978468622482
   83972241375657056057490261407972968652414535100474
   82166370484403199890008895243450658541227588666881
   16427171479924442928230863465674813919123162824586
   17866458359124566529476545682848912883142607690042
   24219022671055626321111109370544217506941658960408
   07198403850962455444362981230987879927244284909188
   84580156166097919133875499200524063689912560717606
   05886116467109405077541002256983155200055935729725
   71636269561882670428252483600823257530420752963450")

(defn digit?
  [c]
  (#{\0 \1 \2 \3 \4 \5 \6 \7 \8 \9} c))

(defn digit->long
  [c]
  (parse-long (str c)))

(def NUM-9 (map digit->long (filter digit? NUM-8)))

(defn part-*
  [p]
  (reduce * p))

(defn Euler-8
  [size]
  (apply max-key part-* (partition size 1 NUM-9)))

(time (Euler-8 13))

(defn Euler-8'
  [size]
  (->> NUM-8
    (filter digit?)
    (map digit->long)
    (partition size 1)
    (apply max-key part-*)))

(time (Euler-8' 13))

;; Zadanie 4.
;; https://projecteuler.net/problem=12

;; SILNIA
#_(defn silnia [n]
    (if (= n 0)
      1
      (*' n (silnia (dec n)))))

(defn silnia [n wartość]
  (if (= n 0)
    wartość
    (recur (dec n) (*' wartość n))))

;; FIBONACCI
(defn fib ^long
  [^long n]
  (if (< n 2)
    n
    (+ (fib (- n 1))
       (fib (- n 2)))))

(defn fib-gen [[a b]]
  [b (+' a b)])

(defn fib-opt-1
  []
  (->> [0 1]
    (iterate fib-gen)
    (map first)))

(defn fib-opt-2
  [n a b]
  (if (zero? n)
    a

    (recur (dec n) b (+' a b))))
