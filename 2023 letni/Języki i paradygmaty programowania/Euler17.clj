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

#_(number->polish 5117)
