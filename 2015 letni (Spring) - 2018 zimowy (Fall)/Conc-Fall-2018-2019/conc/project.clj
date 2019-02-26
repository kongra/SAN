(defproject conc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]]
  :profiles {:repl  {:dependencies [[org.clojure/tools.nrepl "0.2.13"]]
                     :plugins      [[lein-nodisassemble      "0.1.3" ]
                                    [cider/cider-nrepl       "0.17.0"]]}}

  :source-paths ["src/clj" "test"]

  :global-vars  {*warn-on-reflection* true
                 *assert*             true
                 *print-length*       500}

  :pedantic? :warn
  :aot       :all
  )
