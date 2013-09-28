(ns asylum.handler
  (:require [compojure.core :refer (GET defroutes)]
            [compojure.route :refer (resources not-found)]
            [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [net.cgrand.enlive-html :as enlive]
            [clojure.java.io :as io]))

(defn main-file [_]
  "Hello")

(enlive/deftemplate page
  (io/resource "public/index.html")
  []
  [:body] (enlive/append
           (enlive/html [:script (browser-connected-repl-js)])))

(defroutes main
  (resources "/")
  (GET "/*" [] (main-file true))
  (not-found "I still haven't found what you're looking for."))
