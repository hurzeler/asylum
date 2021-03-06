(ns asylum.events
  (:require [asylum.event-content :as ec]
            [asylum.event-content2 :as ec2]
            [asylum.event-engine :as ee]
            [asylum.morrison :as m]))

(def s {:morrison 0.5 :turn 1 :population 100})

(def dummy-event
  {:constraints {:morrison [0 1]
                 :turn [1 6]}
   :title "Dummy Event"
   :content "<p>Dummy dumb dumb!!!</p>"
   :links []
   :options
   {:good {:effect (ee/dsr-factor :morrison -0.2) :title "Good" :description "Very good stuff"}
    :bad {:effect (ee/dsr-factor :morrison 0.2) :title "Bad" :description "bad bad badness is herezzz"}}})

(def end-event-0
  {:constraints {:morrison [0 0.2] :turn [1 100]}
   :media {:name "end-0.jpg" :type :image}
   :links []
   :title "The game has ended"
   :content "<p>I'm sorry Prime Minister, but you have just been removed from office.  With your popularity in free fall over the last months, your caucus colleagues have finally lost confidence in your ability to deliver them to office in the next election.  You will be remembered fondly by Muesli eating, sandal wearing greenies, but the rest of us think you're a spineless traitor who prefers foreign queue-jumping asylum seekers to your own true blue Australian people.  Good riddance.</p>"
   :options {}})

(def end-event-1
  {:constraints {:morrison [0.8 1] :turn [1 100]}
   :media {:name "end-1.jpg" :type :image}
   :links []
   :title "The game has ended"
   :content "<p>Congratulations, your popularity with the Australian people is sky high, especially with the Southern-Cross-Tattoo contingent.  Your heavy handed policies have resulted in international condemnation, cruelty that was unimaginable only a few years ago, and thousands of deaths.  But hey, you'll probably win the next election, so good on ya mate.</p>"
   :options {}})

(def end-event-mid
  {:constraints {:morrison [0.2 0.8] :turn [20 100]}
   :media {:name "end-mid.jpg" :type :image}
   :links []
   :title "The game has ended"
   :content "<p>Well, it looks like you managed to muddle through your term, vacillating between policies and not really making a stand.  Your popularity remains flat as it seems the people prefer a strong leader with simple solutions.  Good luck in the next election.  That is, if you want to come back to this thankless job.</p>"
   :options {}})

(defn initial-events []
  #{end-event-0 end-event-1 end-event-mid
          ec2/operation-steel-fences
          ec/mou-naru
          ec/int-covenant-breach
          ec/naru-riot
          ec/png-resettlement
          ec/unchr-report-manus-island
          ec/resumption-processing-asylum-claims
          ec/parliamentary-joint-committee'
          ec/community-proposal-pilot
          ec/redcross-inaugural-vulnerability-report
          ec/excision-policy
          ec/full-federal-court-challange
          ec/resettlemet-deal-new-zealand
          ec/manus-island-unhcr-report
          ec/unhcr-nauru-report
          ec/no-advantage-test
          ec/offshore-processing-png
          ec/enhanced-screening
          ec/implemenation-expert-panel
          ec/nauru-transfer
          ec/increase-intake-20000
          ec/offshore-legislation})

(def event-store
  (atom (initial-events)))

(defn reset-events []
  (reset! event-store (initial-events)))

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

(defn cap-state-values [state]
  (assoc state :morrison (min 1 (max 0 (:morrison state)))))

(defn apply-single-effect [state [age effect]]
  (cap-state-values (effect state age)))

(defn age-effects [effects]
  (map (fn [[age effect]] [(inc age) effect]) effects))

(defn calculate-dependants [state]
  (let [morrison (:morrison state)
        popularity (m/popularity morrison)
        deaths (m/deaths morrison)]
    (merge state {:popularity popularity :deaths deaths})))

(defn apply-effects [state]
  (let [effects (:effects state)
        state (reduce apply-single-effect state effects)
        state (assoc state :effects (age-effects effects))]
    (calculate-dependants state)))

(defn apply-levers [state]
  state)
