(set! *warn-on-reflection* true)

(require '[cljc.telsos.core
           :refer [non-blank?]])
(require '[malli.core  :as malli])
(require '[malli.error :as malli-error])

(def REGISTRY
  {:registry
   {"FirstName" [:fn {:error/message "FirstName must not be blank"} non-blank?]
    "LastName"  [:fn {:error/message "LastName  must not be blank"} non-blank?]

    "Person"
    [:map
     [:first-name [:ref "FirstName"]]
     [:last-name  [:ref "LastName" ]]]}})

(def FirstName (malli/schema [:schema REGISTRY "FirstName"]))
(def Person    (malli/schema [:schema REGISTRY "Person"   ]))

(malli/validate Person {:firstName "John" :last-name "Doe"})
(malli/explain  Person {:firstName "John" :last-name "Doe"})

(malli-error/humanize
  (malli/explain
    Person {:first-name "   " :last-Name "Doe"}))

(malli/parse Person {:first-name "John" :last-name "Doe"})
