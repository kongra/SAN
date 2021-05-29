(ns fp1.core)

;; (make-rat <numerator> <denominator>) → CHMURA
;; (numer CHMURA) → numerator
;; (denom CHMURA) → denominator

(declare make-rat numer denom gcd as-rat rat?)

(defn rat+ [x y]
  (let [x (as-rat x)
        y (as-rat y)]

    (make-rat (+ (* (numer x) (denom y))
                (* (numer y) (denom x)))
      (* (denom x) (denom y)))))

(defn rat* [x y]
  (let [x (as-rat x)
        y (as-rat y)]

    (make-rat (* (numer x) (numer y))
      (* (denom x) (denom y)))))

(defn gcd
  [m n]
  (let [r (mod m n)]
    (if (zero? r)
      n
      (recur n r))))

;; PROPOZYCJA 1
#_(defn make-rat [x y]
    (assert (int? x))
    (assert (int? y))
    (assert (not (zero? y)))

    (let [g1 (gcd x y)
          x1 (/ x g1)
          y1 (/ y g1)]

      (cond (= x1 y1) 1
            (= y1  1) x

            :else [x1 y1])))

#_(defn numer [r]
    (first (as-rat r)))

#_(defn denom [r]
    (second (as-rat r)))

#_(defn rat?
    [r]
    (and
      (vector? r)
      (= 2      (count  r))
      (int?     (first  r))
      (pos-int? (second r))))

#_(defn as-rat
    [x]
    (cond (rat? x) x
          (int? x) [x 1]

          :else (assert false
                  (str "Illegal argument " x
                    " can't be converted into rat"))))

;; PROPOZYCJA 2
(defrecord Rat [numer denom])

(defn make-rat [x y]
  (assert (int? x))
  (assert (int? y))
  (assert (not (zero? y)))

  (let [g1 (gcd x y)
        x1 (/ x g1)
        y1 (/ y g1)]

    (cond (= x1 y1) 1
          (= y1  1) x

          :else (Rat. x1 y1))))

(defn numer [r]
  (:numer (as-rat r)))

(defn denom [r]
  (:denom (as-rat r)))

(defn as-rat
  [x]
  (cond (rat? x) x
        (int? x) (Rat. x 1)

        :else (assert false
                (str "Illegal argument " x
                  " can't be converted into rat"))))

(defn rat?
  [r]
  (instance? Rat r))

;; TODO: Improve
