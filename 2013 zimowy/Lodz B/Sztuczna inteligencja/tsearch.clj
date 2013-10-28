(ns tsearch
  (:use [kongra.core]))

(def d
  '{A [B C]
    B [D E]
    C [F G]
    E [H I]
    G [J K L]})

(defn make-goal
  [value]
  (fn [node] (= node value)))

(def g        (make-goal 'W))
(def start    'A)
(def adjacent d)

(defn search-1
  []
  (tree-search ['A] g adjacent depth-first-combiner))