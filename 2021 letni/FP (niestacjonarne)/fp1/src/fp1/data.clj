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

;; 4. ODROCZONA EWALUACJA WYRAŻEŃ

(defn long-computations
  [n]
  (println "Performing a series of some very long computations...")
  (Thread/sleep 2000) ;; hold for 2s
  (println "...done.")
  (+ n 3))

(def v1 (delay (long-computations 4)))

;; fp1.data=> (load-file "src/fp1/data.clj")
;; #'fp1.data/v1
;; fp1.data=> v1
;; #object[clojure.lang.Delay 0x5a7fa7c8 {:status :pending, :val nil}]
;; fp1.data=> (force v1)
;; Performing a series of some very long computations...
;; ...done.
;; 7
;; fp1.data=> (force v1)
;; 7
;; fp1.data=> (force v1)
;; 7
;; fp1.data=> v1
;; #object[clojure.lang.Delay 0x5a7fa7c8 {:status :ready, :val 7}]
;; fp1.data=> (+ 1 2 3 4 5)
;; 15
;; fp1.data=> (delay (+ 1 2 3 4 5))
;; #object[clojure.lang.Delay 0x1c361acb {:status :pending, :val nil}]
;; fp1.data=>
;; fp1.data=> (deref v1)
;; Performing a series of some very long computations...
;; ...done.
;; 7
;; fp1.data=> (deref v1)
;; 7
;; fp1.data=> (load-file "src/fp1/data.clj")
;; #'fp1.data/v1
;; fp1.data=> @v1
;; Performing a series of some very long computations...
;; ...done.
;; 7
;; fp1.data=> @v1
;; 7
;; fp1.data=> @v1
;; 7
;; fp1.data=> (+ 2 3 4 @v1)
;; 16
;; fp1.data=> (load-file "src/fp1/data.clj")
;; #'fp1.data/v1
;; fp1.data=>
;; fp1.data=> (delay (deref v1))
;; #object[clojure.lang.Delay 0x4a1b31a1 {:status :pending, :val nil}]
;; fp1.data=> (delay (deref v1))
;; #object[clojure.lang.Delay 0x23cbc52f {:status :pending, :val nil}]
;; fp1.data=> (delay (deref v1))
;; #object[clojure.lang.Delay 0x7ba8f8e4 {:status :pending, :val nil}]
;; fp1.data=> (delay (deref v1))
;; #object[clojure.lang.Delay 0x173305b2 {:status :pending, :val nil}]
;; fp1.data=> (delay (deref v1))
;; #object[clojure.lang.Delay 0x425b0c77 {:status :pending, :val nil}]
;; fp1.data=> (delay (deref v1))
;; #object[clojure.lang.Delay 0xdc31d5f {:status :pending, :val nil}]
;; fp1.data=> @(delay (deref v1))
;; Performing a series of some very long computations...
;; ...done.
;; 7

;; 5. KOLEKCJE ODROCZONE (LAZY)
;; fp1.data=> (first (list 0 1 2 3 4 5))
;; 0
;; first [0, -]--->[1, -]---> [2, -]---> [3, -]---> [4, -]---> [5, nil]
;; 0

;; fp1.data=> (list 0 1 2 3 4 5)
;; (0 1 2 3 4 5)
;; fp1.data=> (range 6)
;; (0 1 2 3 4 5)

;; (iterate <f> <start>) <=> (<start> (<f> <start>) (<f> (<f> <start>)) ...)

;; (iterate inc 0) <==> (0 (inc 0) (inc (inc 0)) (inc (inc (inc 0))) ...)
;; fp1.data=> (first (iterate inc 0))
;; 0

;; Czym jest wyrażenie:
;; (0 (iterate inc 1)) ?

;; a. W schemacie eager (wczesna ewaluacja):
;; [0, -]--->(iterate inc 1)
;; [0, -]--->[1, -]--->(iterate inc 2)
;; [0, -]--->[1, -]--->[2, -]--->(iterate inc 3)
;; ...
;; To oznacza pełną materializację danych i prowadzi do OutOfMemoryError

;; b. W schemacie lazy (późna ewaluacja):
;; [0, -]--->(delay (iterate inc 1))
;; [0, -]--->(delay [1, -]--->(delay (iterate inc 2)))
;; Nie ma pełnej materializacji danych

(defn my-range [n]
  (take n (iterate inc 0)))

;; SĄ ONE NIEZMIENNE (IMMUTABLE/PERSISTENT)
