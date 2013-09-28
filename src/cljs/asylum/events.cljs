(ns asylum.events
  (:require [asylum.event-content :as ec]
            [asylum.event-engine :as ee]))

(def s {:morrison 0.5 :turn 1 :population 100})

(def dummy-event
  {:constraints {:morrison [0 1]
                 :turn [1 6]}
   :options
   {:good {:effect (ee/dsr-factor :morrison -0.2) :title "Good" :description "Very good stuff"}
    :bad {:effect (ee/dsr-factor :morrison 0.2) :title "Bad" :description "Shit fuck crap"}}})

(def event-store
  (atom #{dummy-event}))

(defn in-range [n [x y]]
  (<= x n y))

(defn does-apply? [state evt]
  (let [{:keys [morrison turn]} state
        {constraints :constraints} evt]
    (and
     (in-range morrison (:morrison constraints))
     (in-range turn (:turn constraints)))))

(defn choose-event [state]
  (let [potentials (filter (partial does-apply? state) @event-store)]
    (let [chosen (when-not (empty? potentials)
                   (rand-nth potentials))]
      (swap! event-store disj chosen)
      (when chosen
        {:next-event chosen}))))

(defn apply-event-choice [state choice]
  (let [effect (get-in state [:next-event :options choice :effect])]
    (update-in state [:effects] conj [1 effect])))

(defn apply-single-effect [state [age effect]]
  (effect state age))

(defn age-effects [effects]
  (map (fn [[age effect]] [(inc age) effect]) effects))

(defn apply-effects [state]
  (let [effects (:effects state)
        state (reduce apply-single-effect state effects)]
    (assoc state :effects (age-effects effects))))

(defn apply-levers [state]
  state)
