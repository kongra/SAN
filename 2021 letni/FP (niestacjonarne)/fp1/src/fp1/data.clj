(ns fp1.data)

;; STRUKTURY DANYCH W CLOJURE
;; 1. SEKWENCJE (LISTA, WEKTOR)
;; fp1.core=> (def l1 (list "a" "b" "c"))
;; #'fp1.core/l1
;; fp1.core=> l1
;; ("a" "b" "c")
;; fp1.core=> (first l1)
;; "a"
;; fp1.core=> (rest l1)
;; ("b" "c")
;; fp1.core=> (rest (rest l1))
;; ("c")
;; fp1.core=> (rest (rest (rest l1)))
;; ()
;; fp1.core=> (rest (rest (rest (rest l1))))
;; ()
;; fp1.core=> (rest (rest (rest (rest (rest l1)))))
;; ()
;; fp1.core=> (first (list))
;; nil
;; fp1.core=> (next l1)
;; ("b" "c")
;; fp1.core=> (next (next l1))
;; ("c")
;; fp1.core=> (next (next (next l1)))
;; nil
;; fp1.core=> (seq (list))
;; nil
;; fp1.core=> (seq nil)
;; nil
;; fp1.core=>
;; fp1.core=> (cons "x" l1)
;; ("x" "a" "b" "c")
;; fp1.core=> l1
;; ("a" "b" "c")
;; fp1.core=> (def l2 (cons "x" l1))
;; #'fp1.core/l2
;; fp1.core=> l2
;; ("x" "a" "b" "c")
;; fp1.core=> l1
;; ("a" "b" "c")

;; Przykład. Napisz procedurę zwracającą ostatni element danej listy
;; (get-last (list "a" "b" "c")) => "c"
;; (get-last (list)) => nil

(defn get-last'
  ([l result]
   (if-not (seq l)
     result

     (recur (rest l) (first l)))))

(defn get-last
  [l]
  (loop [l (seq l)
         result nil]
    (if-not l
      result
      (recur (next l) (first l)))))

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

;; SĄ ONE NIEZMIENNE (IMMUTABLE/PERSISTENT)
