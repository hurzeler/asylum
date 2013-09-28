(ns asylum.state)

(def state
  (atom {:turn 0
         :morrison 0.5
         :current {:arrivals 250
                   :deaths 10
                   :transit 3}
         :accumulated {:deported 0
                       :offshore 0
                       :detention 0
                       :community 0}
         :policies []
         :levers {:offshore-intake 1000
                  :detention-proportion 0.5}}))
