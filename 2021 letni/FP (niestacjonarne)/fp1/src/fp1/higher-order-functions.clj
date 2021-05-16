;; PROCEDURY WYŻSZEGO RZĘDU
;; 1. Σ i=a..b . i
(defn sum-ints
  [a b]
  (if (> a b)
    0
    (+ a
      (sum-ints (inc a) b))))

;; 2. Σ i=a..b . i^2
(defn square [x] (* x x))

(defn sum-squares
  [a b]
  (if (> a b)
    0
    (+ (square a)
      (sum-squares (inc a) b))))

;; POWYŻSZE PROGRAMY SĄ NIEMAL IDENTYCZNE
;; PISANIE WIELOKROTNE PROCEDUR, W KTÓRYCH POWTARZA SIĘ PEWNA IDEA, JEST BARDZO
;; NIEKORZYSTNE I TO NIE Z POWODU KONIECZNOŚCI PRZEPISYWANIA (OSTATECZNIE MAMY
;; CTRL-C, CTRL-V).
;; W PRZYPADKU ZŁOŻONYCH SYSTEMÓW I PROCESÓW CHODZI O TO, BY ROZBIJAĆ TĘ
;; ZŁOŻONOŚĆ NA WIELE OSOBNYCH ELEMENTÓW POJĘCIOWO RÓŻNYCH
;; (ORTOGONALNYCH), A SPEŁNIAJĄCYCH KONKRETNE I NIEPOWTARZALNE FUNKCJE.
;; DIVIDE AND CONQUER
;; ZMIANA JEDNEJ IDEI/POMYSŁU, JEGO DEBUGGOWANIE, JEST LOKALNA I NIE
;; POWODUJE KOLIZJI Z INNYMI.
;; TO JEST KLUCZ DO ROUMIENIA I DO KONTROLOWANIA.
;; 3. Σ i=a..b by 4. 1 / (i * (i + 2))
(defn pi-sum
  [a b]
  (if (> a b)
    0
    (+ (/ 1 (* a (+ a 2)))
      (pi-sum (+ a 4) b))))

;; 4. WSPÓLNY WZORZEC
;; (defn <name>
;;   [a b]
;;   (if (> a b)
;;     0
;;     (+ (<term> a)
;;       (<name> (<next> a) b))))

;; 5. I UOGÓLNIENIE
(defn sum
  [term a next b]
  (if (> a b)
    0
    (+ (term a)
      (sum term (next a) next b))))

;; 6. TERAZ MAMY
(defn sum-int
  [a b]
  (sum identity a inc b))

(defn sum-squares
  [a b]
  (sum square a inc b))

(defn pi-sum
  [a b]
  (sum (fn [i] (/ 1 (* i (+ i 2))))
    a
    (fn [x] (+ x 4))
    b))
