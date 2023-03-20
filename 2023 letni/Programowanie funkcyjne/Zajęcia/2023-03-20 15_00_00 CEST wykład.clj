(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

;; Arytmetyka Peano
(defn peano+ ^long
  [^long x ^long y]
  (if (= x 0)
    y
    (recur (dec x) (inc y))))

;; (use 'no.disassemble)
;; (println (disassemble peano+))

;; (inc 4)
;; (dec 4)

(time (peano+ 30000000 400))

;; (defn peano+trans [[x y]]
;;   [(dec x) (inc y)])

;; (defn peano+cond [[x _]]
;;   (>= x 0))

;; (defn peano+alternate-take [x y]
;;   (->> [x y]
;;     (iterate   peano+trans)
;;     (take-while peano+cond)
;;     (last)
;;     (second)))

;; (peano+alternate-take 7623573 4000)

(defn peano++ ^long
  [^long x ^long y]
  (long
    (if (= x 0)
      y
      (inc (peano++ (dec x) y)))))


(defn silnia
  [^long n wartość]
  (if (= n 0)
    wartość
    (recur (dec n) (*' wartość n))))

#_(println (disassemble silnia))
#_(silnia 3000 1)

(defn silnia' [n]
  (reduce *' (range 1 (inc (long n)))))

#_(time (silnia' 20000))

(reduce *' (range 1 (inc 100)))

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(map fib (range 20))

(time (fib 50))
