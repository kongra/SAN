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
;; * Zbiór nie zawiera powtórzeń elementów.
;; * Uzyskanie informacji o przynależności obiektu do zbioru realizowane jest ze
;;   złożonością O(1) lub O(log(n)).
;; * Zbiór nie utrzymuje porządku (kolejności) elementów(**)

;; ** - są zbiory utrzymujące porządek, np. tree-set

;; fp2.core=> (hash-set "a" "b" "c" "d")
;; #{"d" "a" "b" "c"}
;; fp2.core=> (def s1 (hash-set "a" "b" "c" "d" "a" "d" "c"))
;; #'fp2.core/s1
;; fp2.core=> s1
;; #{"d" "a" "b" "c"}
;; fp2.core=> (contains? s1 "a")
;; true
;; fp2.core=> (contains? s1 "x")
;; false
;; fp2.core=> (s1 "a")
;; "a"
;; fp2.core=> (s1 "x")
;; nil
;; fp2.core=>
;; fp2.core=> (class s1)
;; clojure.lang.PersistentHashSet
;; fp2.core=> (def s2 (conj s1 "x"))
;; #'fp2.core/s2
;; fp2.core=> s2
;; #{"d" "x" "a" "b" "c"}
;; fp2.core=> s1
;; #{"d" "a" "b" "c"}
;; fp2.core=> (def s2 (disj s1 "a"))
;; #'fp2.core/s2
;; fp2.core=> s2
;; #{"d" "b" "c"}
;; fp2.core=> (clojure.set/difference s2 s1)
;; #{}
;; fp2.core=> (clojure.set/difference s1 s2)
;; #{"a"}
;; fp2.core=> (clojure.set/intersection s1 s2)
;; #{"d" "b" "c"}
;; fp2.core=> (clojure.set/union s1 s2)
;; #{"d" "a" "b" "c"}
;; fp2.core=> (clojure.set/subset? s1 s2)
;; false
;; fp2.core=> (clojure.set/subset? s2 s1)
;; true

;; Zadanie 5. proszę zrealizować procecurę unique-seq, która ma następujące działanie:
;; (unique-seq [1 2 3 4 1 1 2 2 7 7 3 2 1]) => (1 2 3 4 7)
;; Innymi słowy, procedura ta utrzymuje kolejność elementów w oryginalnej sekwencji,
;; ale eliminuje duplikaty.
;; Hint: wykorzystać hash-set.

;; 3. MAPY (POJEMNIKI ASOCJACYJNE)
;; Są to kolekcje, w których występuje skojarzenie kluczy oraz wartości.
;; String[] tab = {"a", "b", "c"};
;; 0 -> "a"
;; 1 -> "b"
;; 2 -> "c"

;; "a" -> 0
;; "b" -> 1
;; "c" -> 2
;; fp2.core=> (hash-map "a" 0, "b" 1, "c" 2)
;; {"a" 0, "b" 1, "c" 2}
;; fp2.core=> (def m1 (hash-map "a" 0, "b" 1, "c" 2))
;; #'fp2.core/m1
;; fp2.core=> (get m1 "a")
;; 0
;; fp2.core=> (get m1 "b")
;; 1
;; fp2.core=> (get m1 "c")
;; 2
;; fp2.core=> (get m1 "x")
;; nil
;; fp2.core=> (get m1 "x" "wartość domyślna")
;; "wartość domyślna"
;; fp2.core=> (m1 "a")
;; 0
;; fp2.core=> (m1 "b")
;; 1
;; fp2.core=> (m1 "c")
;; 2
;; fp2.core=> (m1 "x")
;; nil
;; fp2.core=> (class m1)
;; clojure.lang.PersistentHashMap
;; fp2.core=> (def m2 (assoc m1 "x" 10))
;; #'fp2.core/m2
;; fp2.core=> m2
;; {"x" 10, "a" 0, "b" 1, "c" 2}
;; fp2.core=> m1
;; {"a" 0, "b" 1, "c" 2}
;; fp2.core=> (def m3 (dissoc m1 "a"))
;; #'fp2.core/m3
;; fp2.core=> m3
;; {"b" 1, "c" 2}
;; fp2.core=> (merge {"a" 1 "b" 2} {"c" 3})
;; {"a" 1, "b" 2, "c" 3}
;; fp2.core=> (merge {"a" 1 "b" 2} {"c" 3 "b" 10})
;; {"a" 1, "b" 10, "c" 3}
