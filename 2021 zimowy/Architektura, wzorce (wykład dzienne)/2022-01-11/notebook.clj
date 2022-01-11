(in-ns 'user)

(defrecord Point [x y])

(def p1 (->Point 1 2))
(:x p1)
(:y p1)

(class p1)

(def n1 (int 123))
(class n1)

(defprotocol Sendable
  (send! [this]))

(defn sender-send!
  [list-]
  (doseq [e list-]
    (send! e)))

(extend-protocol Sendable
  Point
  (send! [this]
    (println "Point::send!" (:x this) (:y this))))

(extend-protocol Sendable
  java.lang.Long
  (send! [this]
    (println "Sending!" this))

  java.lang.String
  (send! [this]
    (println "Sending!" this)))

(sender-send!
  [(->Point 1 2) (->Point 3 4) 1 2 3 "www"])

;; MULTIMETHODS
(defmulti display!
  (fn [x y]
    [(class x) (class y)]))

(display! 1 "aaa")

(defmethod display! [Long String]
  [x y]
  (println "displaying Long,String" x y))
