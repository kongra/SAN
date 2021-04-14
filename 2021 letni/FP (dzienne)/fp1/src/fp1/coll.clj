(ns fp1.core)

;; Czy jest możliwe przedstawienie w pamięci komputera zbioru
;; N = 0, 1, 2, 3, 4, ...

(defn foo [x]
  (println "Trwają bardzo długie obliczenia...")
  (Thread/sleep 1000) ;; 1s
  (println "...done.")
  (+ x 3))

;; (def y (foo 4))

(def y (delay (foo 4)))

(deref y)
@y

;; ODROCZONA (PÓŹNA/LENIWA) EWALUACJA WYRAŻEŃ (ang. lazy evaluation)
;; Haskell

;; (first (range 1000000000000000000000000000000))
;; 0

;; (iterate <f> <start>)
;; (<start> (<f> <start>) (<f> (<f> <start>)) ... (<f> ... (<f> <start>)) ...)

;; (iterate inc 0)
;; (0 (inc 0) (inc (inc 0)) (inc (inc (inc 0))) ... )
;; (0 1 2 3 4 ...)
