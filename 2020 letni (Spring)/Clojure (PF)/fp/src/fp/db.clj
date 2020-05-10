(ns fp.db)

(defonce persons-atom (atom {}))

(defn add-person!
  [email first-name last-name]
  (swap! persons-atom assoc
    email {:first-name first-name
           :last-name   last-name}))

(defn first-name
  [email]
  (:first-name (@persons-atom email)))

(defn last-name
  [email]
  (:last-name (@persons-atom email)))

;; (defn add-person
;;   [persons email first-name last-name]
;;   (assoc persons
;;     email {:first-name first-name
;;            :last-name   last-name}))

;; (defn first-name
;;   [persons email]
;;   (:first-name (persons email)))

;; (defn last-name
;;   [persons email]
;;   (:last-name (persons email)))

;; (-> {}
;;   (add-person"kongra@gmail.com"  "Konrad"  "Grzanek")
;;   (add-person"kongra1@gmail.com" "Konrad1" "Grzanek")
;;   (add-person"kongra2@gmail.com" "Konrad2" "Grzanek")
;;   (add-person"kongra3@gmail.com" "Konrad3" "Grzanek")
;;   (add-person"kongra4@gmail.com" "Konrad4" "Grzanek"))
