(in-ns 'user)
(set! *warn-on-reflection* true)

(defn test-1 [n]
  (cond (= n 1) "one"
        (= n 2) "two"
        (= n 3) "three"
        :else   "the other ones"))

(defn atom?
  [expression]
  (or (number?  expression)
      (string?  expression)
      (symbol?  expression)
      (keyword? expression)))

(defn constant?
  [expression variable]
  (and (atom? expression) (not= expression variable)))

(defn same-var?
  [expression variable]
  (and (atom? expression) (= expression variable)))

;; <L> + <R>
;; 1 + 2 + 3 + 4 === (((1 + 2) + 3) + 4)

;; W Clojure (+ 1 2 3 4)
;; W naszej notacji wyrażenie sumacyjne ma postać: (+ L R)

(defn L [[_ l _]] l)
(defn R [[_ _ r]] r)

(defn binary-operator-expression?
  [operator [op l r :as expression]]
  (and (= operator op)
       (some? l)
       (some? r)
       (= (count expression) 3)))

(def sum?     (partial binary-operator-expression? '+))
(def product? (partial binary-operator-expression? '*))

(defn make-sum
  [l r]
  (cond (number? l)
        (cond (number? r)
              (+ l r)
              (zero? l)
              r
              :else (list '+ l r))

        (number? r)
        (cond (zero? r)
              l
              :else (list '+ l r))

        :else (list '+ l r)))

(defn make-product
  [l r]
  (cond (number? l)
        (cond (number? r)
              (* l r)
              (= 1 l)
              r
              (zero? l)
              0
              :else (list '* l r))
        (number? r)
        (cond (= 1 r)
              l
              (zero? r)
              0
              :else (list '* l r))
        :else (list '* l r)))

(defn deriv
  [expression variable]
  (cond (constant? expression variable)
        0

        (same-var? expression variable)
        1

        (sum? expression)
        (make-sum (deriv (L expression) variable)
                  (deriv (R expression) variable))

        (product? expression)
        (make-sum (make-product (L expression)
                                (deriv (R expression) variable))
                  (make-product (deriv (L expression) variable)
                                (R expression)))
        ;; ... TODO: kolejne reguły
        ))

(defn ->binary-operator-expression
  [expression]
  (cond (= 3 (count expression))
        (doall (seq expression))

        (< 3 (count expression))
        (let [[op & others] expression]
          (list op
                (->binary-operator-expression (cons op (butlast others)))
                (last others)))

        :else (assert false)))

(->binary-operator-expression '(+ x y z w v))
