(require
  '[clojure.core.async
    :refer [go thread chan timeout
            <!! >!! <! >!]])

#_(let [ch1 (chan)]
    (thread
      (println "--1: 3 -> ch1")
      (>!! ch1 3)
      (println "--1: OK")
      (println "--1: END"))

    (thread
      (println "--2:" (<!! ch1))
      (println "--2: END")))

(let [ch1 (chan)]
  (go
    (println "--1: 3 -> ch1")
    (>! ch1 3)
    (println "--1: OK")
    (println "--1: END"))

  (go
    (println "--2:" (<! ch1))
    (println "--2: END")))

(let [t1 (timeout 1000)]
  (go (println "Za 1000 ms ruszę dalej")
      (<! t1)
      (println "Idę dalej")))

(go (println "Za 1000 ms ruszę dalej")
    (<! (timeout 1000))
    (println "Idę dalej"))
