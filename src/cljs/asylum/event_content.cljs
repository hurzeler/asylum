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

(def full-federal-court-challange
  {:title "Full Federal Court finds that the International Treaty Obligations Assessment process unlawful"
   :media {:name "federal-court.jpg" :type :image}
   :content "In Minister for Immigration and Citizenship v SZQRB, the Full Federal Court finds that the International Treaty Obligations Assessment process (used by the Government to assess complementary protection needs) is not being carried out in accordance with the law. It also finds that the Minister¡¯s decision not to exercise his non-compellable public interest powers to grant the plaintiff a visa, irrespective of the merits of the case, denied the plaintiff procedural fairness ¡°in the most fundamental way¡±. The Court issues an injunction preventing the deportation of the plaintiff until his claims have been assessed according to law. As a result, deportations of other asylum seekers in a similar position to the plaintiff are temporarily suspended."
   :event-date "20 March 2013"
   :links ["http://www.austlii.edu.au/au/cases/cth/FCAFC/2013/33.html"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Accept findings" :description "You accept the courts findings and seek to implement changes"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Appeal" :description "You fight the courts decision"}}})

(def resettlemet-deal-new-zealand
  {:title "Resettlemet deal with New Zealand"
   :media {:name "nz-refugees.jpg" :type :image}
   :content "The Australian Government enter a new resettlemet deal with New Zealand, whereby 150 places from New Zealand¡¯s overall resettlement quota of 750 will be allocated to refugees who sought asylum in Australia. The refugees may be resettled directly from Australia or from offshore processing centres in Nauru and Manus Island."
   :event-date "9 February 2013"
   :links ["http://www.immigration.govt.nz/migrant/general/generalinformation/media/refugeefactsheet.htm"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.01) :title "Accept" :description "You accept the resettlement deal with New Zealand"}
    :bad {:effect (ee/dsr-factor :morrison 0.02) :title "Reject" :description "You reject the deal"}}})

(def manus-island-unhcr-report
  {:title "Manus Island UNHCR report"
   :media {:name "manus-island-2.jpg" :type :image}
   :content "UNHCR releases a report on conditions in the offshore processing facility on Manus Island which similarly concludes that ¡°the transfer of asylum-seekers to unsatisfactory temporary facilities, within a closed detention setting, and in the absence of a legal framework and functional system to assess refugee claims, do not currently meet the required international protection standards¡±. It also expresses concern about the situation of children transferred to Manus Island, noting that ¡°the lack of any appropriate legal or regulatory framework for their treatment (in what UNHCR finds to be a mandatory, arbitrary and indefinite detention setting), and on-going delays in establishing any procedures to assess children¡¯s refugee protection needs, and broader best interests, is particularly troubling¡±."
   :event-date "4 February 2013"
   :links ["http://unhcr.org.au/unhcr/images/2013-02-04%20Manus%20Island%20Report%20Final.pdf"] 
   :constraints {:morrison [0.3 1]
                 :turn [3 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Accept findings" :description "You accept the findings and seek to improve conditions"}
    :bad {:effect (ee/dsr-factor :morrison 0.07) :title "Ignore" :description "You choose to ignore the UNHCR report"}}})

(def unhcr-nauru-report
  {:title "UNHCR Nauru condition report"
   :media {:name "nauru.jpg" :type :image}
   :content "UNHCR releases a report on conditions in the offshore processing facility in Nauru. It concludes that ¡°the transfer of asylum-seekers to what are currently harsh and unsatisfactory temporary facilities, with in a closed detention setting, and in the absence of a fully functional legal framework and adequately capacitated system to assess refugee claims, do not currently meet the required protection standards.¡±"
   :event-date "14 December 2012"
   :links ["http://unhcr.org.au/unhcr/images/Amended%20footnote%202012-12-14%20nauru%20monitoring%20report%20final_2.pdf"] 
   :constraints {:morrison [0.2 1]
                 :turn [2 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Accept findings" :description "You accept the findings and seek to improve conditions"}
    :bad {:effect (ee/dsr-factor :morrison 0.07) :title "Ignore" :description "You choose to ignore the UNHCR report"}}})

(def no-advantage-test
  {:title "¡°no advantage¡± test"
   :media {:name "no-advantage.jpg" :type :image}
   :content "Following a significant increase in boat arrivals, the Australian Government considers not to transfer all asylum seekers who arrived by boat after today to offshore processing centres in the immediate future. As a result, some of these asylum seekers will be processed in Australia, but will still be subject to the ¡°no advantage¡± test. This would mean that:
They will be released into the community on bridging visas but will not have the right to work.
If they are found to be refugees, they will remain on bridging visas and will not receive a permanent visa until they have ¡°waited¡± for the same length of time that they would have waited, if they had applied for resettlement overseas.
They may be transferred to Nauru or Manus Island at any time."
   :event-date "21 November 2012"
   :links ["http://www.refugeecouncil.org.au/n/mr/121121_noadvantage.pdf"] 
   :constraints {:morrison [0.2 1]
                 :turn [2 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Take no action" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.07) :title "Introduce no-advantage test" :description "You accept that a no-advantage test is necessary"}}})

(def offshore-processing-png
  {:title "Offshore processing in PNG"
   :media {:name "png.jpg" :type :image}
   :content "Australia considers transferring asylum seekers, including families with young children, to the re-established offshore processing centre on Papua New Guinea¡¯s Manus Island."
   :event-date "21 November 2012"
   :links ["http://www.refugeeaction.org.au/?page_id=980"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Take no action" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Implement" :description "Start prossesing refugees in PNG"}}})

(def enhanced-screening
  {:title "¡°enhanced screening¡± process for Sri Lankan asylum seekers who arrive by boat"
   :media {:name "sri-lankan.jpg" :type :image}
   :content "The Australian Government proposes to introduce an ¡°enhanced screening¡± process for Sri Lankan asylum seekers who arrive by boat. Under this process, an asylum seeker is interviewed by two officers from the Department of Immigration and Citizenship, about their reasons for travelling to Australia. If the asylum seeker raises concerns which suggest that they may have a valid protection claim, they are ¡°screened in¡± so that their claim can be formally processed. If they do not raise any protection concerns, they are ¡°screened out¡± and returned to Sri Lanka without having the opportunity to formally lodge a protection claim."
   :event-date "October 2012"
   :links ["http://www.humanrights.gov.au/sites/default/files/document/publication/enhanced-screening.pdf"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Reject proposal" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Implement enhanced screening" :description "Implement and start enhanced screening for Sri Lankan asylum seekers"}}})

