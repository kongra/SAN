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

(defn make-sum
  [lhs rhs]
  (list '+ lhs rhs))

(defn product?
  [expr]
  (and (seq? expr) (= (first expr) '*)))

(defn make-product
  [lhs rhs]
  (list '* lhs rhs))

(defn L
  [expr]
  (second expr))

(defn R
  [expr]
  (nth expr 2))

(L '(+ x y))
(R '(+ x y))

(defn deriv*
  [expr var]
  (cond (constant? expr var)
        0
        (same-var? expr var)
        1
        (sum? expr)
        (make-sum (deriv* (L expr) var)
                  (deriv* (R expr) var))
        (product? expr)
        (make-sum (make-product (L expr)
                                (deriv* (R expr) var))
                  (make-product (deriv* (L expr) var)
                                (R expr)))

        :else (assert false (str "Illegal expression " expr))))

(defmacro deriv
  [expr var]
  `(fn [~var]
     ~(deriv* expr var)))

(deriv* '(* 2 (* x x)) 'x)
(def f (deriv (* 2 (* x x)) x))
(f 2)

(use 'no.disassemble)

(println (disassemble f))
