(ns asylum.event-content
  (:require [asylum.event-engine :as ee]))

(def dummy-event
  {:constraints {:morrison [0 1]
                 :turn [1 6]}
   :effectss 1
   :effect (ee/dsr-factor :population -0.2)})

(def mou-naru
  {:title "Australian Government signs a new memorandum of understanding with Nauru"
   :media {:name mou-naru.jpg :type image}
   :content "The Australian Government signs a new memorandum of understanding with Nauru similar to its Regional Resettlement Arrangement with Papua New Guinea. Asylum seekers who are transferred to Nauru for processing and found to be refugees could be settled in Nauru permanently."
   :event-date "3 Aug 2013"
   :link "http://www.sbs.com.au/news/article/2013/08/04/australia-signs-asylum-deal-nauru"
   :constraints {:morrison [0 1]
                 :turn [1 6]}
   :effectss 1
   :effect (ee/dsr-factor :population -0.2)})
