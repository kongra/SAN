(ns wiki
  (:use [kongra.core])
  
  (:require [kongra.search :as SEARCH]
            [kongra.html   :as HTML]
            [kongra.nlp    :as NLP]))


(def page (HTML/grab "http://en.wikipedia.org/wiki/Magnus_Carlsen"))

(defn page-title
  [page]
  (when-let [root (second (:nodes page))]
    (when-let [span (first
                     (SEARCH/tree-predpath-search
                      root :children

                      [(HTML/html)
                       (HTML/body)
                       (HTML/div :id "content" :class "mw-body" :role "main")
                       (HTML/h1 :id "firstHeading" :class "firstHeading" :lang "en")
                       (HTML/span :dir "auto")]))]

      (->> span :children first :content))))


(def tekst
  "In information theory and computer science, the Damerau–Levenshtein
  distance (named after Frederick J. Damerau and Vladimir
  I. Levenshtein) is a \"distance\" (string metric) between two
  strings, i.e., finite sequence of symbols, given by counting the
  minimum number of operations needed to transform one string into the
  other, where an operation is defined as an insertion, deletion, or
  substitution of a single character, or a transposition of two
  adjacent characters. In his seminal paper[1], Damerau not only
  distinguished these four edit operations but also stated that they
  correspond to more than 80% of all human misspellings. Damerau's
  paper considered only misspellings that could be corrected with at
  most one edit operation. The corresponding edit distance, i.e.,
  dealing with multiple edit operations, known as the Levenshtein
  distance, was introduced by Levenshtein,[2] but it did not include
  transpositions in the set of basic operations. The name
  Damerau–Levenshtein distance is used to refer to the edit distance
  that allows multiple edit operations including transpositions,
  although it is not clear whether the term Damerau–Levenshtein
  distance is sometimes used in some sources as to take into account
  non-adjacent transpositions or not.")

