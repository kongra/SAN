(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

;; (defn test-1 ^long
;;   [^String s]
;;   (.length s))

;; ;; (bench (test-1 "abcd"))
;; ;; (test-1 6542564)

;; (defn test-2 ^double
;;   [^double x]
;;   (* x 4))

;; ;; (use 'no.disassemble)
;; (println (disassemble test-1))

;; (<f> <a1> <a2> ... <an>)
;; a + b
;; (+ a b)

(def n 5)

(if (= n 7)
  "n jest równe 7"
  "n nie jest równe 7")

;; (if <predykat> <następnik> <alternatywa>)

;; TERNARY CONDITIONAL
;; n == 7 ? "n jest równe 7" : "n nie jest równe 7"

(cond (= n 7) "n jest równe 7"
      (= n 5) "n jest równe 5"
      :else   "n NIE jest równe ani 5 ani 7")

;; Prawda i fałsz w Clojure
(class false)

;; Prawda: true, not nil
;; Fałsz:  false, nil

(if 0
  "Prawda"
  "Fałsz")

(boolean true)
(boolean false)

(boolean 0)
(boolean "xyz")

(boolean nil)

(and true true true false)

(defn square [x] (* x x))

(defn good-enough? [G x]
  (< (abs (- (square G) x)) 0.000001))

(defn avg [x1 x2]
  (/ (+ x1 x2) 2))

(defn improve [G x]
  (avg G (/ x G)))

(defn sqrt [G x]
  ;; (println "sqrt" G x)
  (if (good-enough? G x)
    G
    (sqrt (improve G x) x)))

#_(sqrt 1 2)

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

;; sum-of-squares
;; (class 4)

(sum-of-squares 3 4)
(+ (square 3) (square 4))
(+ (*   3  3) (*   4  4))
(+         9         16)
25

;; Arytmetyka Peano
(defn peano+ [x y]
  (println
    "peano+" x y
    "(= x 0)" (= x 0) "(dec x)" (dec x) "(inc y)" (inc y))

  (if (= x 0)
    y
    (peano+ (dec x) (inc y))))

(inc 4)
(dec 4)
(peano+ 3 4)

(defn peano+trans [[x y]]
  [(dec x) (inc y)])

(defn peano+cond [[x _]]
  (>= x 0))

(defn peano+alternate-take [x y]
  (->> [x y]
    (iterate   peano+trans)
    (take-while peano+cond)
    (last)
    (second)))

(peano+alternate-take 7623573 4000)
