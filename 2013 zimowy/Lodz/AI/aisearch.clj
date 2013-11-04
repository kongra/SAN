(ns aisearch
  (:use     [kongra.core])
  (:require [kongra.search :as SEARCH]))


(def DOM
  '{document [html]
    html     [head body]
    head     [title]
    body     [a1 a2 a3]
    a1       [b4 b5]
    a3       [b6 b7]
    b4       [c8 c9]
    b6       [c10 c11]
    c8       [e14 e15]
    c10      [d12 d14]})

;; Zadanie 1. Znajdź węzeł d12, który jest dzieckiem węzła c10, który
;; znajduje się gdzieś w html, body.

(defn- pred
  [s]
  (fn [node] (= node s)))


(defn- zadanie-1
  []
  (SEARCH/tree-predpath-search
   'document DOM
   
   [(pred 'document) (pred 'html) (pred 'body) SEARCH/... (pred 'd12)]

   ))