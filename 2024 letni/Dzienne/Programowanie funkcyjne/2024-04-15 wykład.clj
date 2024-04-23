(in-ns 'user)

;; (reduce <f> <val> <coll>)

(reduce * [1 2 3 4 5])

(first [1 2 3 4 5])
(rest  [1 2 3 4 5])

;; f : [acc x] -> acc

(defn my-reduce
  ([f coll]
   (my-reduce f (first coll) (rest coll)))

  ([f acc coll]
   (if-not (seq coll)
     acc

     (recur f (f acc (first coll)) (rest coll)))))

(defn my-map
  [f coll]
  (my-reduce (fn [acc x] (conj acc (f x))) [] coll))

(my-map inc [1 2 3 4 5])
