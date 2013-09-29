(ns asylum.main
    (:require [clojure.browser.repl]
      [jayq.core :as jq]
      [asylum.view :as v]
      [asylum.events :as e]
      [asylum.boats :as b])
    (:use [jayq.core :only [$ css html]]
      [jayq.util :only [log]]
      [asylum.state :only [state]]))

(defn apply-event-choice [choice]
      (log (str "Player chose " (name choice)))
      (swap! state e/apply-event-choice choice))

(defn advance-turn []
      (let [turn-levers (v/lever-values)]
           (swap! state update-in [:turn] inc)
           (swap! state merge turn-levers)
           (log "<<<STATE PRIOR TO EVENT PROCESSING>>>")
           (log @state)
           (swap! state e/apply-effects)
           (swap! state e/apply-levers)
           (swap! state b/add-boats)
           (log "<<<STATE POST EVENT PROCESSING>>>")
           (log @state)
           (swap! state merge (e/choose-event @state))
           (v/display @state (comp advance-turn apply-event-choice))))

($ (fn []
       (v/display @state (comp advance-turn apply-event-choice))))

(defn action-boat [boat action]
  (swap! state update-in [:morrison] + ({:sink 0.3 :turn-back 0.1 :rescue -0.1} action))
  (swap! state update-in [:boats] disj boat))
