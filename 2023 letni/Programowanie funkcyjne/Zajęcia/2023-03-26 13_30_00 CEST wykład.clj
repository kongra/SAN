(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

;; Arytmetyka Peano
(defn peano+ ^long
  [^long x ^long y]
  (if (= x 0)
    y
    (recur (dec x) (inc y))))

;;      x y
(peano+ 3 4)
(peano+ 2 5)
(peano+ 1 6)
(peano+ 0 7)
7

;; (use 'no.disassemble)
;; (println (disassemble peano+))
;; (bench (peano+ 300000 4))

(defn peano++ ^long
  [^long x ^long y]
  (if (= x 0)
    y
    (inc (peano++ (dec x) y))))

(defn silnia [n]
  (if (= n 0)
    1
    (*' n (silnia (dec n)))))

(silnia 5)
(* 5 (silnia 4))
(* 5 (* 4 (silnia 3)))
(* 5 (* 4 (* 3 (silnia 2))))
(* 5 (* 4 (* 3 (* 2 (silnia 1)))))
(* 5 (* 4 (* 3 (* 2 (* 1 (silnia 0))))))
(* 5 (* 4 (* 3 (* 2 (* 1 1)))))
(* 5 (* 4 (* 3 (* 2 1))))
(* 5 (* 4 (* 3 2)))
(* 5 (* 4 6))
(* 5 24)
120

(silnia 30000)

(defn silnia'
  [n wartość]
  (if (= n 0)
    wartość
    (recur (dec n) (*' wartość n))))

(silnia' 5 1)
(silnia' 4 (*' 1 5))
(silnia' 3 (*' (*' 1 5) 4))
(silnia' 2 (*' (*' (*' 1 5) 4) 3))
(silnia' 1 (*' (*' (*' (*' 1 5) 4) 3) 2))
(silnia' 1 (*' (*' (*' (*' (*' 1 5) 4) 3) 2) 1))
(silnia' 0 (*' (*' (*' (*' (*' 1 5) 4) 3) 2) 1))
(*' 120 1)
120

(defn factorial [n]
  (reduce *' (range 1 (inc n))))

(def n 5)
(range 1 (inc n))

;; (reduce f          start [e1   e2   e3 e4 ...])
;; (reduce f       (f start  e1) [e2   e3 e4 ...])
;; (reduce f    (f (f start  e1)  e2) [e3   e4 ...])
;; (reduce f (f (f (f start  e1)  e2)  e3) [e4 ...]

;; (reduce *       1  [1 2 3 4 5])
;; (reduce *    (* 1   1) [2 3 4 5])
;; (reduce * (* (* 1   1)  2) [3 4 5]

(reduce * [1 2 3 4 5])

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

;; (map fib (range 20))
(time (fib 40))

(defn reduce'
  [f start coll]
  (println "reduce f " start coll)
  (if-not (seq coll)
    start

    (recur f (f start (first coll)) (rest coll))))

(reduce' + 0 [1 2 3 4 5])
