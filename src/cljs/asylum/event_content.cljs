(ns asylum.event-content
  (:require [asylum.event-engine :as ee]))

(def mou-naru
  {:title "Australian Government could sign a new memorandum of understanding with Nauru"
   :media {:name "mou-naru.jpg" :type :image}
   :content "The Australian Government could sign a new memorandum of understanding with Nauru similar to its Regional Resettlement Arrangement with Papua New Guinea. Asylum seekers who are transferred to Nauru for processing and found to be refugees could be settled in Nauru permanently."
   :event-date "3 Aug 2013"
   :links ["http://www.sbs.com.au/news/article/2013/08/04/australia-signs-asylum-deal-nauru"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "No action" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Review" :description "Review beeing a signatory to UN convention"}}})

(def int-covenant-breach
  {:title "Australia has breached the International Covenant on Civil and Political Rights by indefinitely detaining refugees who have failed security assessments"
   :media :image
   :content "The UN Human Rights Committee finds that Australia has breached the International Covenant on Civil and Political Rights by indefinitely detaining refugees who have failed security assessments. It asserts that Australia has violated Articles 7 and 9 of the Convention through detaining refugees arbitrarily, failing to provide an effective judicial remedy and subjecting detainees to conditions of detention which are ¡°cumulatively inflicting serious psychological harm upon them¡±. The Committee directs the Australian Government to provide all refugees in this situation with an effective remedy, including release from detention under appropriate conditions, rehabilitation and appropriate compensation."
   :event-date "25 and 26 July 2013"
   :links ["http://tbinternet.ohchr.org/Treaties/CCPR/Shared%20Documents/AUS/CCPR_C_108_D_2094_2011_20720_E.pdf" "http://tbinternet.ohchr.org/Treaties/CCPR/Shared%20Documents/AUS/CCPR_C_108_D_2136_2012_20721_E.pdf"]
   :constraints {:morrison [0.5 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "No action" :description "Accept the UN's findings"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Review" :description "Review beeing a signatory to UN convention"}}})

(def naru-riot
  {:title "Nauru Riot"
   :media {:name "nauru-riot.jpg" :type :image}
   :content "A peaceful protest at the offshore processing facility in Nauru degenerates into a riot. Several buildings are destroyed and over 100 detainees are arrested. On 23 July, a group of present and former Salvation Army staff members who had worked in the Nauru facility release a public statement asserting that ¡°this riot although shocking, was an inevitable outcome from a cruel and degrading policy¡±. They describe the ¡°constant degradation and suffering¡± experienced by detainees in the Nauru facility and claim that the riot ¡°was not borne out of malice was not borne out of malice. It was a build up of pressure and anxiety over ten months of degrading treatment, and a planned peaceful protest that degenerated. It was a reaction to a refugee processing system that is devoid of logic and fairness¡±."
   :event-date "19 July 2013"
   :links ["http://www.refugeecouncil.org.au/n/mr/130723-Salvos-Nauru.pdf"] 
   :constraints {:morrison [0.5 1]
                 :turn [10 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Review current policies" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "No action" :description "Review beeing a signatory to the UNHCR convention"}}})

(def png-resettlement
  {:title "Regional Resettlement Arrangement with Papua New Guinea"
   :media {:name "resettlement-agreement.jpg" :type :image}
   :content "Prime Minister considers a new Regional Resettlement Arrangement with Papua New Guinea, whereby all asylum seekers arriving in Australia by boat will be transferred to Papua New Guinea for processing and, if they are found to be refugees, permanent settlement. They would not have the opportunity to seek asylum or settle in Australia."
   :event-date "19 July 2013"
   :links ["http://unhcr.org.au/unhcr/index.php?option=com_content&view=article&id=344:unhcr-australia-papua-new-guinea-asylum-agreement-presents-protection-challenges-&catid=35:news-a-media&Itemid=63"] 
   :constraints {:morrison [0.2 1]
                 :turn [4 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "No action" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Sign agreement" :description "All asylum seekers will be settled in PNG"}}})

(def unchr-report-manus-island
  {:title "UNCHR report on conditions in the offshore processing facility on Manus Island"
   :media {:name "manus-island.jpg" :type :image}
   :content "A second UNCHR report on conditions in the offshore processing facility on Manus Island acknowledges that, while there have been some positive developments, ¡°conditions remain below international standards for the reception and treatment of asylum-seekers¡±. In particular, UNHCR expresses ongoing concern about the harsh physical living conditions, the mandatory and indefinite detention of asylum seekers, the lack of adequate safeguards in transfer arrangements and shortcomings in the legal framework for refugee status determination."
   :event-date "12 July 2013"
   :links ["http://unhcr.org.au/unhcr/files/2013-07-12_Manus_Island_Report_Final%281%29.pdf"] 
   :constraints {:morrison [0.4 1]
                 :turn [4 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Review" :description "Order a review of offshore facilities"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Ignore Report" :description "You consider the Australian government not being a signatury of the UNHCR convention"}}})

(def resumption-processing-asylum-claims
  {:title "Australian Government could resume processing of asylum claims"
   :media {:name "asylum-seekers.jpg" :type :image}
   :content "The Government could resume processing of asylum claims for people who arrived by boat. By this point, the backlog stands at over 20,000."
   :event-date "3 July 2013"
   :links [] 
   :constraints {:morrison [0.3 1]
                 :turn [3 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Resume" :description "Resume processing claims"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Don't resume" :description "No claims are processed"}}})

(def parliamentary-joint-committee
  {:title "Parliamentary Joint Committee on Human Rights"
   :content "The Parliamentary Joint Committee on Human Rights releases the report of its inquiry into changes introduced in response to the Expert Panel¡¯s recommendations. The Committee, which includes MPs and Senators from the Australian Labor Party, Liberal Party and the Australian Greens, concludes that ¡°the measures as currently implemented carry a significant risk of being incompatible with a range of human rights¡±."
   :event-date "19 June 2013"
   :links ["http://www.aph.gov.au/Parliamentary_Business/Committees"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Accept report" :description "You review and implement the committee's recommenations"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "No Action" :description "You ignore the committee's recommenations"}}})

(def community-proposal-pilot
  {:title "Community Proposal Pilot"
   :media {:name "community-proposal-pilot.jpg" :type :image}
   :content "Community Proposal Pilot. The program could allow approved community organisations in Australia to propose humanitarian entrants for resettlement in Australia and help them to settle once they arrive. Organisations are expected to pay a substantial visa fee and meet a range of costs related to the person¡¯s resettlement in Australia (such as airfares, medical checks and on-arrival support)."
   :event-date "3 June 2013"
   :links ["http://www.immi.gov.au/visas/humanitarian/offshore/community-proposal-pilot.htm"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Accept proposal" :description "You will allow asylum seekers to enter the community"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Reject proposal" :description "You will try to keep the refugees out of the community"}}})

(def redcross-inaugural-vulnerability-report
  {:title "Red Cross Inaugural Vulnerability Report"
   :media {:name "redcross.png" :type :image}
   :content "The Red Cross releases its inaugural Vulnerability Report, focusing on asylum seekers living in the Australian community under the ¡°no advantage¡± test. The Report reveals that many people seeking asylum live in the Australian community in a state of poverty, with many struggling to access adequate and sustainable housing and afford basics such as food, furniture and bedding. In launching the report, Red Cross CEO Robert Tickner says ¡°We are not talking about destitution here by normal standards. We're talking about what is by any measure among the most destitute people in the country, who are absolutely marginalised by every reasonable measure.¡±"
   :event-date "June 2013"
   :links ["http://www.redcross.org.au/files/20130614_Inaugural_Vulnerability_Report_LR.pdf"] 
   :constraints {:morrison [0.3 1]
                 :turn [3 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Accept report" :description "Address regugee's vulnerabilities"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Try to refute findings" :description "There is nothing wrong with the no advantage test"}}})

(def excision-policy 
  {:title "Extend excision policy to the Australian mainland"
   :media {:name "christmas-island.jpg" :type :image}
   :content "The Australian Parliament could pass legislation which extends the excision policy to the Australian mainland. This means that asylum seekers who arrive by boat anywhere in Australia cannot lodge a valid protection claim except at the discretion of the Minister for Immigration, and are at risk of being transferred offshore for processing. Previously, the excision policy applied only to excised offshore places, such as Christmas Island."
   :event-date "16 May 2013"
   :links ["http://www.redcross.org.au/files/20130614_Inaugural_Vulnerability_Report_LR.pdf"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Reject" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Pass" :description "Pass excission policy legislation"}}})

