(in-ns 'user)

(set! *warn-on-reflection* true)

(require '[clojure.data.csv :as  csv]
         '[clj-http.client  :as http]
         '[clojure.java.io])

(def PAGE-ID      (random-uuid))
(def ACCESS-TOKEN "EAAE9bnfNug4BO7NwT25CH6yJRWVu45gFy8qfw5SC0U1SW9ZC7GSsQAPp4hZC9pnFvEKwStnfMpSBv11tEubmZCU6PeChpXHH7ElnagrHv3JmNVpytypBxzlT90X1YXIsgJoZCAHI1fXisRGZCblaZAsfjYeaiADqqvt06agHKl1K0ue32HlcCBPOZBWjT0MrXpATilAuy6vQZBgemttbCAZDZD")

(str "https://graph.facebook.com/"
     PAGE-ID "/events"
     "?access_token=" ACCESS-TOKEN)

(defn hit-events! [page-id access-token]
  (try
    (let [headers  {:headers {"Authorization" (str "Bearer " access-token)}}
          response (http/get (str "https://graph.facebook.com/"
                                  page-id "/events"
                                  "?access_token=" access-token) headers)]
      (if (= (:status response) 200)
        {:response (:body response)}
        {:error {:status (:status response)}}))
    (catch Exception e
      {:fail {:message (.getMessage e)}})))

;; (hit-events! PAGE-ID ACCESS-TOKEN)

(defn hit-google! [q]
  (try
    (let [response (http/get (str "https://www.google.com/search?q="q))]
      (if (= (:status response) 200)
        {:response (:body response)}
        {:error {:status (:status response)}}))
    (catch Exception e
      {:fail {:message (.getMessage e)}})))

(defn hit-google-multiple-times!
  [n]
  #_(->> (range n)
         (map hit-google!)
         (vec))

  (let [results (atom [])]
    (dotimes [i n]
      (let [r (hit-google! (str i))]
        (swap! results conj r)))

    results))

;; (def RESULTS (hit-google-multiple-times! 10000))
;; (count @RESULTS)

(defn hit-google-multiple-times-virthreads!
  [n]
  (let [executor     (java.util.concurrent.Executors/newVirtualThreadPerTaskExecutor)
        results-atom (atom [])]
    (dotimes [i n]
      (.submit executor
               ^Runnable
               (fn []
                 (let [r (hit-google! (str i))]
                   (swap! results-atom conj r)))))

    results-atom))

(def RESULTS (hit-google-multiple-times-virthreads! 10000))
(count @RESULTS)
