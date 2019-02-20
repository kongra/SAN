(ns clj1.core)

;; Lista jednokierunkowa
(def l1 (list 1 2 3 4 5 6))
(def l2 (cons 7 l1))

;; Mapa
(def m1
  {"janek"  100
   "marcin" 200
   "adam"   150
   "ewa"    160})

(def m2 (assoc m1 "jarek" 50))

(defn make-person
  [first-name last-name job-title]
  {:first-name first-name
   :last-name  last-name
   :job-title  job-title})

(def p1 (make-person "Bill" "Gates" "CEO"))

;; Rekordy i typy strukturalne
(defrecord Person
    [first-name
     last-name
     job-title])

(def p2 (Person. "Melinda" "Gates" "CEO's CEO"))

(deftype Employee
    [first-name
     last-name
     job-title])

(def p3 (Employee. "Melinda" "Gates" "CEO's CEO"))

;; Zbiór
(def s1 #{1 2 3 4 "aaa" "bbb"})
(conj )

;; Wektor
(def v1 [1 2 3 4 5 6])

(def l3 (range 1e6))
(def v3 (vec l3))

;; Silnia

(defn factorial
  [n]
  (if (= n 0)
    1

    (*' n (factorial (dec n)))))

(defn factorial1 ^long
  [^long n]
  (if (= n 0)
    1

    (* n (factorial1 (dec n)))))
