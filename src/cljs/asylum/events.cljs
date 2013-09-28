(ns asylum.events)

(def s {:morrison 0.5 :turn 1 :population 100})

(def dummy-event
  {:constraints {:morrison [0 1]
                 :turn [1 6]}
   :effectss 1
   :effect (fn [state] (update-in state [:population] dec))})

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
  (let [{:keys [morrison turn]} state]
    (rand-nth (filter (partial does-apply? state) event-store))))

(defn apply-event [state]
  (let [evt (choose-event state)
        effect (:effect evt)]
    (effect state)))