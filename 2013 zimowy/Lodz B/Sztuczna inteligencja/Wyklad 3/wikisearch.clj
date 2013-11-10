(ns wikisearch
  (:use     [kongra.core])
  (:require [kongra.search    :as SEARCH]
            [kongra.html.grab :as HTG]))


(defrecord Node [name attrs])

(defn node
  [name & attrs]
  (Node. name (apply hash-map attrs)))


(def sample-tree
  {(node 'document)  [(node 'html)]
   (node 'html)      [(node 'head) (node 'body)]
   (node 'head)      [(node 'title) (node 'meta) (node 'meta)]
   (node 'body)      [(node 'div :id 1) (node 'div :id 2)]
   (node 'div :id 1) [(node 'table) (node 'div :id 3)]
   (node 'div :id 3) [(node 'div :id 4) (node 'table :class "orange")]
   (node 'div :id 4) [(node 'a :href "url1") (node 'a :href "url2")]})

(def ROOT (node 'document))


(defn attr-matches?
  [node attr value]
  (= value ((:attrs node) attr)))


(defn tag?
  [name & attrs]
  (let [attr-map (apply hash-map attrs)]
    (fn [node]
      (and (= name (:name node))
           (every? (fn [[attr value]]
                     (attr-matches? node attr value))

                   attr-map)))))


;; 1. ZNAJDŹ TAKI ELEMENT LUB ELEMENTY div, KTÓRE ZNAJDUJĄ SIĘ BEZPOŚREDNIO POD ELEMENTEM <div id=3>

(SEARCH/tree-predpath-search ROOT sample-tree [SEARCH/... (tag? 'div :id 3) (tag? 'div)])


;; 2. ZNAJDŹ TAKI ELEMENT LUB ELEMENTY a, KTÓRE ZNAJDUJĄ SIĘ BEZPOŚREDNIO POD ELEMENTEM <div id=3>

(SEARCH/tree-predpath-search ROOT sample-tree [SEARCH/... (tag? 'div :id 3) (tag? 'a)])


;; 3. ZNAJDŹ TAKI ELEMENT LUB ELEMENTY a, KTÓRE ZNAJDUJĄ SIĘ GDZIEŚ POD ELEMENTEM <div id=3>

(SEARCH/tree-predpath-search ROOT sample-tree [SEARCH/... (tag? 'div :id 3) SEARCH/... (tag? 'a)])


;; 4. ZNAJDŹ WSZYSTKIE ELEMENTY, KTÓRE ZNAJDUJĄ SIĘ GDZIEŚ POD ELEMENTEM <div id=3>

(SEARCH/tree-predpath-search ROOT sample-tree [SEARCH/... (tag? 'div :id 3) SEARCH/...])


;; 5. ZNAJDŹ WSZYSTKIE ELEMENTY, KTÓRE ZNAJDUJĄ SIĘ BEZPOŚREDNIO POD ELEMENTEM <div id=3>

(SEARCH/tree-predpath-search ROOT sample-tree [SEARCH/... (tag? 'div :id 3) SEARCH/?])

;; 6. ZBADAJ, CZY PODANA STRONA ZAWIERA OBSZAR ODWOŁUJĄCY SIĘ DO ZASOBÓW POWIĄZANYCH.

(def HTML (->> (HTG/grab "http://en.wikipedia.org/wiki/The_Fisher_King_(film)")
               pair-second
               second))

(defn solve-6
  []
  (SEARCH/tree-predpath-search
   HTML :subnodes

   [(HTG/pred (HTML))]))