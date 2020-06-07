(defn atom?
  [expr]
  (not (seq? expr)))

(defn constant?
  [expr var]
  (and (atom? expr) (not (= expr var))))

(defn same-var?
  [expr var]
  (and (atom? expr) (= expr var)))

(defn sum?
  [expr]
  (and (seq? expr) (= (first expr) '+)))

(defn product?
  [expr]
  (and (seq? expr) (= (first expr) '*)))

(defn L
  [expr] ;; expr jest postaci (op L R)
  (second expr))

(defn R
  [expr] ;; expr jest postaci (op L R)
  (nth expr 2))

(defn make-sum
  [L R]
  (cond (number? L)
        (cond (number? R) (+ L R)
              (zero?   L) R

              :else (list '+ L R))

        (number? R)
        (cond (zero? R) L

              :else (list '+ L R))

        :else (list '+ L R)))

(defn make-product
  [L R]
  (cond (number? L)
        (cond (number? R) (* L R)
              (= 1   L) R
              (zero? L) 0

              :else (list '* L R))

        (number? R)
        (cond (= 1   R) L
              (zero? R) 0

              :else (list '* L R))

        :else (list '* L R)))

(defn deriv
  [expr var]
  (cond
    (constant? expr var) ;; d(C) / dx = 0
    0

    (same-var? expr var) ;; d(x) / dx = 1
    1

    (sum? expr) ;; d(e1 + e2) / dx = d(e1) / dx + d(e2) / dx
    (make-sum
      (deriv (L expr) var)
      (deriv (R expr) var))

    (product? expr) ;; d(e1 * e2) = e1 * d(e2) / dx + d(e1) / dx * e2
    (make-sum
      (make-product
        (L expr)
        (deriv (R expr) var))

      (make-product
        (deriv (L expr) var)
        (R expr)))

    ;; ... kolejne reguły
    ))

(deriv '(* 2 x) 'x)

(def e1 '(+ (* a (* x x))
           (+ (* b x)
             c)))

(deriv e1 'x)
(deriv e1 'a)
(deriv e1 'b)
(deriv e1 'c)

;; (class (quote x))
;; 'x
;; (def x 2)
;; (+ x 3)
;; (class '(+ x 3))
;; (list '+ 'x 3)
