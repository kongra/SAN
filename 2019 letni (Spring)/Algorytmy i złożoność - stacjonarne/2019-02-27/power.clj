(defn pow
  [a n]
  (if (zero? n)
    1
    (*' a (pow a (dec n)))))

(defn pow1
  [a n result]
  (if (zero? n)
    result

    (recur a (dec n) (*' a result))))

(use 'criterium.core)
(quick-bench (pow 2 100))
(time (pow1 2 100000 1))

(defn square [x] (*' x x))

(defn pow3
  [a n]
  (cond (zero? n) 1
        (even? n) (square (pow3 a (/ n 2)))
        :else     (*' a (pow3 a (dec n)))))

#_ (map #(pow 2 %) [0 1 2 3 4 5 6 7])

(quick-bench (pow1 2 1024 1))

(quick-bench (pow3 2 100000))
