(in-ns 'user)

(defn factorial [n]
  (assert (nat-int? n))
  (if (zero? n)
    1

    (*' n (factorial (dec n)))))

;; (factorial' 10000)

(defn factorial'
  ([n]
   (factorial' n 1))

  ([n result]
   (if (zero? n)
     result

     (recur (dec n) (*' n result)))))

(map factorial' (range 10))

(def l1 (list 1 2 3 4 5))

(count l1)

(first l1)
(rest  l1)

(last  l1)

(cons 0 (rest l1))
(reduce + (map factorial (range 10)))

(defn char->long
  [c]
  (assert (instance? Character c))
  (- (long c) 48))


(factorial 10)
(time (reduce + (map char->long (str (factorial' 10000)))))

(->> 10000
     factorial'
     str
     (map char->long)
     (reduce +)
     time)
