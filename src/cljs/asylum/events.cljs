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

(def end-event-0
  {:constraints {:morrison [0 0.2] :turn [1 100]}
   :title "The game has ended"
   :content "I'm sorry Prime Minister, but you have just been removed from office.  With your popularity in free fall over the last months, your caucus colleagues have finally lost confidence in your ability to deliver them to office in the next election.  You will be remembered fondly by Meusli eating, sandal wearing greenies, but the rest of us think you're a spineless traitor who prefers foreign queue-jumping asylum seekers to your own true blue Australian people.  Good riddance."
   :options {}})

(def end-event-1
  {:constraints {:morrison [0.8 1] :turn [1 100]}
   :title "The game has ended"
   :content "Congratulations, your popularity with the Australian people is sky high, especially with the Southern Cross Tattoo contingent.  Your hard handed policies have resulted in international condemnation, cruelty that was unimaginable only a few years ago, and thousands of deaths.  But hey, you'll probably win the next election, so good on ya mate."
   :options {}})

(def event-store
  (atom #{end-event-0 end-event-1 dummy-event}))

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
