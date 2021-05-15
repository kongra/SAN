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

;; ZBIÓR
;; * Służy do określenia przynależności elementu(ów) do pewnej grupy.
;; * Dany element w zbiorze występuje dokładnie 1 raz. Jest więc gwarantem unikalności.
;; * Odpytywanie zbioru o przynależność elementu do niego zazwyczaj ma złożoność O(1) lub O(log(n)).
;; * Kolejność elementów nie gra roli(**)

;; ** Chyba, że mamy do czynienia z pojemnikiem typu sorted-set
;; fp1.core=> (hash-set "a" "b" "c" "d")
;; #{"d" "a" "b" "c"}
;; fp1.core=> (hash-set "a" "b" "c" "d" "a" "a" "c")
;; #{"d" "a" "b" "c"}
;; fp1.core=> (def s1 (hash-set "a" "b" "c" "d"))
;; #'fp1.core/s1
;; fp1.core=> (contains? s1 "a")
;; true
;; fp1.core=> (contains? s1 "x")
;; false
;; fp1.core=> (s1 "a")
;; "a"
;; fp1.core=> (s1 "x")
;; nil

;; fp1.core=> (class s1)
;; clojure.lang.PersistentHashSet
;; fp1.core=> (conj s1 "x")
;; #{"d" "x" "a" "b" "c"}
;; fp1.core=> (def s2 (conj s1 "x"))
;; #'fp1.core/s2
;; fp1.core=> s2
;; #{"d" "x" "a" "b" "c"}
;; fp1.core=> s1
;; #{"d" "a" "b" "c"}
;; fp1.core=> (def s3 (disj s1 "a"))
;; #'fp1.core/s3
;; fp1.core=> s3
;; #{"d" "b" "c"}
;; fp1.core=> s1
;; #{"d" "a" "b" "c"}
;; fp1.core=> s2
;; #{"d" "x" "a" "b" "c"}
;; fp1.core=> s3
;; #{"d" "b" "c"}
;; fp1.core=> (clojure.set/difference s2 s1)
;; #{"x"}
;; fp1.core=> (clojure.set/union s1 s3)
;; #{"d" "a" "b" "c"}
;; fp1.core=> (clojure.set/intersection s2 s1)
;; #{"d" "a" "b" "c"}
;; fp1.core=> (clojure.set/intersection s2 s3)
;; #{"d" "b" "c"}
;; fp1.core=>

;; Zadanie 5. Zrealizuj procedurę unique-seq, która działa, jak poniżej:
;; (unique-seq [1 2 3 4 1 1 4 4 2 3 7]) => (1 2 3 4 7)
;; czyli zwraca sekwencję pozbawioną duplikatów, kolejność jest zachowana.
;; Hint: wykorzystaj zbiór.

;; MAPA (POJEMNIK ASOCJACYJNY)
;; Jest to pojemnik, który łączy ze sobą klucze i odpowiadające im wartości.
;; W przypadku tablicy mamy klucze będące kolejnymi liczbami naturalnymi.
;; String[] tab = {"a", "b", "c"}
;; 0 -> "a"
;; 1 -> "b"
;; 2 -> "c"

;; Załóżmy, że chcemy zbudować pojemnik będący odwrotnością powyższego
;; "a" -> 0
;; "b" -> 1
;; "c" -> 2

;; fp1.core=> (hash-map "a" 0 "b" 1 "c" 2)
;; {"a" 0, "b" 1, "c" 2}
;; fp1.core=> (def m1 (hash-map "a" 0 "b" 1 "c" 2))
;; #'fp1.core/m1
;; fp1.core=> m1
;; {"a" 0, "b" 1, "c" 2}
;; fp1.core=> (get m1 "b")
;; 1
;; fp1.core=> (get m1 "x")
;; nil
;; fp1.core=> (get m1 "x" "wartość domyślna")
;; "wartość domyślna"
;; fp1.core=> (m1 "b")
;; 1
;; fp1.core=> (m1 "x")
;; nil
;; fp1.core=> (def v1 ["a" "b" "c" "d"])
;; #'fp1.core/v1
;; fp1.core=> (v1 0)
;; "a"
;; fp1.core=> (v1 1)
;; "b"
;; fp1.core=> (v1 2)
;; "c"
;; fp1.core=> (v1 3)
;; "d"
;; fp1.core=> (v1 5)
;; Execution error (IndexOutOfBoundsException) at fp1.core/eval2067 (form-init16399973377660957740.clj:1).
;; null

;; fp1.core=> (class m1)
;; clojure.lang.PersistentHashMap
;; fp1.core=> (def m2 (assoc m1 "x" 10))
;; #'fp1.core/m2
;; fp1.core=> m2
;; {"x" 10, "a" 0, "b" 1, "c" 2}
;; fp1.core=> m1
;; {"a" 0, "b" 1, "c" 2}
;; fp1.core=> (def m3 (dissoc m1 "a"))
;; #'fp1.core/m3
;; fp1.core=> m3
;; {"b" 1, "c" 2}
;; fp1.core=> m1
;; {"a" 0, "b" 1, "c" 2}
