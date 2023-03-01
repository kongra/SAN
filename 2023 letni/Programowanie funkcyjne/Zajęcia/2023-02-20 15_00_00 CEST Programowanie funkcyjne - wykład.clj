;; * Imperatywny wzór Herona jest ŁATWY (EASY).
;; * Deklaratywna realizacja pierwiastka kwadratowego
;;   z wykorzystaniem fixed-point jest PROSTA (SIMPLE).
;; * SIMPLE != EASY - przynajmniej w wielu ciekawych
;;   sytuacjach.
;; * INTERESUJE NAS SIMPLE, a nie EASY!!!

;; REPL - Read-Eval-Print Loop

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
