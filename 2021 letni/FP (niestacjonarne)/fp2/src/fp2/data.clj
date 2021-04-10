(ns fp2.data)

;; (DYNAMICZNE) STRUKTURY DANYCH W CLOJURE
;; * Alokowane na stercie (JVM)
;; * Równocześnie niezmienne (ang. immutable/persistent)

;; 1. SEKWENCJE (LISTA JEDNOKIERUNKOWA, WEKTOR)
;; fp2.core=> (list "a" "b" "c")
;; ("a" "b" "c")
;; fp2.core=> (def l1 (list "a" "b" "c"))
;; #'fp2.core/l1
;; fp2.core=> l1
;; ("a" "b" "c")
;; fp2.core=> (first l1)
;; "a"
;; fp2.core=> (rest l1)
;; ("b" "c")
;; fp2.core=> (rest (rest l1))
;; ("c")
;; fp2.core=> (rest (rest (rest l1)))
;; ()
;; fp2.core=> (= nil '())
;; false
;; fp2.core=> (seq '())
;; nil
;; fp2.core=> (seq nil)
;; nil
;; fp2.core=> (= (seq '()) (seq nil))
;; true
;; fp2.core=> (next l1)
;; ("b" "c")
;; fp2.core=> (next (next l1))
;; ("c")
;; fp2.core=> (next (next (next l1)))
;; nil
;; fp2.core=> (cons "x" l1)
;; ("x" "a" "b" "c")
;; fp2.core=> l1
;; ("a" "b" "c")
;; fp2.core=> (def l2 (cons "x" l1))
;; #'fp2.core/l2

;; Przykład 1.
;; Napisz procedurę, która zwraca ostatni element listy:
;; (get-last (list 'a 'b 'c)) => 'c
;; (get-last (list 'a 'b))    => 'b
;; (get-last (list))          => nil

(defn get-last
  [l result]
  (if-not (seq l)
    ;; Gdy lista jest pusta, zwróć
    result

    ;; w przeciwnym wypadku
    (recur (rest l) (first l))))

(def l3 (range 1000000))

;; Zadanie 4.
;; Napisz procedurę find-index, która działa w sposób następujący
;; (find-index (list "a" "b" "c") "c") => 2
;; (find-index (list "a" "b" "c") "b") => 1
;; (find-index (list "a" "b" "c") "a") => 0

;; (find-index (list "a" "b" "c") "d") => nil

;; (find-index (list) "c") => nil
;; (find-index (list) "b") => nil
;; (find-index (list) "a") => nil

;; 2. ZBIORY
;; 3. MAPY (POJEMNIKI ASOCJACYJNE)
