(in-ns 'user)

(def m 123)

(class m)
(+ m 1)

;; (+ n 1)

;; n! = 1 * 2 * 3 * 4 * ... * n

;; 0! = 1
;; n! = n * (n-1)! dla n > 0

;; f : R -> R
;; f x = 2x + 3

(def f
  (fn [x]
    (+ (* 2 x) 3)))

;; Ω należy do R
;; Ω = 0.00111101010101110000111
;; 1. Czy Putin najedzie na Europę?
;; 2. Czy w roku 2067 będzie koniec świata?
;; 3. Czy Bóg istnieje?
;; 4. Czy Polacy wygrali pod Grunwaldem?
;; 5.
;; ...

;; 1/3 = 0.333333333333333333333333333333...
;; π   = 3.14159.....

(defn g [x]
  (+ (* 2 x) 3))

(defn factorial [n]
  (if (zero? n)
    1

    (*' n (factorial (- n 1)))))

;; (factorial 10000)

(defn factorial-1 [n result]
  ;; (println "factorial-1" n result)
  (if (zero? n)
    result

    (recur (dec n) (*' result n))))

(factorial-1 10000 1)
;; (factorial 10000)

;; (* 1 2 3 4 5 6 7 8 9 10)
(defn factorial-2 [n]
  (apply *' (range 1 (inc n))))



























;; n! = 1 * 2 * 3 * 4 * ... * n

(defn factorial-2 [n]
  (apply *' (range 1 (inc n))))































(defn factorial-3 [n]
  (reduce *' (range 1 (inc n))))

(map factorial-3 (range 1 11))





(def l1 (list 1 2 3 4 5))
(identity l1)

(def l2 (cons 7 l1))
(identity l2)

(def v1 (vec l1))
(identity v1)

(def v2 (assoc v1 3 7))
(identity v2)

(def v4 (vec (range 1 1000000)))
(time (assoc v4 999999 -1))

(use '[criterium.core])
(quick-bench (assoc v4 999999 -1))
