(in-ns 'user)

(set! *warn-on-reflection* true)

;; APPLICATION ABSTRACTION
(def ^:dynamic *app* nil)

(defmacro with-app
  [app & body]
  `(binding [*app* ~app]
     ~@body))

;; LOGGING
(def ^:dynamic *logging?* false)

(defmacro with-logging
  [& body]
  `(binding [*logging?* true]
     ~@body))

;; LOGGING ABSTRACTION
(defprotocol Logger
  (log [this message argmap]))

;; TRANSACTIONAL ABSTRACTION
(defprotocol Transactor
  (in-serializable* [this body]))

(defmacro in-serializable [& body]
  `(in-serializable* *app* (fn [] ~@body)))

;; (BUSINESS) LOGIC
(defrecord User
  [email fist-name last-name date-of-birth])

(defprotocol UsersDB
  (add-email->user!  [this email user])
  (email->maybe-user [this email]))

(defn add-user! [{:keys [email] :as user}]
  (assert (instance? User user))
  (log *app* "add-user!" {:user user})
  (add-email->user! *app* email user))

(defn sign-up!
  [user]
  (assert (instance? User user))
  (log *app* "sign-up!" {:user user})

  (let [user-1 (email->maybe-user *app* (:email user))]
    (when-not user-1
      (add-user! user))))

;; EXAMPLE APP1
(defrecord App1 [email->user-ref]
  Logger
  (log [_ message argmap]
    (when *logging?*
      (println "log" message argmap)))

  Transactor
  (in-serializable* [_ body]
    (dosync (body))))

(extend-type App1
   UsersDB
   (add-email->user! [this email user]
     (alter (:email->user-ref this) assoc email user))

   (email->maybe-user [this email]
     (@(:email->user-ref this) email)))

;; TESTS
;; (add-user! (map->User {:email "john@gmail.com"}))
;; (email->maybe-user "john1@gmail.com")

#_(with-app (->App1)
    (binding [*logging?* true]
      (sign-up! (map->User {:email "kongra@gmail.com"}))
      (binding [*logging?* false]
        (sign-up! (map->User {:email "kongra@gmail.com"})))))

(->> (sign-up! (map->User {:email "kongra@gmail.com"}))
     (in-serializable)
     (with-logging)
     (with-app (map->App1 {:email->user-ref (ref {})})))

(def account-a (atom 50000))
(def account-b (atom 10000))

(defn test-1 []
  (dotimes [_ 1000]
    (future
      (let [a @account-a
            ;; _ (Thread/sleep 5)
            b @account-b]

        ;; (Thread/sleep 5)
        (reset! account-a (- a 1))
        ;; (Thread/sleep 5)
        (reset! account-b (+ b 1))))))

(test-1)
(vector @account-a @account-b (+ @account-a @account-b))

(def account-c (ref 50000))
(def account-d (ref 10000))

(defn test-2 []
  (dotimes [_ 1000]
    (future
      (dosync
       (alter account-c - 1)
       (alter account-d + 1)))))

(test-2)

(vector @account-c @account-d (+ @account-c @account-d))
