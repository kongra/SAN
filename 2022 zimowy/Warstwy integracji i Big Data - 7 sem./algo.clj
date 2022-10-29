(require
  '[clojure.string   :as         str]
  '[clojure.pprint   :refer [pprint]]
  '[clojure.data.csv :as         csv]
  '[clojure.java.io  :as          io])

(set! *warn-on-reflection*       true)
(set! *unchecked-math* :warn-on-boxed)

(defn- read-csv
  [filename]
  (io! (with-open [reader (io/reader filename)]
         (doall (csv/read-csv reader)))))

(def PATH "/media/kongra/Devel/Projects/Present/SAN/2022 zimowy/Warstwy integracji i Big Data - 7 sem./")

(defn csv->first-name [[first-name n gender]]
  {:first-name (str/capitalize first-name)
   :count      (Long/parseLong n)
   :gender     gender})

(def FIRST-NAMES-CSV
  (str PATH "IMIONA_NADANE_DZIECIOM_W_POLSCE_W_I_POŁOWIE_2022_R._-_IMIĘ_PIERWSZE.csv"))

(def FIRST-NAMES
  (->> FIRST-NAMES-CSV
    (read-csv)

    ;; We skip the heading row
    (next)

    (map csv->first-name)
    (sort-by :count)
    (reverse)
    (vec)))

(def FIRST-NAMES-M (get (group-by :gender FIRST-NAMES) "M"))
(def FIRST-NAMES-F (get (group-by :gender FIRST-NAMES) "K"))

;; SELECT id FROM PROFILES WHERE email LIKE '%gmail.com%'
