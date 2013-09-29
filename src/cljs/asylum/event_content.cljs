(ns asylum.event-content
  (:require [asylum.event-engine :as ee]))

(def mou-naru
  {:title "Australian Government - MOU with Nauru"
   :media {:name "mou-naru.jpg" :type :image}
   :content "<p>The Australian Government could sign a new memorandum of understanding with Nauru similar to its Regional Resettlement Arrangement with Papua New Guinea. Asylum seekers who are transferred to Nauru for processing and found to be refugees could be settled in Nauru permanently.</p>"
   :event-date "3 Aug 2013"
   :links ["http://www.sbs.com.au/news/article/2013/08/04/australia-signs-asylum-deal-nauru"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "No action" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.01) :title "Review" :description "Review being a signatory to UN convention"}}})

(def int-covenant-breach
  {:title "Australia has breached the International Covenant on Civil and Political Rights by indefinitely detaining refugees who have failed security assessments"
   :media :image
   :content "<p>The UN Human Rights Committee finds that Australia has breached the International Covenant on Civil and Political Rights by indefinitely detaining refugees who have failed security assessments. It asserts that Australia has violated Articles 7 and 9 of the Convention through detaining refugees arbitrarily, failing to provide an effective judicial remedy and subjecting detainees to conditions of detention which are cumulatively inflicting serious psychological harm upon them . The Committee directs the Australian Government to provide all refugees in this situation with an effective remedy, including release from detention under appropriate conditions, rehabilitation and appropriate compensation.</p>"
   :event-date "25 and 26 July 2013"
   :links ["http://tbinternet.ohchr.org/Treaties/CCPR/Shared%20Documents/AUS/CCPR_C_108_D_2094_2011_20720_E.pdf" "http://tbinternet.ohchr.org/Treaties/CCPR/Shared%20Documents/AUS/CCPR_C_108_D_2136_2012_20721_E.pdf"]
   :constraints {:morrison [0.5 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "No action" :description "Accept the UN's findings"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Review" :description "Review being a signatory to UN convention"}}})

(def naru-riot
  {:title "Nauru Riot"
   :media {:name "nauru-riot.jpg" :type :image}
   :content "<p>A peaceful protest at the offshore processing facility in Nauru degenerates into a riot. Several buildings are destroyed and over 100 detainees are arrested. A group of present and former Salvation Army staff members who had worked in the Nauru facility release a public statement asserting that this riot although shocking, was an inevitable outcome from a cruel and degrading policy. They describe the constant degradation and suffering  experienced by detainees in the Nauru facility and claim that the riot was not borne out of malice was not borne out of malice. It was a build up of pressure and anxiety over ten months of degrading treatment, and a planned peaceful protest that degenerated. It was a reaction to a refugee processing system that is devoid of logic and fairness .</p>"
   :event-date "19 July 2013"
   :links ["http://www.refugeecouncil.org.au/n/mr/130723-Salvos-Nauru.pdf"] 
   :constraints {:morrison [0.5 1]
                 :turn [10 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Review current policies" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "No action" :description "Review  being a signatory to the UNHCR convention"}}})

(def png-resettlement
  {:title "Regional Resettlement Arrangement with Papua New Guinea"
   :media {:name "resettlement-agreement.jpg" :type :image}
   :content "<p>Prime Minister considers a new Regional Resettlement Arrangement with Papua New Guinea, whereby all asylum seekers arriving in Australia by boat will be transferred to Papua New Guinea for processing and, if they are found to be refugees, permanent settlement. They would not have the opportunity to seek asylum or settle in Australia.</p>"
   :event-date "19 July 2013"
   :links ["http://unhcr.org.au/unhcr/index.php?option=com_content&view=article&id=344:unhcr-australia-papua-new-guinea-asylum-agreement-presents-protection-challenges-&catid=35:news-a-media&Itemid=63"] 
   :constraints {:morrison [0.2 1]
                 :turn [4 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "No action" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Sign agreement" :description "All asylum seekers will be settled in PNG"}}})

(def unchr-report-manus-island
  {:title "UNHCR report on Manus Island"
   :media {:name "manus-island.jpg" :type :image}
   :content "<p>A second UNHCR report on conditions in the offshore processing facility on Manus Island acknowledges that, while there have been some positive developments, conditions remain below international standards for the reception and treatment of asylum-seekers . In particular, UNHCR expresses ongoing concern about the harsh physical living conditions, the mandatory and indefinite detention of asylum seekers, the lack of adequate safeguards in transfer arrangements and shortcomings in the legal framework for refugee status determination.</p>"
   :event-date "12 July 2013"
   :links ["http://unhcr.org.au/unhcr/files/2013-07-12_Manus_Island_Report_Final%281%29.pdf"] 
   :constraints {:morrison [0.4 1]
                 :turn [4 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Review" :description "Order a review of offshore facilities"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Ignore Report" :description "You consider the Australian government not being a signatury of the UNHCR convention"}}})

(def resumption-processing-asylum-claims
  {:title "Australian Government to resume processing of asylum claims"
   :media {:name "asylum-seekers.jpg" :type :image}
   :content "<p>The Government could resume processing of asylum claims for people who arrived by boat. By this point, the backlog stands at over 20,000.</p>"
   :event-date "3 July 2013"
   :links [] 
   :constraints {:morrison [0.3 1]
                 :turn [3 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Resume" :description "Resume processing claims"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Don't resume" :description "No claims are processed"}}})

(def parliamentary-joint-committee
  {:title "<p>Parliamentary Joint Committee on Human Rights"
     :content "The Parliamentary Joint Committee on Human Rights releases the report of its inquiry into changes introduced in response to the Expert Panel's recommendations. The Committee, which includes MPs and Senators from the Australian Labor Party, Liberal Party and the Australian Greens, concludes that the measures as currently implemented carry a significant risk of being incompatible with a range of human rights .</p>"
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
   :content "<p>The program could allow approved community organisations in Australia to propose humanitarian entrants for resettlement in Australia and help them to settle once they arrive. Organisations are expected to pay a substantial visa fee and meet a range of costs related to the person's resettlement in Australia (such as airfares, medical checks and on-arrival support).</p>"
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
   :content "<p>The Red Cross releases its inaugural Vulnerability Report, focusing on asylum seekers living in the Australian community under the no advantage  test. The Report reveals that many people seeking asylum live in the Australian community in a state of poverty, with many struggling to access adequate and sustainable housing and afford basics such as food, furniture and bedding. In launching the report, Red Cross CEO Robert Tickner says \"We are not talking about destitution here by normal standards. We're talking about what is by any measure among the most destitute people in the country, who are absolutely marginalised by every reasonable measure.\"</p>"
   :event-date "June 2013"
   :links ["http://www.redcross.org.au/files/20130614_Inaugural_Vulnerability_Report_LR.pdf"] 
   :constraints {:morrison [0.3 1]
                 :turn [3 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Accept report" :description "Address regugee's vulnerabilities"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Refute findings" :description "There is nothing wrong with the no advantage test"}}})

(def excision-policy 
  {:title "Extend excision policy to the Australian mainland"
   :media {:name "christmas-island.jpg" :type :image}
   :content "<p>The Australian Parliament could pass legislation which extends the excision policy to the Australian mainland. This means that asylum seekers who arrive by boat anywhere in Australia cannot lodge a valid protection claim except at the discretion of the Minister for Immigration, and are at risk of being transferred offshore for processing. Previously, the excision policy applied only to excised offshore places, such as Christmas Island.</p>"
   :event-date "16 May 2013"
   :links ["http://www.redcross.org.au/files/20130614_Inaugural_Vulnerability_Report_LR.pdf"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Reject" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Pass" :description "Pass excission policy legislation"}}})

(def full-federal-court-challange
  {:title "Full Federal Court finds that the International Treaty Obligations Assessment process is unlawful"
   :media {:name "federal-court.jpg" :type :image}
   :content "<p>In Minister for Immigration and Citizenship v SZQRB, the Full Federal Court finds that the International Treaty Obligations Assessment process (used by the Government to assess complementary protection needs) is not being carried out in accordance with the law. It also finds that the Minister's decision not to exercise his non-compellable public interest powers to grant the plaintiff a visa, irrespective of the merits of the case, denied the plaintiff procedural fairness in the most fundamental way. The Court issues an injunction preventing the deportation of the plaintiff until his claims have been assessed according to law. As a result, deportations of other asylum seekers in a similar position to the plaintiff are temporarily suspended.</p>"
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
   :content "<p>The Australian Government enter a new resettlemet deal with New Zealand, whereby 150 places from New Zealand's overall resettlement quota of 750 will be allocated to refugees who sought asylum in Australia. The refugees may be resettled directly from Australia or from offshore processing centres in Nauru and Manus Island.</p>"
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
   :content "<p>UNHCR releases a report on conditions in the offshore processing facility on Manus Island which similarly concludes that the transfer of asylum-seekers to unsatisfactory temporary facilities, within a closed detention setting, and in the absence of a legal framework and functional system to assess refugee claims, do not currently meet the required international protection standards . It also expresses concern about the situation of children transferred to Manus Island, noting that the lack of any appropriate legal or regulatory framework for their treatment (in what UNHCR finds to be a mandatory, arbitrary and indefinite detention setting), and on-going delays in establishing any procedures to assess children's refugee protection needs, and broader best interests, is particularly troubling .</p>"
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
   :content "<p>UNHCR releases a report on conditions in the offshore processing facility in Nauru. It concludes that the transfer of asylum-seekers to what are currently harsh and unsatisfactory temporary facilities, with in a closed detention setting, and in the absence of a fully functional legal framework and adequately capacitated system to assess refugee claims, do not currently meet the required protection standards. </p>"
   :event-date "14 December 2012"
   :links ["http://unhcr.org.au/unhcr/images/Amended%20footnote%202012-12-14%20nauru%20monitoring%20report%20final_2.pdf"] 
   :constraints {:morrison [0.2 1]
                 :turn [2 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Accept findings" :description "You accept the findings and seek to improve conditions"}
    :bad {:effect (ee/dsr-factor :morrison 0.07) :title "Ignore" :description "You choose to ignore the UNHCR report"}}})

(def no-advantage-test
  {:title "no advantage  test"
   :media {:name "no-advantage.jpg" :type :image}
   :content "<p>Following a significant increase in boat arrivals, the Australian Government considers not to transfer all asylum seekers who arrived by boat after today to offshore processing centres in the immediate future. As a result, some of these asylum seekers will be processed in Australia, but will still be subject to the no advantage  test. This would mean that:
   They will be released into the community on bridging visas but will not have the right to work.
   If they are found to be refugees, they will remain on bridging visas and will not receive a permanent visa until they have waited  for the same length of time that they would have waited, if they had applied for resettlement overseas.
   They may be transferred to Nauru or Manus Island at any time.</p>"
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
   :content "<p>Australia considers transferring asylum seekers, including families with young children, to the re-established offshore processing centre on Papua New Guinea's Manus Island.</p>"
   :event-date "21 November 2012"
   :links ["http://www.refugeeaction.org.au/?page_id=980"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Take no action" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Implement" :description "Start prossesing refugees in PNG"}}})

(def enhanced-screening
  {:title "Enhanced screening process for Sri Lankan asylum seekers"
   :media {:name "sri-lankan.jpg" :type :image}
   :content "<p>The Australian Government proposes to introduce an enhanced screening  process for Sri Lankan asylum seekers who arrive by boat. Under this process, an asylum seeker is interviewed by two officers from the Department of Immigration and Citizenship, about their reasons for travelling to Australia. If the asylum seeker raises concerns which suggest that they may have a valid protection claim, they are screened in  so that their claim can be formally processed. If they do not raise any protection concerns, they are screened out  and returned to Sri Lanka without having the opportunity to formally lodge a protection claim."
      :event-date "October 2012.</p>"
   :links ["http://www.humanrights.gov.au/sites/default/files/document/publication/enhanced-screening.pdf"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Reject proposal" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Implement enhanced screening" :description "Implement and start enhanced screening for Sri Lankan asylum seekers"}}})

(def implemenation-expert-panel
  {:title "Implementation of the Expert Panel's recommendations on humanitarian family reunion"
   :media {:name "family-reunion.jpg" :type :image}
   :content "<p>The Australian Government announces that it will consider the implemention of the Expert Panel's recommendations on humanitarian family reunion:
Refugees who arrived by boat before today (except for unaccompanied minors) will have to meet additional criteria if they wish to sponsor family members for resettlement under the Special Humanitarian Program, and their applications will be given the lowest processing priority.</p>
<ul>
<li>Refugees who arrived by boat after today will no longer be eligible to sponsor their family members for resettlement under the Special Humanitarian Program.</li>
<li>An additional 4,000 places are made available under the Family stream of the migration program for humanitarian entrants seeking to sponsor family members.</li></ul>"
   :event-date "22 September 2012"
   :links ["http://www.immi.gov.au/visas/humanitarian/family-reunion.htm"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Adopt recommendations" :description "You are in favour of family reunion"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Reject recommendations" :description ""}}})

(def nauru-transfer
  {:title "Offshore processing in Nauru"
   :media {:name "nauru.jpg" :type :image}
   :content "<p>Australia considers transferring asylum seekers to the re-established offshore processing centre in Nauru.</p>"
   :event-date "14 September 2012"
   :links [] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Reject" :description "You are not in favour of offshore processing"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Implement" :description ""}}})

(def increase-intake-20000
  {:title "Offshore processing in Nauru"
   :media {:name "asylum-seekers.jpg" :type :image}
   :content "<p>In line with the Expert Panel's recommendations, the Australian Government considers that the Refugee and Humanitarian Program will be increased to 20,000 places annually.</p>"
   :event-date "23 August 2012"
   :links [] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Adopt" :description "You are willing to increase the refugee intake by 20000"}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Reject" :description ""}}})

(def offshore-legislation
  {:title "Legislation to allow offshore processing of asylum seekers in Nauru and Papua New Guinea"
   :media {:name "australian-government.jpg" :type :image}
   :content "<p>The Australian Government considers introducing legislation to allow offshore processing of asylum seekers in Nauru and Papua New Guinea. It is passed by both houses of Parliament and becomes law. The legislation strips away legal safeguards for asylum seekers subject to offshore processing, including those which led the High Court to rule the Malaysia arrangement invalid. Any asylum seeker who arrived in Australia after today is now at risk of being processed offshore.</p>"
   :event-date "14 August 2012"
   :links [] 
   :constraints {:morrison [0 1]
                 :turn [1 3]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.02) :title "Reject" :description ""}
    :bad {:effect (ee/dsr-factor :morrison 0.05) :title "Adopt" :description "You pass the legislation that make offshore processing legal"}}})

(def expert-panel-recommendations
  {:title "Expert Panel recommendations"
   :media {:name "australian-government.jpg" :type :image}
   :content "<p>After six weeks of consultation and research, the Expert Panel releases a report containing 22 recommendations. These include:</p>
<ul><li>Working towards the development of a cooperative regional framework for improving protection and asylum systems;</li>
<li>Increasing Australia's Refugee and Humanitarian Program to 20,000 places annually;
Reintroducing offshore processing of asylum seekers in Nauru and Papua New Guinea's Manus Island; and</li>
<li>Changing Australia's humanitarian family reunion policies to make it more difficult for refugees who arrive by boat to reunite with their family members.</li>
<li>Central to the Expert Panel's recommendations is the \"no advantage\" test, which stipulates that refugees arriving by boat should not receive an \"advantage\" over refugees awaiting resettlement overseas. On this basis, the Panel proposes that asylum seekers who are processed offshore and found to be refugees should have to \"wait\" for resettlement for the same amount of time they would have waited if they had applied for resettlement from overseas. The Panel does not specify a \"waiting time\" or explain how a fair \"waiting time\" could be calculated.</li>
<li>On the same day, the Australian Government endorses in principle all 22 of the Expert Panel's recommendations. From this point, the processing of asylum claims made by people who arrived by boat after today is effectively suspended.</li></ul>"
   :event-date "14 August 2012"
   :links ["http://expertpanelonasylumseekers.dpmc.gov.au/report"] 
   :constraints {:morrison [0 1]
                 :turn [1 3]}
    :options
   {:good {:effect (ee/dsr-factor :morrison 0.05) :title "Adopt" :description "You adopt the recommendation"}
    :bad {:effect (ee/dsr-factor :morrison -0.05) :title "Reject" :description "You do not trust the expert panel"}}})

(def expert-panel-angus-houston
  {:title ""
   :media {:name "angus-houston.jpg" :type :image}
   :content "<p>Following the sinking of several boats en route to Australia and the resulting deaths of dozens of asylum seekers, Prime Minister considers appointing an Expert Panel to \"provide a report on the best way forward for Australia to prevent asylum seekers risking their lives on dangerous boat journeys to Australia\". The Panel is led by Air Chief Marshal Angus Houston, the former chief of Australia's defence force, and includes Paris Aristotle, Director of Foundation House, and Professor Michael L'Estrange, the Director of the National Security College at the Australian National University.</p>"
   :event-date "28 June 2012"
   :links [] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison 0.05) :title "Implement" :description "You appoint an expert panel"}
    :bad {:effect (ee/dsr-factor :morrison -0.05) :title "Take no action" :description "You reconsider and will not appoint an expert panel"}}})

(def joint-select-committee
  {:title "Joint Select Committee's findings"
   :media {:name "senate-entrance.jpg" :type :image}
   :content "<p>The Joint Select Committee on Australia's Immigration Detention Network releases its findings. Key recommendations include a 90-day time limit on detention for asylum seekers who pass inital identity, health, character and security checks; the publication of reasons for continued detention beyond 90 days; the replacement of the Minister for Immigration as the legal guardian of unaccompanied minors; using detention as a last resort for the shortest practicable time; and allowing the Administrative Appeals Tribunal to review negative ASIO assessments of refugees and asylum seekers. The report can be read here.</p>"
   :event-date "30 March 2012"
   :links ["http://www.aph.gov.au/Parliamentary_Business/Committees/Senate_Committees?url=immigration_detention_ctte/immigration_detention/report/index.htm"] 
   :constraints {:morrison [0 1]
                 :turn [1 100]}
    :options
   {:good {:effect (ee/dsr-factor :morrison 0.07) :title "Adopt" :description "You adopt the findings"}
    :bad {:effect (ee/dsr-factor :morrison -0.05) :title "Reject" :description "You reject the findings"}}})

(def single-processing-system
  {:title "Single Processing System"
   :media {}
   :content "<p>The single processing system for asylum seekers, regardless of their mode of arrival, comes info effect.</p>"
   :event-date "24 March 2012"
   :links [] 
   :constraints {:morrison [0 0.5]
                 :turn [1 2]}
    :options
   {:good {:effect (ee/dsr-factor :morrison 0.07) :title "Adopt" :description "You adopt a simple processing system"}
    :bad {:effect (ee/dsr-factor :morrison -0.05) :title "Reject" :description ""}}})

(def resettlement-workgroup
  {:title "Single Processing System"
   :media {:name "business-council.jpg" :type :image}
   :content "Australia considers hosting a meeting of the Working Group on Resettlement (WGR) in Melbourne. The meeting would bring together 87 leaders of government, NGO and UN refugee resettlement programs to discuss post-arrival settlement support for resettled refugees. It would be the first WGR meeting in the Southern Hemisphere and the first WGR meeting to focus exclusively on post-arrival support."
   :event-date "20-23 February 2012"
   :links [] 
   :constraints {:morrison [0 0.5]
                 :turn [1 10]}
    :options
   {:good {:effect (ee/dsr-factor :morrison 0.03) :title "Support" :description "You facilitate the meeting"}
    :bad {:effect (ee/dsr-factor :morrison -0.05) :title "Reject" :description "You have no interest meeting other world leaders"}}})

(def shp-visa-high-court-decision
  {:title "High Court desicion on SHP visas"
   :media {:name "fereral-court.jpg" :type :image}
   :content "The High Court of Australia rules that it was not a requirement for the grant of an Special Humanitarian Program (SHP) visa for the sponsor to be under 18 both at the time of application and the time of decision. The ruling removes the barrier to family reunion faced by unaccompanied minors who apply to sponsor their parents for resettlement but turn 18 before a decision is made on the application."
   :event-date "14 December 2011"
   :links [] 
   :constraints {:morrison [0 1]
                 :turn [1 10]}
    :options
   {:good {:effect (ee/dsr-factor :morrison 0.03) :title "Support" :description "You are ready to implement the High Courts decision"}
    :bad {:effect (ee/dsr-factor :morrison -0.03) :title "Appeal Decision" :description "You do not accept the court decision"}}})

(def unhcr-meeting-geneva
  {:title "UNHCR meeting in Geneva"
   :media {:name "un.jpg" :type :image}
   :content "UNHCR hosts a ministerial-level meeting in Geneva to commemorate the 60th anniversary of the 1951 Refugee Convention and the 50th anniversary of the 1961 Statelessness Convention. The meeting results in a number of significant breakthroughs on statelessness, with 20 countries pledging to ratify one or both of the two key Conventions on statelessness and 25 countries pledging to improve protections for stateless people."
   :event-date "7-8 December 2011"
   :links [] 
   :constraints {:morrison [0 1]
                 :turn [1 10]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "Implement Findings" :description "You are acknowledge the findings and recommendations of the UNHCR meeting"}
    :bad {:effect (ee/dsr-factor :morrison 0.03) :title "Ignore Findings" :description "You ignore the findings and implement your own policies"}}})

(def minister-announcement
  {:title "Announcment on Bridging Visas"
   :media {:name "un.jpg" :type :image}
   :content "Minister for Immigration and Citizenship considers announcing that the first group of asylum seekers in detention could soon be released on bridging visas. He also considers to announce that asylum claims will be processed under a single system regardless of whether asylum seekers arrive by boat or plane."
   :event-date "25 November 2011"
   :links [] 
   :constraints {:morrison [0 1]
                 :turn [1 10]}
    :options
   {:good {:effect (ee/dsr-factor :morrison -0.03) :title "No press release" :description "You will not proceed with the announcement"}
    :bad {:effect (ee/dsr-factor :morrison 0.03) :title "Announce" :description "You are instructing the minister to go ahead with the announcement"}}})



