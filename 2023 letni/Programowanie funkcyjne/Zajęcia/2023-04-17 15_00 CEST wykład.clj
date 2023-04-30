(in-ns 'user)

(require '[clj.telsos.core.macros :refer :all])
(require '[cljc.telsos.core       :refer :all])

(set! *warn-on-reflection* true)

(defn getter
  [property]
  (let [name- (str "get-" (name property))]
    (if (keyword? property)
      (keyword name-)
      (symbol  name-))))

(getter 'x)
(getter :x)

(def person-1
  {:name "John"
   :email "joe@gmail.com"
   :age 24})

(class person-1)

(person-1 :name)
(:first-name person-1 :default)

(defn report-person [p]
  (let [name- (:name  p)
        email (:email p)
        age   (:age   p)]

    (str "This is a Person"
         name- " "
         email " "
         age)))

(report-person person-1)

(defn report-person-1 [p]
  (let [{:keys [name- email age]} p]
    (str "This is a Person "
         name- " "
         email " "
         age)))

(report-person-1 person-1)

(defn report-person-2 [{:keys [name- email age]}]
  (str "This is a Person"
       name- " "
       email " "
       age))

(report-person-2 person-1)

(eval '(+ 1 2 3 4 5))

;; OPERACJA apply
(defn foo [a b c]
  (+ (* 3 a) b c))

(foo 1 2 3)

(apply foo [1 2 3])

(for [a  [1 2 3 4]
      b '[a b c]]

  (str a b))

#_(forc [i 1, (< i 10), (inc i)]
      (println i))

#_(forc [<init> <pred> <post>] <body>)

(defmacro forc
  [[init pred post] & body]

  `(loop [~@init]
     (when ~pred
       ~@body
       (recur ~post))))

(forc [(i 1) (< i 10) (inc i)]
      (println i))
