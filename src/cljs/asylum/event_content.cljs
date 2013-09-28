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
   :links ["http://www.sbs.com.au/news/article/2013/08/04/australia-signs-asylum-deal-nauru"] 
   :constraints {:morrison [0 1]
                 :turn [1 6]}
   :effectss 1
   :effect (ee/dsr-factor :population -0.2)})

(def int-covenant-breach
  {:title "Australia has breached the International Covenant on Civil and Political Rights by indefinitely detaining refugees who have failed security assessments"
   :media {:name mou-naru.jpg :type image}
   :content "The UN Human Rights Committee finds that Australia has breached the International Covenant on Civil and Political Rights by indefinitely detaining refugees who have failed security assessments. It asserts that Australia has violated Articles 7 and 9 of the Convention through detaining refugees arbitrarily, failing to provide an effective judicial remedy and subjecting detainees to conditions of detention which are ¡°cumulatively inflicting serious psychological harm upon them¡±. The Committee directs the Australian Government to provide all refugees in this situation with an effective remedy, including release from detention under appropriate conditions, rehabilitation and appropriate compensation."
   :event-date "25 and 26 July 2013"
   :links [http://tbinternet.ohchr.org/Treaties/CCPR/Shared%20Documents/AUS/CCPR_C_108_D_2094_2011_20720_E.pdf http://tbinternet.ohchr.org/Treaties/CCPR/Shared%20Documents/AUS/CCPR_C_108_D_2136_2012_20721_E.pdf]
   :constraints {:morrison [0 1]
                 :turn [1 6]}
   :effectss 1
   :effect (ee/dsr-factor :population -0.2)})

