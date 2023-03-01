(def x1 5)

(+ x1 4)
(* x1 7)

(class 4)
(class 4.356)
(class true)
(class false)
(class "abcd")
(class \a)

((fn [x] (+ x 7)) 8)

((fn [x y z] (+ x y z 1)) 1 2 3)

(def f1 (fn [x] (+ x 7)))
(f1 8)

(defn f2
  [x y z]
  (+ x y z 1))

(f2 1 2 3)

;; Zadanie. Znajdź sumę cyfr liczby n!
;; https://projecteuler.net/problem=20

(if (= 5 x1)
  "x1 jest równe 5"
  "x1 nie jest równe 5")

(defn factorial [n]
  (assert (nat-int? n))
  (if (= n 0)
    1

    (*' n (factorial (- n 1)))))

;; var s = x1 == 5 ? "..." : "---";

;; (factorial -10)
(factorial 0)
(factorial 1)
(factorial 2)
(factorial 3)
(factorial 4)
(factorial 5)
(map factorial (range 0 10))

(count (str (factorial 100)))

(defn factorial [n]
  (assert (nat-int? n))
  (if (= n 0)
    1

    (*' n (factorial (- n 1)))))

(defn factorial' [n]
  (assert (nat-int? n))
  (reduce *' 1 (range 1 (inc n))))

;;               coll  := (x1 x2 x3 ... xn)
;; (reduce f val coll) := (f ... (f (f (f val x1) x2) x3) ... xn)

(count (str (factorial' 100)))

(.length (str (factorial' 100000)))
(.length (str (factorial  100000)))
