(defproject asylm "1.0.0"
  :description "Asylum - A Clojure Cup game"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1847"]
                 [compojure "1.1.5"]
                 [ring "1.2.0"]
                 [enlive "1.1.1"]
                 [domina "1.0.2-SNAPSHOT"]
  				 [jayq "2.4.0"]]

  ; Resources
  :scss {:src "resources/scss"
         :output-directory "resources/public/css"
         ;; Other options (provided are default values)
         ;; :output-extension "css"
         ;; :auto-compile-delay 250
         ;; :delete-output-dir true ;; -> when running lein clean it will delete the output directory if it does not contain any file
         ;; :ignore-hooks [:clean :compile :deps] ;; -> if you use the hooks, this option allows you to remove some hooks that you don't want to run
         ;; :gem-version "3.2.1"
         }

  :plugins [[lein-haml-sass "0.2.5"]]

  :source-paths ["src/clj" "src/cljs"]

  :ring {:handler asylum.handler/main}
  
  :profiles {:dev
             {:repl-options {:init-ns clojure-cup-prep.core}
              :plugins [[com.cemerick/austin "0.1.1"]
                        [lein-cljsbuild "0.3.2"]
                        [lein-ring "0.8.5"]]
              :cljsbuild {:builds [{:source-paths ["src/cljs"]
                                    :compiler {:output-to "resources/public/main.js"
                                               :optimizations :simple
                                               :pretty-print true}}]}}})

