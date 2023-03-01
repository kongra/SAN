(defn one-nine->polish
  [n]
  (case n
    1 "jeden"
    2 "dwa"
    3 "trzy"
    4 "cztery"
    5 "pięć"
    6 "sześć"
    7 "siedem"
    8 "osiem"
    9 "dziewięć"

    ;; otherwise:
    nil))

(defn teen->polish
  [n]
  (case n
    11  "jedenaście"
    12  "dwanaście"
    13  "trzynaście"
    14  "czternaście"
    15  "piętnaście"
    16  "szesnaście"
    17  "siedemnaście"
    18  "osiemnaście"
    19  "dziewiętnaście"

    ;; otherwise:
    nil))

(defn tens->polish
  [n]
  (case n
    1 "dziesięć"
    2 "dwadzieścia"
    3 "trzydzieści"
    4 "czterdzieści"
    5 "pięćdziesiąt"
    6 "sześćdziesiąt"
    7 "siedemdziesiąt"
    8 "osiemdziesiąt"
    9 "dziewięćdziesiąt"

    ;; otherwise:
    nil))

(defn hundred->polish
  [n]
  (case n
    1 "sto"
    2 "dwieście"
    3 "trzysta"
    4 "czterysta"
    5 "pięćset"
    6 "sześćset"
    7 "siedemset"
    8 "osiemset"
    9 "dziewięćset"

    ;; otherwise:
    nil))

(defn thousand->polish
  [n]
  (cond
    (<  n 1) nil
    (=  n 1) "tysiąc"
    (<= n 4) (str (one-nine->polish n) " tysiące")
    (<= n 9) (str (one-nine->polish n) " tysięcy")

    :else nil))

(defn digits->long
  [d & digits]
  (Long/parseLong (apply str d digits)))

(defn long->digits
  [n]
  (->> n
    (str)
    (map digits->long)
    (vec)))

(long->digits 137625375872634876238746827634782364876N)

(defn number->polish
  [n]
  (.trim
    (str (let [[d3 d2 d1 d0] (long->digits n)]
           #_(println "Rozbicie liczby na cyfry:" d3 d2 d1 d0)
           (cond
             ;; Liczba 1-9
             (= nil d2 d1 d0)
             (one-nine->polish d3)

             ;; Liczba 10-99
             (= nil d1 d0)
             (or
               (teen->polish (digits->long d3 d2))
               (str
                 (tens->polish     d3) " "
                 (one-nine->polish d2)))

             ;; Liczba 100-999
             (= nil d0)
             (str
               (hundred->polish d3) " "
               (number->polish (digits->long d2 d1)))

             ;; 1000-9999
             :else
             (str
               (thousand->polish d3) " "
               (number->polish (digits->long d2 d1 d0))))))))

(number->polish 9876)

(defn fib-trans [[a b]]
  [b (+' a b)])

(fib-trans [0 1])
(fib-trans [1 1])
(fib-trans [1 2])
(fib-trans [2 3])
(fib-trans [3 5])
;; ...

(defn fibseq []
  (map first (iterate fib-trans [0 1])))

(take 400 (fibseq))

;; (iterate f x) => (          x
;;                          (f x)
;;                    (   f (f x))
;;                    (f (f (f x)))
;;                         ...
;;                                )

;;        coll  := (   x1     x2     x3     x4     x5  ...)
;; (map f coll) := ((f x1) (f x2) (f x3) (f x4) (f x5) ...)

(first [4 6])
(time (.length (str (last (take 100000 (fibseq))))))
