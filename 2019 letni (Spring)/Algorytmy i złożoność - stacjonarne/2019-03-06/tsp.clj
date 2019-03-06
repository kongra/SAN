(defn factorial
  [n]
  (if (zero? n)
    1
    (*' n (factorial (dec n)))))

(factorial 5000)

(defn fact
  [n result]
  (if (zero? n)
    result
    (recur (dec n) (*' result n))))

(fact 5000 1)
(= (factorial 4000) (fact 400 1))

(System/nanoTime)
