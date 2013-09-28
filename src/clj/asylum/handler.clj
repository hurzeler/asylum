(ns asylum.handler
  (:require [compojure.core :refer (GET defroutes)]
            [compojure.route :refer (resources not-found)]
            [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [net.cgrand.enlive-html :as enlive]
            net.cgrand.reload
            [clojure.java.io :as io]

            ring.adapter.jetty))

(def dev false)

(if dev
  (enlive/deftemplate page
    "public/index.html"
    []
    [:body] (enlive/append
             (enlive/html [:script (browser-connected-repl-js)])))
  (enlive/deftemplate page
    "public/index.html"
    []
    [:body] (enlive/append
             (enlive/html [:div]))))

(defroutes main
  (resources "/")
  (GET "/*" reg (page))
  (not-found "I still haven't found what you're looking for."))

#(defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'main {:port 3000 :join? false}))
  server)

#(defn start-server-and-repl
  []
  (run)
  (cemerick.austin.repls/cljs-repl
   (reset! cemerick.austin.repls/browser-repl-env
           (cemerick.austin/repl-env))))

