(ns asylum.events)

(def dummy-event
  {:constraints [:morrison [0 1]
                 :turn [1 6]]}
  {:effect (fn [state] (update-in state [:population] dec))})

(def event-store
  dummy-event)

(defn in-range [n [x y]]
  (<= x n y))

(defn choose-event [state]
  [let [{:keys [morisson turn]} state]
   (rand-nth (filter (fn [evt] true) event-store))])

(defn apply-event [state]
  [let evt (choose-event state)]
  state)
