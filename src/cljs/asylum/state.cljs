(ns asylum.state)

(def state
  (atom {:turn 1
         :morrison 0.5
         :current {:arrivals 250
                   :deaths 10
                   :transit 3}
         :accumulated {:deported 0
                       :offshore 0
                       :detention 0
                       :community 0}
         :policies []
         :effects []
         :levers {:offshore-intake 1000
                  :detention-proportion 0.5}
         :next-event {:title "Welcome to Asylum"
                      :content "Congratulations on your elevation to the office of Prime Minister.  Asylum is a game intended to demonstrate the difficult problem of asylum policy in the West, and specifically in Australia.  Since 2008, more than 31,000 asylum seekers arrived by boat in Australia, with a further 870 drowning during the journey.
The asylum seekers issue was a dominant issue during the entire reign of the last government and was one of the major causes of its demise in the polls on September 14th, 2013.  
We invite you, the player, to sit in the hot seat of asylum policy.  As Prime Minister of Australia, you will need to determine asylum seeker policy and will have to juggle the effects on your popularity, your commitment to humanitarian standards and your basic humanity.  Be warned, the effort is fraught, and you aren't likely to succeed.
Asylum is a turn-based game, each turn representing a month.  You will be shown a major event that happened in the past month, and the accumulated statistics.  You will be able to change some policy levers, and even bring into effect entire new policies avery once in a while.  Will you be able to survive in office without sacrificing your values?
Good luck Prime Minister, you're going to need it!"
                      :options {:continue {:effect (fn [s _] s) :title "Play now!"}}}}))
