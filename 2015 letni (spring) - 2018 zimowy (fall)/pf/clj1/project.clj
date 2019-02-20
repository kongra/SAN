(defproject clj1 "0.1.0"
  :description "FIXME: write description"
  :url         "https://github.com/kongra/clj1"
  :license     {:name "Eclipse Public License"
                :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]]

  :profiles {:repl  {:dependencies [[org.clojure/tools.nrepl "0.2.13"]]
                     :plugins      [[lein-nodisassemble      "0.1.3" ]
                                    [cider/cider-nrepl       "0.16.0"]]}}

  :global-vars  {*warn-on-reflection* true
                 *assert*             true
                 *print-length*       500}

  :pedantic?    :warn
  :jvm-opts     ["-Dclojure.compiler.direct-linking=true"])
