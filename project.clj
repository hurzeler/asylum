(defproject asylm "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]

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
  )
