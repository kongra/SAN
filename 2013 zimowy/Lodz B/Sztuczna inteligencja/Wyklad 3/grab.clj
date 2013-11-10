;;; -*- Mode: Clojure; coding: utf-8 -*-
;;;
;;; Project LAN
;;; Copyright (c) Konrad Grzanek. All rights reserved.
;;; Created 2009-10-22
;;; 

(ns kongra.html.grab
  (:use		[kongra.core])
  (:require	[kongra.log  :as LOG])

  (:import	[kongra.core Pair]
		[org.htmlparser Node Tag Remark Text Attribute]
		[org.htmlparser.util NodeList]))


(LAN-header) 

(LOG/on)

(defmulti convert
  "Converts a low-level HTML representation into Clojure structures."
  
  class)


(defn grab
  "Grabs a WEB page from a given location. Returns a pair of the target URL
  (redirections possible) and the page content.

  When the to-clojure parameter is set to true (default), the content is
  converted to Clojure representation - recursive map. Otherwise the native
  NodeList Java object is kept."
  ([^String url] 
     (when-let [[url nodes] (kongra.net.HTML/parse url false)] 
       (pair url (convert nodes)))))


(defn ^String page-url 
  "Returns the URL of a grabbed page or nil, if the argument was nil."
  [^Pair page] 
  (when page (tstr (.first page))))


(defn page-content
  "Returns the grabbed page content in a Clojure form or nil if the argument
  was nil."
  [^Pair page]
  (when page (.second page)))


;; THE EFFECTIVE LISP REPRESENTATION

;; STRUCTRE OF THE HTML NODE
;; NAME       - OF A TAG, NIL FOR TXT NODES AND COMMENTS (REMARKS)
;; TXT        - TEXTUAL CONTENT OF THE NODE, NIL FOR TAG
;; REMARK     - TEXTUAL CONTENT OF THE COMMENT (REMARK)
;; ATTRIBUTES - ATTRIBS OF THE TAG
;; SUBNODES   - SUBNODES OF THE TAG
(defstruct node :name :txt :remark :attributes :subnodes)


(defn- create-txt-node 
  [^String content] (struct-map node :txt content))


(defn- create-remark-node 
  [^String content] (struct-map node :remark content))


(defn- create-tag-node 
  [name attributes subnodes] 
  (struct-map node :name name :attributes attributes :subnodes subnodes))


(defn grab-as-tree
  "Grabs the WEB page specified by the given URL. Returns a two-element
  vector consisting of the target URL (redirection possible) and the page
  content represented as a Clojure collection (recursive map)."
  [^String url] 
  (when-let [[url nodes] (grab url)] 
    [url (convert nodes)]))


(defn- not-nil
  "Filters out nil elements"
  [coll] (filter (fn [obj] (not (nil? obj))) coll))


(defmethod convert NodeList
  [^NodeList lst] 
  (when lst 
    (not-nil (map convert (seq (.toNodeArray lst))))))


(defmethod convert Text
  [^Text node] 
  (let [content (.getText node)] 
    (when-not (blank? content) 
      (create-txt-node (.trim content)))))


(defmethod convert Remark
  [^Remark node] 
  (let [content (.getText node)] 
    (when-not (blank? content) 
      (create-remark-node (.trim content)))))


(declare html-attributes)
(defmethod convert Tag
  [^Tag node] 
  (when-not (.isEndTag node) 
    (let [name (.getTagName node)
	  attrs (html-attributes node name)
	  subnodes (when-let [subs (.getChildren node)] 
		     (convert subs))]
      (create-tag-node name attrs subnodes))))


(defn- html-attributes 
  [^Tag node ^String node-name] 
  (apply hash-map 
	 (mapconcat (fn [[^String name value]] 
		      (list (keyword name) value)) 
		    (filter (fn [[^String name value]] 
			      (and name value 
				   (not (.equalsIgnoreCase name node-name)))) 
			    (map (fn [^Attribute attr] 
				   [(.getName attr) (tstr (.getValue attr))]) 
				 (seq (.getAttributesEx node)))))))


;; SEARCHING FOR NODES



(defn search
  "Searches for the leaf-node of the path specified by the collection of
  predicates. Every predicate is a description of a node. By default 
  breadth-first search is used as a search strategy.

  Parameter max-depth specifies the maximum depth of recursion during the
  search for the first node matching a single predicate."
  [max-depth start & predicates]
  (cond
    ;; MAPS GO FIRST
    (map? start) 
    (cond (empty? predicates) start
	  :else (when-let [current-found (breadth-first-search 
					  start 
					  (first predicates)
					  :subnodes
					  max-depth)]
			       
		  (recur max-depth current-found (rest predicates))))    
   
    ;; IF START IS A SEQUENCE OF MAPS, WE TRY TO FIND IN CONSECUTIVE MAPS
    (sequential? start) 
    (first (not-nil 
	    (map (fn [e]
		   (apply search max-depth e predicates)) 
		 start)))

    ;; PAIRS ARE STRIPPED TO THE CONTENT
    (instance? Pair start) 
    (recur max-depth 
	   (.second ^Pair start) 
	   predicates)

    ;; ANU OTHER GO AS AN ERROR
    :else 
    (throw (new IllegalArgumentException 
		(tstr "Illegal start type " start)))))


(defn tag-of-name?
  "Answers true when node is a tag of specified name."
  [node ^String name] 
  (let [node-name (tstr (:name node))]
    (when node-name
      (or (= name node-name) 
	  (.equalsIgnoreCase name node-name)))))


(declare txt-predicate remark-predicate tag-predicate)

(defmacro pred
  "Defines a predicate based on an expression. The expression has one of the
  following forms:
  
  (tag-name attrs and vals) e.g. (div :id 'sample-class')
  (:txt 'The text to be found')
  (:remark 'The text to be found')."
  [expr] 
  (cond (= :txt (first expr)) (txt-predicate expr)
	(= :remark (first expr)) (remark-predicate expr)
	(or (string? (first expr)) (symbol? (first expr))) 
	(tag-predicate expr)
	:else (throw (new IllegalArgumentException 
			  (tstr "Illegal expr " expr)))))


(defn- tag-predicate
  [expr]
  (let [node (gensym "node_")]
    `(fn [~node] 
       (and (tag-of-name? ~node ~(kongra.core/tstr (first expr)))
	    ~@(map (fn [[key val]] `(= ~val (~key (:attributes ~node)))) 
		   (partition 2 (rest expr)))))))


(defn- txt-predicate 
  [expr]
  (let [text-to-match (second expr)]
    `(fn [node#] 
       (= ~text-to-match (:txt node#)))))


(defn- remark-predicate
  [expr]
  (let [text-to-match (second expr)]
    `(fn [node#] 
       (= ~text-to-match (:remark node#)))))
