(ns cemerick.austin.bcrepl-sample
  (:require [clojure.browser.repl]
            [domina :as d]
            [domina.css :as css]
            [domina.events :as evts]))

(defn hello
  []
  (js/alert "hello"))

(defn whoami
  []
  (.-userAgent js/navigator))

(defn say [something]
  (js/alert something))

(defn say-in
  ([msg] (say-in "h1" msg))
  ([sel msg]
     (d/set-text! (css/sel sel) msg)))

(def say-h2 (partial say-in "h2"))

(evts/listen! js/window :load (fn [] (say-in "h1" "Hello there")))
