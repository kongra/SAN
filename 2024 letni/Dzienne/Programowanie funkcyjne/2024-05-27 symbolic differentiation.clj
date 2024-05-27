(in-ns 'user)

(def x 10)

(println x)

(def y 5)
(println y)

(class (quote x))

'x
:abcd

#_(defn my-if
    [pred then consequent else alternative]
    (assert (= then :then))
    (assert (= else :else))
    (if pred
      consequent
      alternative))

(defmacro my-if
  [pred then consequent else alternative]
  (assert (= then :then))
  (assert (= else :else))

  `(if ~pred
     ~consequent
     ~alternative))

(my-if (zero? x)
       :then 4
       :else 5)

(def w 0)

(my-if (zero? w) :then 1 :else (/ 1 w))

;; SYMBOLIC DIFFERENTIATION
(defn atom?
  [expr]
  (or (number? expr)
      (symbol? expr)))

;; (atom? '(+ x y))
;; '(+ x y)
;; (+ x y)

(defn constant?
  [expr var-]
  (assert (symbol? var-))
  (and (atom? expr) (not (= expr var-))))

(defn same-var?
  [expr var-]
  (assert (symbol? var-))
  (= expr var-))

;; (same-var? '(+ x y) 'x)

(defn sum?
  [expr]
  (and (sequential? expr)
       (= (count expr) 3)
       (= (first expr) '+)))

;; (sum? '(+ 1 2))

(defn make-sum
  [lhs rhs]
  (assert lhs)
  (assert rhs)
  (list '+ lhs rhs))

(defn ->sum
  [lhs rhs]
  (cond
    (number? lhs)
    (cond (number? rhs)
          (+ lhs rhs)

          (zero? lhs)
          rhs

          :else (list '+ lhs rhs))

    (number? rhs)
    (cond (zero? rhs)
          lhs

          :else (list '+ lhs rhs))

    :else (list '+ lhs rhs)))

(defn product?
  [expr]
  (and (sequential? expr)
       (= (count expr) 3)
       (= (first expr) '*)))

(defn make-product
  [lhs rhs]
  (assert lhs)
  (assert rhs)
  (list '* lhs rhs))

(defn ->product
  [lhs rhs]
  (cond (number? lhs)
        (cond (number? rhs)
              (* lhs rhs)
              (= 1 lhs)
              rhs
              (zero? lhs)
              0
              :else (list '* lhs rhs))
        (number? rhs)
        (cond (= 1 rhs)
              lhs
              (zero? rhs)
              0
              :else (list '* lhs rhs))

        :else (list '* lhs rhs)))

(defn L [[_op  lhs _rhs]] lhs)
(defn R [[_op _lhs  rhs]] rhs)

(defn deriv
  [expr var-]
  (assert (symbol? var-))
  (cond
    (constant? expr var-)
    0

    (same-var? expr var-)
    1

    (sum? expr)
    (->sum (deriv (L expr) var-)
           (deriv (R expr) var-))

    (product? expr)
    (->sum (->product (L expr)
                      (deriv (R expr) var-))
           (->product (deriv (L expr) var-)
                      (R expr)))))

(deriv '(+ (* x x) (* 2 x)) 'x)

;; dy / dx = 0
;; d (2) / dx = 0

(defn foo [m n]
  (assert (nat-int? m) (str "m is " m))
  (assert (nat-int? n) (str "n is " n))

  (+ m n))

(foo 0.245 -5)

(defn goo [m n]
  {:pre [(assert (nat-int? m))
         (assert (nat-int? n))]}

  (+ m n))

(goo 0.256 -4)

(identity *assert*)
