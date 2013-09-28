(ns asylum.main
  (:require [clojure.browser.repl]
            [jayq.core :as jq]
            [asylum.view :as v]
            [asylum.events :as e])
  (:use [jayq.core :only [$ css html]]
        [asylum.state :only [state]]))

(defn advance-turn []
  (swap! state update-in [:turn] inc)
  (swap! state e/apply-event)
  (v/display @state))

(defn hello [] 
  (js/alert "Hello"))

(defn register-next []
  (jq/bind ($ "#nextTurn") :click advance-turn))

($ (fn []
     (register-next)
     (v/init-map)))
