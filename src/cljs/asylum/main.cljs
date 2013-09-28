(ns asylum.main
    (:require [clojure.browser.repl]
      [jayq.core :as jq]
      [asylum.view :as v]
      [asylum.events :as e])
    (:use [jayq.core :only [$ css html]]
      [jayq.util :only [log]]
      [asylum.state :only [state]]))

(defn advance-turn []
      (let [turn-levers (v/lever-values)]
           (swap! state update-in [:turn] inc)
           (swap! state merge turn-levers)
           (log "<<<STATE PRIOR TO EVENT PROCESSING>>>")
           (log @state)
           (swap! state e/apply-event)
           (log "<<<STATE POST EVENT PROCESSING>>>")
           (log @state)
           (v/display @state)))

(defn register-next []
      (jq/bind ($ "#nextTurn") :click advance-turn))

($ (fn []
       (register-next)
       (v/init-view @state)))
