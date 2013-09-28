(ns asylum.main
  (:require [clojure.browser.repl]
            [domina :as d]
            [domina.events :as evt]
            [domina.css :as css]
            [jayq.core :as jq])
  (:use [jayq.core :only [$ css html]]))

(defn say
  ([something]
     (say "greeting" something))
  ([id something]
     (d/set-text! (d/by-id id) something)))

(def state
  (atom {:turn 0 :changes 0}))

(defn display []
  (say "turn" (str (-> @state :turn)))
  (say "changes" (str (-> @state :changes))))

(defn advance-turn []
  (swap! state update-in [:turn] inc)
  (display))

(defn hello []
  (js/alert "Hello"))

(defn register-next []
  (jq/bind ($ "#nextTurn") :click advance-turn))

(defn register-change []
  (jq/bind ($ "#changeSomething") :click (fn [] (swap! state update-in [:changes] inc))))

($ (fn []
     (register-next)
     (register-change)
     (say "Welcome to Asylum")))
