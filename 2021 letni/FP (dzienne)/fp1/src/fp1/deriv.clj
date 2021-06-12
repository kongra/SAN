(ns fp1.core)

(defn atom?
  [expr]
  (not (seq? expr)))

(defn constant?
  [expr var]
  (assert (symbol? var))
  (and (atom? expr) (not= expr var)))

(defn same-var?
  [expr var]
  (assert (symbol? var))
  (= expr var))

(defn sum?
  [expr]
  (and (seq? expr) (= (first expr) '+)))

(defn make-sum
  [lhs rhs]
  ;; lhs - left-hand  side
  ;; rhs - right-hand side
  (cond (and (number? lhs) (number? rhs))
        (+ lhs rhs)

        (and (number? lhs) (= 0 lhs))
        rhs

        (and (number? rhs) (= 0 rhs))
        lhs

        :else
        (list '+ lhs rhs)))

(defn product?
  [expr]
  (and (seq? expr) (= (first expr) '*)))

(defn make-product
  [lhs rhs]
  ;; lhs - left-hand  side
  ;; rhs - right-hand side
  (cond (and (number? lhs) (number? rhs))
        (* lhs rhs)

        (and (number? lhs) (= 1 lhs))
        rhs

        (and (number? lhs) (= 0 lhs))
        0

        (and (number? rhs) (= 1 rhs))
        lhs

        (and (number? rhs) (= 0 rhs))
        0

        :else
        (list '* lhs rhs)))

;; lhs + rhs
;; lhs <op> rhs

;; (+ lhs rhs)
;; (<op> lhs rhs)

(defn L
  [expr]
  (second expr))

(defn R
  [expr]
  (nth expr 2))

(defn deriv
  [expr var]
  (cond (constant? expr var)
        0

        (same-var? expr var)
        1

        (sum? expr)
        (make-sum (deriv (L expr) var)
          (deriv (R expr) var))

        (product? expr)
        (make-sum (make-product (L expr)
                    (deriv (R expr) var))
          (make-product (deriv (L expr) var)
            (R expr)))

        ;; ... kolejne reguły
        :else (assert false)))
