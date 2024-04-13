(in-ns 'user)

(set! *warn-on-reflection*       true)
;; (set! *unchecked-math* :warn-on-boxed)

(defn square [x] (*' x x))
(square 78)

(identity Long/MAX_VALUE)

(defn pow ;; aka power
  [x ^long n]
  (assert (nat-int? n))
  (if (zero? n)
    1

    (*' x (pow x (unchecked-dec n)))))

;; (pow-1 3 7000);;
;; (unchecked-dec 3)

(defn pow-1
  ([x ^long n]
   (pow-1 1 x n))

  ([result x ^long n]
   (assert (nat-int? n))
   (if (zero? n)
     result

     (recur (*' result x) x (unchecked-dec n)))))

(reduce *' (repeat 20 3))


(defn pow-2
  [x n]
  (assert (int? n))
  (if (>= n 0)
    (reduce *' (repeat n x))

    ;; n is negative
    (/ 1 (pow-2 x (- n)))))

(pow-2 3 -5600)

(- 1.1 0.9)

(class (rationalize 1.1))

(- (rationalize 1.1)
   (rationalize 0.9))

(* 3 (/ 1 3))
(* 3 0.3333333333333333)

;; (time (pow-2 3 7000))
;; (repeat 3)

(seq (list))
(class false)

(defn my-reduce [f acc coll]
  (println "my-reduce" acc coll)

  (if-not (seq coll)
    acc

    (recur f
           (f acc (first coll))
           (rest coll))))

(my-reduce *' 1 [1 2 3 4])
(map inc [1 2 3 4 5])

(conj #{1 2 3 4} 5)

(defn my-map [f task acc coll]
  (my-reduce
   (fn [acc x]
     (task acc (f x)))

   acc coll))

(defn swap
  [f]
  (fn [x y]
    (f y x)))

(my-map inc (swap cons) '() [1 2 3 4])

(defn factorial [n]
  (assert (nat-int? n))
  (reduce *' (range 1 (inc n))))


(require
 '[clojure.string :as str]
 '[clojure.math.combinatorics :as combo])

(nth (->> [0 1 2 3 4 5 6 7 8 9]
      combo/permutations
      (map str/join)
      sort)

     999999)
