(ns asylum.event-content2
  (:require [asylum.event-engine :as ee]))

(def operation-steel-fences
  {:title "Operation Steel Fences"
   :media {:name "op-steel.jpg" :type :image}
   :content "<p>Your political adviser suggests that militarising the asylym seeker issue can help your popularity and give you an excuse to control the media message.  Operation Steel Fences would hand over control of executing your policy to a military commander and position asylum seekers as an enemy of the state.</p>"
   :event-date "14 Sep 2013"
   :links ["http://www.sbs.com.au/news/article/2013/09/19/factbox-operation-sovereign-borders"]
   :constraints {:morrison [0.4 0.8] :turn [1 100]}
   :options
   {:enact {:effect (ee/dsr-factor :morrison 0.1) :title "Enact" :description ""}
    :reject {:effect (ee/dsr-factor :morrison -0.01) :title "Reject" :description ""}}})

