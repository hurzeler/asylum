(ns asylum.handler
  (:require [compojure.core :refer (GET defroutes)]
            [compojure.route :refer (resources not-found)]
            [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [net.cgrand.enlive-html :as enlive]
            net.cgrand.reload
            [clojure.java.io :as io]

            ring.adapter.jetty))

(def dev true)

(defn make-page []
  (let [res (enlive/html-resource (io/resource "public/index.html"))
        res (if dev
              (enlive/transform res [:body] (enlive/append (enlive/html (browser-connected-repl-js))))
              res)]
    (apply str (enlive/emit* res))))

(defroutes main
  (resources "/")
  (GET "/*" req (make-page))
  (not-found "I still haven't found what you're looking for."))

(defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'main {:port 3000 :join? false}))
  server)

(defn non-browser-repl []
  (cemerick.piggieback/cljs-repl :repl-env (cemerick.austin/exec-env)))

(defn start-server-and-repl
  []
  (run)
  (cemerick.austin.repls/cljs-repl
   (reset! cemerick.austin.repls/browser-repl-env
           (cemerick.austin/repl-env))))

