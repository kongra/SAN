(defn abs
  [x]
  (if (< x 0)
    (- x)
    x))

(defn square
  [x]
  (* x x))

(defn good-enough?
  [G x]
  (<= (abs (- x (square G))) 0.00000001))

(defn avg
  [x y]
  (/ (+ x y) 2))

(defn improve
  [G x]
  (avg G (/ x G)))

(defn heron-sqrt
  [G x]
  (println "G is" G)
  (if (good-enough? G x)
    G

    ;; otherwise,
    (heron-sqrt (improve G x) x)))

(time (heron-sqrt 1 2))

(-
  (double (heron-sqrt 1 2))
  (Math/sqrt 2))

;; Definicja procedury
#_(defn <procedure-name>
    [<arg-1> <arg-2> ... <arg-n>]
    <body>)

;; Wyrażenie warunkowe
#_(if <predicate>
    <consequent>
    <alternative>)

;; JS ternary-conditional expression
;; <predicate> ? <consequent> : <alternative>;
;; np. let v1 = s1 === 'aaa' ? 2 : 3;
