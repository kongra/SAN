(in-ns 'user)

(defn square [x]
  (* x x))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(sum-of-squares 3 4)

123
"abcd"
:www

(class (quote xyz))
(class 'xyz)
(class (symbol "xyz"))

((fn [x] (+ x 3)) 5)

(def PI 3.14159)

;;              x y
(sum-of-squares 3 4)
(+ (square 3) (square 4))
(+ (* 3 3) (* 4 4))
(+ 9 16)
25

;; i++

0
(inc 0)
(inc (inc 0))
(inc (inc (inc 0)))
(inc (inc (inc (inc 0))))

(take 100 (iterate inc 0))

(defn p+ [x y]
  (println "p+" x y)
  (if (= x 0)
    y
    (p+ (dec x) (inc y))))

;;  x y
(p+ 3 4)

#_(if (= 3 0)
    4
    (p+ (dec 3) (inc 4)))

#_(p+ (dec 3) (inc 4))

(p+ 2 5)
(p+ 1 6)
(p+ 0 7)
7

(p+ 5 40)

(defn p+ [x y]
  (if (= x 0)
    y
    (inc (p+ (dec x) y))))

(p+ 3 4)

(defn silnia [n wartość]
  (if (= n 0)
    wartość
    (silnia (dec n) (* wartość n))))

(use 'no.disassemble)
(println (disassemble silnia))

(defn sum-ints
  [a b]
  (if (> a b)
    0
    (+ a
       (sum-ints (inc a) b))))

(sum-ints 0 10)
