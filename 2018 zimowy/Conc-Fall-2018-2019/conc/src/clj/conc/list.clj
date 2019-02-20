(ns conc.list)

(defrecord List
    [listHead
     listTail
     ])

(defrecord Node
    [nodePrv
     nodeNxt
     nodeVal
     ])

(defn makeList
  []
  (List. (ref nil) (ref nil)))

(defn addFirst!
  [lst val]
  (dosync
   (let [listHead @(:listHead lst)
         listTail @(:listTail lst)
         node (Node. (ref nil) (ref listHead) (ref val))]

     (ref-set (:listHead lst) node)
     (when (nil? @(:listTail lst))
       (ref-set (:listTail lst) node))

     lst)))

(defn log!
  [msg]
  (io! (println msg)))

(defn getFirst
  [lst]
  (log! "getFirst running ...")
  (dosync
   (when-let [listHead (ensure (:listHead lst))]
     (ensure (:nodeVal listHead)))))

(def l1 (makeList))
(dosync
 (-> l1
     (addFirst! "pierwszy")
     (addFirst! "drugi")))

(defn mapList
  [f lst]
  (->> lst :listHead deref
       (iterate #(deref (:nodeNxt %)))
       (take-while identity)
       (map     #(f (deref (:nodeVal %))))))

(future
  (dotimes [i 1000]
    (Thread/sleep 1)
    (addFirst! l1 (str "element-" i)))

  (println "done."))

(println (getFirst l1))
