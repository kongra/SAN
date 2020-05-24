(defproject fp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.0"]]

  :aot          :all
  :source-paths ["src/main/clojure"]
  :test-paths   ["test/clojure"]

  :global-vars  {*warn-on-reflection* false
                 *print-length*         500}

  :jvm-opts     ["-Dclojure.compiler.direct-linking=true"]

  :clean-targets ^{:protect false} ["target"]

  :repl-options {:init-ns fp.core}

  :profiles {:repl {:plugins
                    [[lein-nodisassemble "0.1.3"]
                     [cider/cider-nrepl  "0.25.0-SNAPSHOT"]]

                    :middleware
                    [lein-nodisassemble.plugin/middleware
                     cider-nrepl.plugin/middleware]

                    :jvm-opts ["-Dclojure.compiler.direct-linking=false"
                               "-XX:+DoEscapeAnalysis"
                               "-Xms1G"
                               "-Xmx1G"
                               #_"-verbose:gc"]}})
