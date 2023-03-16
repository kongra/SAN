(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

;; Lisp, LISP - Lists Processing
;; 1956, 1958

(def l1 (take 1000000 (iterate inc 1)))
(time (last l1))

;; VECTOR
(def v1 (vec l1))
(time (nth v1 54678))
(time (nth l1 54678))

(list 1 2 3 4 5 6)
(list '+ 5 6)

(vec (list 1 2 3 4 5 6))
[1 2 3 4 5 6]

(time (last v1))

(defn vec-last [v]
  (let [n (count v)]
    (when (not (zero? n))
      (nth v (dec n)))))

(time (vec-last v1))

(vec-last [])

(String/valueOf 123)

(Integer/parseInt "654265")

(java.lang.Object.)

;; MAPA
(def ages
  {"John" 24
   "Eve"  32
   "Ana"  17
   "Tom"  10})

(ages "Mark" :unknown)

(def ages1 (assoc ages "Mark" 15))
(ages1 "Mark" :unknown)

;; SET
(def boys #{"John" "Tom" "Mark"})

(boys "Eve")
