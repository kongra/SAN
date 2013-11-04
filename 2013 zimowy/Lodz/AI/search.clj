;;; -*- Mode: Clojure; coding: utf-8 -*-
;;;
;;; Project LAN
;;; Copyright (c) Konrad Grzanek. All rights reserved.
;;; Created 2009-10-02
;;;

(ns kongra.search
  (:use		[kongra.core])

  (:require	[clojure.set	:as CSET]
		[kongra.log	:as LOG]))

(LAN-header)


;; PATH-ORIENTED TREE SEARCHES

(defn- make-inode
  ([node satisfies-goal?]
     (let [result ^objects (make-array java.lang.Object 2)]
       (aset result 0 node)
       (aset result 1 satisfies-goal?)
       result))

  ([node] (make-inode node false)))

(defn- inode-node [^objects in] (aget in 0))
(defn- inode-satisfies-goal? [^objects in] (aget in 1))
(defn- inode-set-satisfies-goal [^objects in satisfies-goal?]
  (aset in 1 satisfies-goal?))

(defn- inode-goal?
  [goal?]
  (fn [in]
    (or (inode-satisfies-goal? in) (goal? (inode-node in)))))

(defn- inode-adjacent
  [goal? adjacent]
  (fn [in]
    (let [node (inode-node in)]
      (if (goal? node)
	(do (inode-set-satisfies-goal in true)
	    nil)

	(map make-inode (adjacent node))))))


(defn- tree-search-all-first
  "Finds all occurences of nodes satisfying the goal? predicate. Searches all
  branches finishing the recursive step on the nodes that actually satisfy the
  predicate, doesn't go deeper."

  ([strategy start goal? adjacent]
     (map inode-node
     	  (filter (inode-goal? goal?)
     		  (strategy (make-inode start)
     			    (inode-adjacent goal? adjacent)))))

  ([strategy start goal? adjacent depth]
     (map inode-node
	  (filter (inode-goal? goal?)
		  (strategy (make-inode start)
			    (inode-adjacent goal? adjacent)
			    depth)))))


(defn breadth-first-search-all-first
  "@see tree-search-all-first with breadth-first-tree-seq."
  ([start goal? adjacent]
     (tree-search-all-first breadth-first-tree-seq
			    start goal? adjacent))

  ([start goal? adjacent depth]
     (tree-search-all-first breadth-first-tree-seq
			    start goal? adjacent depth)))


(defn depth-first-search-all-first
  "@see tree-search-all-first with depth-first-tree-seq."
  ([start goal? adjacent]
     (tree-search-all-first depth-first-tree-seq
			    start goal? adjacent))

  ([start goal? adjacent depth]
     (tree-search-all-first depth-first-tree-seq
			    start goal? adjacent depth)))


(def ^{:doc "Tree predpath wildcard symbol. @see tree-predpath-search"}
     ... '...)

(defn ?
  "@see tree-predpath-search"
  [& _] true)

(defn tree-predpath-search
  "Searches the argument tree represented by start node and adjacent nodes
  generator according to the passed sequence (path) of predicates.
  The special predicate ... means searching depth-first for all the first
  occurences of nodes that fulfill the next predicate (after ...) or -
  if ... is the last - returns a concatenation of depth-first sequences.
  ? symbol is a predicate that accepts every node at the current level."
  [start adjacent predpath]
  (assert (seq predpath) "The predpath must not be empty.")
  
  (loop [nodes [start]
	 predpath predpath]
    
    (when (seq nodes)
      (let [pred (first predpath)]
	(if (= pred ...)
	  ;; WILDCARD SEARCH
	  (if-let [pred (second predpath)]
	    (recur (apply concat
			  (map #(depth-first-search-all-first % pred adjacent)
			       nodes))
		   (rest predpath))

	    ;; ... IS THE LAST PREDICATE - RETURN A DEPTH-FIRST SEQUENCE
            (->> nodes
                 (map #(depth-first-tree-seq % adjacent))
                 (apply concat)))

	  ;; NORMAL PREDICATE-BASED SELECTION
	  (if-let [new-predpath (next predpath)]
            ;; STILL SOME PREDICATES TO GO
            (recur (->> nodes
                        (filter pred)
                        (map adjacent)
                        (apply concat))

                   new-predpath)

            ;; NO MORE PREDICATES
            (seq (filter pred nodes))))))))


(comment
  (def drzewko
       '{
	 [1 a] [[2 b] [3 c]]

	 [2 b] [[4 d] [5 e]]
	 [3 c] [[6 f] [7 g]]

	 [4 d] [[8 n] [9 n]]
	 [5 e] [[10 g] [11 m]]
	 [6 f] [[12 n]]
	 [7 g] [[13 k] [14 k] [15 m]]

	 [10 g] [[16 n] [17 d]]

	 [16 n] [[18 n] [19 g]]
	 [17 d] [[20 m] [ 21 k]]})

  (depth-first-search-all-first '[1 a] #(= 'a (second %)) drzewko))


;; SEARCH ALGORITHMS DESCRIBED IN PAPI, CHAPTER 6.4. CONTINUATION OF
;; kongra.core ALGORITHMS

(defn sorter-combiner
  "Returns the combiner function that adds generated nodes to the collection
  of nodes and sorts accorind to cost-fn (descending according to cost value)."
  [cost-fn]
  (fn [new-nodes search-collection]
    (sort (fn [val-1 val-2] (< (cost-fn val-1) (cost-fn val-2)))
	  (concat new-nodes search-collection))))


(defn best-first-search
  "Searches lowest cost states (according to cost-fn) until goal is reached."
  [start goal? successors-generator cost-fn]
  (tree-search (list start) goal? successors-generator
	       (sorter-combiner cost-fn)))


(defn beam-search
  "Searches highest scoring states (with lowest cost according to cost-fn),
  but never keeps (considers) more than beam-width states in the search
  collection."
  [start goal? successors-generator cost-fn beam-width]
  (tree-search (list start) goal? successors-generator
	       (fn [new-nodes search-collection]
		 (take beam-width ((sorter-combiner cost-fn)
				   new-nodes
				   search-collection)))))


(defn iterative-widening-search
  "Searches increasing beam width from beam-width (default 1) to
  max-beam-width (default 100). Returns the first solution found at
  any width."
  ([start goal? successors-generator cost-fn]
     (iterative-widening-search start goal?
				successors-generator cost-fn 1 100))
  ([start goal? successors-generator cost-fn beam-width max-beam-width]
     (LOG/debug "Beam-width: %d" beam-width)
     (when (<= beam-width max-beam-width)
       (let [result (beam-search start goal? successors-generator
				 cost-fn beam-width)]
	 (if result
	   result
	   (recur start goal? successors-generator cost-fn
		  (inc beam-width) max-beam-width))))))


(defn graph-search
  "Finds a state that satisfies goal? predicate. Starts with states
  and proceeds according to successors-generator and combiner. It
  never considers the same state twice, keeping the set of already
  examined states."
  ([states goal? successors-generator combiner]
     (graph-search states goal? successors-generator combiner #{}))
  ([states goal? successors-generator combiner examined-states]
     (LOG/debug "Search: %s\n" (tstr (seq states)))
     (cond (nil? states) nil
	   (goal? (first states)) (first states)
	   :else (recur
		  (combiner
		   (filter (fn [state]
			     ;; new state is valid only if not present
			     ;; in states nor in examined-states
			     (not (or (member? states state)
				      (examined-states state))))
			   (successors-generator (first states)))
			    (rest states))
		  goal?
		  successors-generator
		  combiner
		  (CSET/union #{(first states)} examined-states)))))


;; A*-SEARCH ALGORITHM

(defstruct path ;; Represents path in search-graphs.
  :state        ;; the end of this path
  :previous     ;; previous partial path that this path is extending
  :cost-so-far  ;; the cost of this path
  :total-cost)  ;; estimated total cost of reaching the goal


(defn- path-to-string
  [path]
  (format "Path to %s, cost %s" (:state path) (:total-cost path)))


(defn make-path
  ([state previous cost-so-far total-cost]
     (struct path state previous cost-so-far total-cost))

  ([state]
     (make-path state nil 0 0)))


(defn- find-path
  "Find the path with this state amongst a list of paths"
  [state paths state-eq]
  (first (filter (fn [path] (state-eq (:state path) state)) paths)))


(defn- better-path?
  "Is path1 cheaper than path2?"
  [path1 path2]
  (< (:total-cost path1) (:total-cost path2)))


(defn- insert-path
  [path paths]
  "Put path in the right position, sorted by total cost."
  (sort better-path? (cons path paths)))


(defn path-states
  "Collect the states along this path."
  [path]
  (when path
    (lazy-seq
      (cons
       (:state path)
       (path-states (:previous path))))))


(defn- setf [^clojure.lang.Atom atom val]
  (swap! atom (constantly val)))


(defn a*-search
  "Find a path whose state satisfies goal?. Start with paths, and
  expand successors, exploring least cost first. When there are
  duplicate states, keep the one with the lower cost and discard the
  other."
  ([paths goal? successors cost-fn cost-left-fn]
     (a*-search paths goal? successors cost-fn cost-left-fn = #{}))
  ([paths goal? successors cost-fn cost-left-fn state-eq]
     (a*-search paths goal? successors cost-fn cost-left-fn state-eq #{}))
  ([paths goal? successors cost-fn cost-left-fn state-eq old-paths]
     (LOG/debug "Search: %s" paths)
     (cond
       (empty? paths) nil
       (goal? (:state (first paths))) (first paths)
       :else (let [path (first paths)
		   rest-paths (rest paths)
		   old-paths-a (atom (insert-path path old-paths)) ;; mutable wrappers
		   paths-a (atom rest-paths)
		   state (:state path)]
	       (doseq [state2 (successors state)]
		 (let [cost (+ (:cost-so-far path)
			       (cost-fn state state2))
		       cost2 (cost-left-fn state2)
		       path2 (make-path state2
					path
					cost
					(+ cost cost2))
		       old-a (atom nil)]
		   (cond
		     (not (empty? (setf old-a (find-path state2 @paths-a state-eq))))
		     (when (better-path? path2 @old-a)
		       (setf paths-a (insert-path path2 (remove (partial = @old-a) @paths-a))))
		     (not (empty? (setf old-a (find-path state2 @old-paths-a state-eq))))
		     (when (better-path? path2 @old-a)
		       (setf paths-a (insert-path path2 @paths-a))
		       (setf old-paths-a (remove (partial = @old-a) @old-paths-a)))
		     :else (setf paths-a (insert-path path2 @paths-a)))))
	       (recur @paths-a goal? successors cost-fn cost-left-fn state-eq @old-paths-a)))))



;; Some testing stuff
(defn- binary-tree
  [x] [(* 2 x) (+ 1 (* 2 x))])


(defn- is [value]
  (fn [x] (= x value)))


(defn- diff [num]
  (fn [x] (clojure.contrib.math/abs (- x num))))


(defn- next2 [x] (list (+ x 1) (+ x 2)))

;; (path-states (a*-search (make-path 1) (is 6) next2 (fn [x y] 1) (diff 6) '()))

;; (time (dotimes [_ 100000] (path-states (a*-search (list (make-path 1)) (is 6) next2 (fn [x y] 1) (diff 6)))))