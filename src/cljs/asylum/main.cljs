(ns asylum.main
  (:require [clojure.browser.repl]
            [domina :as d]
            [domina.events :as evt]
            [domina.css :as css])
  (:use [jayq.core :only [$ css html]]))

(defn hello
  []
  (js/alert "hello"))

(defn whoami
  []
  (.-userAgent js/navigator))

(defn say
  ([something]
     (say "greeting" something))
  ([id something]
     (d/set-text! (d/by-id id) something)))

($ (fn [] (say "Hell WOrld")))
