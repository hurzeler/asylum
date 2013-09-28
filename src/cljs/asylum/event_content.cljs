(ns asylum.event-content
  (:require [asylum.event-engine :as ee]))

(def dummy-event
  {:constraints {:morrison [0 1]
                 :turn [1 6]}
   :effectss 1
   :effect (ee/dsr-factor :population -0.2)})
