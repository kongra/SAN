(def x 3)

(def f (fn [x] (* x 3)))

(defn f1
  [x]
  (println "Działa funkcja f1")
  (* x 3))

(f 7)

(f1 7)

(fn [x]
  (* x 3))
