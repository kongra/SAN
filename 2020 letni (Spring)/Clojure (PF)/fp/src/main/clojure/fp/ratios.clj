(defn gcd
  [m n]
  (let [r (mod m n)]
    (if (zero? r)
      n
      (recur n r))))

(defrecord Ratio [numer denom])

(defn make-ratio
  [n d]
  (let [a (gcd n d)
        n (/ n a)
        d (/ d a)]

    (if (= n d)
      1

      (Ratio. n d))))

(defn numer [r]
  (if (integer? r)
    r

    (:numer  r)))

(defn denom [r]
  (if (integer? r)
    1

    (:denom r)))

;; ---------------------------------------
(defn ratio+ [x y]
  (make-ratio
    (+
      (* (numer x) (denom y))
      (* (numer y) (denom x)))

    (* (denom x) (denom y))))

(defn ratio* [x y]
  (make-ratio
    (* (numer x) (numer y))
    (* (denom x) (denom y))))

;; --------------------------------------
(def r1 (make-ratio 1 2))
(def r2 (make-ratio 3 6))
(ratio+ r1 r2)

(defn test-1
  []
  (let [r1 (make-ratio 1     2)
        r2 (make-ratio 300 600)]

    (ratio+ r1 r2)))

;; (use 'criterium.core)
;; (quick-bench (test-1))

(defn my-cons [x y]
  (fn [dispatch]
    (if (= dispatch 1)
      x
      y)))

(defn my-first [p] (p 1))
(defn my-rest  [p] (p 2))

(my-first (my-cons :a :b)) ;; => :a
(my-rest  (my-cons :a :b)) ;; => :b
