(ns asylum.events
  (:require [asylum.event-content :as ec]
            [asylum.event-engine :as ee]))

(def s {:morrison 0.5 :turn 1 :population 100})

(def dummy-event
  {:constraints {:morrison [0 1]
                 :turn [1 6]}
   :effect (ee/dsr-factor :population -0.2)})

(def event-store
  [dummy-event])

(defn in-range [n [x y]]
  (<= x n y))

(defn does-apply? [state evt]
  (let [{:keys [morrison turn]} state
        {constraints :constraints} evt]
    (and
     (in-range morrison (:morrison constraints))
     (in-range turn (:turn constraints)))))

(defn choose-event [state]
  (let [{:keys [morrison turn]} state
        potentials (filter (partial does-apply? state) event-store)]
    (when-not (empty? potentials)
      (rand-nth potentials))))

(defn apply-event [state]
  (let [evt (choose-event state)
        effect (:effect evt)]
    (if effect
      (effect state)
      state)))

