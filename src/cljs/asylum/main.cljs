(ns asylum.main
  (:require [clojure.browser.repl]
            [jayq.core :as jq]
            [asylum.view :as v]
            [asylum.events :as e])
  (:use [jayq.core :only [$ css html]]))

(def state
  (atom {:turn 0
         :morrison 0.5
         :current {:arrivals 250
                   :deaths 10
                   :transit 3}
         :accumulated {:deported 0
                       :offshore 0
                       :detention 0
                       :community 0}}))

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
