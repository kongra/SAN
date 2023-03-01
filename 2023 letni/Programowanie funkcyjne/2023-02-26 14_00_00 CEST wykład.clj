;; r = 23cm
;; P = r * r * 3.14159

(* 23 23 3.14159)

;; <wyrażenie> => <wartość> (=> - ma-wartość)

(def pi 3.14159) ;; def - define
(* 23 23 pi)

;; Python: 1 + 2
;; Lisp:   (+ 1 2)

(def area-of-cricle
  (fn [r]
    (* pi r r)))

;; Rachunek λ (lambda)

;; (λ r. π * r * r) 23
;; π * 23 * 23 => ...

((fn [r] (* pi r r)) 23)

(area-of-cricle 23)

(defn circle-area [r]
  (* pi r r))

(circle-area 23)

(defn factorial
  [n]
  (if (zero? n)
    1

    (*' n (factorial (dec n)))))

(factorial 100000)

;; n! = 1 * 2 * 3 * 4 * 5 ... * n

(defn factorial' [n result]
  (if (zero? n)
    result

    (recur (dec n) (*' n result))))

#_(.length (str (factorial' 100000 1)))
