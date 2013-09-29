(ns asylum.view
    (:use [jayq.core :only [$]]
          [jayq.util :only [log]])
    (:require [asylum.about-us-content :as about]))


(def map-selector "#map")

(defn- init-map 
       "Initialise the map background that forms the basis for the asylum UI"
       []
       (.gmap3 
         ($ map-selector)
         (clj->js {
                   :map (clj->js {
                                  :address "Darwin, NT, Australia"
                                  :options (clj->js {
                                                     :color "#476DD5"
                                                     :zoom 4
                                                     :mapTypeId js/google.maps.MapTypeId.ROADMAP,
                                                     :scrollwheel false
                                                     :center "-25.085875,104.284057"
                                                     :draggable false
                                                     :disableDefaultUI true
                                                     :disableDoubleClickZoom true
                                                     :styles 
                                                     [
                                                      (clj->js {
                                                                :featureType "administrative.province"
                                                                :elementType "labels.text"
                                                                :stylers [(clj->js { :visibility "off" })]
                                                                }),
                                                      (clj->js {
                                                                :featureType "administrative.locality"
                                                                :elementType "labels"
                                                                :stylers [(clj->js { :visibility "off" })]
                                                                }),
                                                      (clj->js {
                                                                :featureType "administrative.province"
                                                                :elementType "geometry"
                                                                :stylers [(clj->js { :visibility "off" })]
                                                                }),
                                                      (clj->js {
                                                                :featureType "water"
                                                                :elementType "labels.text"
                                                                :stylers [(clj->js { :visibility "off" })]
                                                                }),
                                                      (clj->js {
                                                                :featureType "administrative.country"
                                                                :elementType "labels"
                                                                :stylers [(clj->js { :visibility "off" })]
                                                                }),
                                                      (clj->js {
                                                                :featureType "administrative.country"
                                                                :elementType "geometry"
                                                                :stylers [(clj->js { :visibility "off" })]
                                                                }),
                                                      (clj->js {
                                                                :featureType "landscape.natural.landcover"
                                                                :elementType "geometry"
                                                                :stylers [(clj->js { :visibility "simplified" })]})]})})})))

(def possible-boat-coords
     [[-11.178402 111.870115]
      [-16.045813 114.506834]
      [-18.562947 112.924803]
      [-13.752725 90.351563]
      [-16.972741 100.195313]
      [-20.220966 113.540037]
      [-22.187405 109.76074]
      [-28.767659 85.429688]
      [-24.20689 111.708984]
      [-23.160563 160.839844]
      [-29.840644 160.913084]
      [-10.746969 84.814453]
      [-6.926427 152.841797]
      [-20.385825 105.893553]
      [-30.524413 108.969725]
      [-32.62087 108.706053]
      [-11.953349 117.846678]
      [-13.923404 122.153319]
      [-16.804541 119.956053]
      [-9.535749 129.272459]
      [-11.264612 124.877928]
      [-7.188101 118.989256]
      [-9.882275 134.545897]
      [-9.275622 138.852537]
      [-11.436955 146.147459]
      [-14.008696 149.926756]
      [-17.392579 149.926756]
      [-22.187405 153.442381]
      [-24.20689 154.057615]
      [-11.005904 155.727537]
      [-10.228437 142.456053]
      [-5.528511 100.532225]])

(def boats-actioned-during-turn
  (atom []))


(defn- kw-to-boat-action-label
       [kw]
       (kw {:sink "Sink"
            :turn-back "Turn back"
            :rescue "Rescue"
            :grant-citizenship "Grant citizenship"}))

(defn- boat-action-handler
       [boat action action-fn]
       (fn []
           (do 
             (log "Player chose to " action " " (:name boat))
           	 (.gmap3 
              	($ map-selector)
              	(clj->js {:clear (clj->js {:name ["infowindow"] :id (:name boat)})}))
           	(swap! boats-actioned-during-turn conj boat)
            (action-fn boat action))))

(defn- build-allowable-boat-actions 
       [boat boat-action-fn]
       (log "Number of boats saved this turn:" (count @boats-actioned-during-turn))
       (if (= 0 (count @boats-actioned-during-turn))
	       (to-array (map #(-> ($ "<button>") 
	            	(.click (boat-action-handler boat % boat-action-fn))
	            	(.text (kw-to-boat-action-label %))) 
	      		(:actions boat)))
        	(-> ($ "<p>") (.text "You can only action 1 boat per turn"))))

(defn- boat-info-window-content 
       [{:keys [name actions breakdown] :as boat} boat-action-fn]
       (let [total-passengers (apply + (map val breakdown))
             info-window-container ($ "<div>")
             header (-> ($ "<header>") 
                      	(.append (-> ($ "<h3>") (.text name)))
                       	(.append (-> ($ "<span>") (.text (str total-passengers "ppl")))))
             section (-> ($ "<section>")
                       	(.append (-> ($ "<span>") (.addClass "men") (.text (breakdown :men))))
                       	(.append (-> ($ "<span>") (.addClass "women") (.text (breakdown :women))))
                       	(.append (-> ($ "<span>") (.addClass "children") (.text (breakdown :children)))))
             footer (-> ($ "<footer>") 
                      	(.append (build-allowable-boat-actions boat boat-action-fn)))]
			(-> info-window-container (.addClass "boatInfo") (.append header) (.append section) (.append footer) (aget 0))))


(defn- boat-click-handler
      [boat boat-action-fn]
      (fn [marker event context]
           	(.gmap3 
              ($ map-selector)
              (clj->js {:clear (clj->js {:name ["infowindow"]})
                        :infowindow (clj->js 
                        	{:anchor marker 
                             :options (clj->js {:content (boat-info-window-content boat boat-action-fn)})})}))))

(defn- boat-marker 
      [boat-action-fn boat]
      (let [base-marker {:options {:icon "img/boatPin.png"} :id (:name boat)}
           	 action-handler {:events {:click (boat-click-handler boat boat-action-fn)}}]
           (merge base-marker action-handler)))

(defn- show-boats
       "Given a number of boats to display, randomly place them on a journey to australia"
       [{boats :boats} boat-action-fn]
       (let [boats-coords (take (count boats) (shuffle possible-boat-coords))
             boats-with-coords (zipmap boats-coords (map (partial boat-marker boat-action-fn) boats))
             boats (map #(clj->js (merge {:latLng (key %)} (val %))) boats-with-coords)]
            (do 
              (swap! boats-actioned-during-turn empty)
              (.gmap3 
	              ($ map-selector)
	              (clj->js {
	                        :clear (clj->js {:name ["marker"]})
	                        :marker (clj->js {:values (to-array boats)})})))))


(defn- say
       ([something]
        (say "greeting" something))
       ([id something]
        (.text ($ (str "#" id)) something)))

(defn- update-levers
       [state]
       (let [levers (:levers state)]
            (do 
              (.val ($ "#offshore-intake") (:offshore-intake levers))
              (.val ($ "#detention-proportion") (:detention-proportion levers)))))

(defn- update-gauges 
       [{popularity :popularity deaths :deaths}]
       (let [popularity (str (int (* 100 popularity)) "%")
             deaths-width (str (* 100 (/ (.log js/Math deaths) (.log js/Math 5200))) "%")]
            (-> ($ ".popularityValue") (.css "width" popularity) (.find "p") (.text popularity))
            (-> ($ ".deathsValue") (.css "width" deaths-width) (.find "p") (.text deaths))))


(defn- option-colour 
       [{morrison-index :morrison}]       
       (if (> morrison-index 1) "orange" "blue")) 


(def event-options-selected-during-turn
   (atom []))

(defmulti on-event-choice-selection key)
(defmethod on-event-choice-selection :continue [option]
           (-> ($ ".gaugesPanel") (.removeClass "inactive") (.addClass "active"))
           (-> ($ "#event-panel") (.removeClass "welcome"))
           :continue)
(defmethod on-event-choice-selection :dismiss [option]
	        (-> ($ "#event-panel") (.addClass "selected"))
	        (-> ($ ".endTurn") (.addClass "active") (.removeClass "inactive")))
(defmethod on-event-choice-selection :default [option]                
           (let [buttons ($ "#event-panel footer button")]
                (-> buttons  
                  (.each (fn [elem] 
	                      (this-as this
	                        (let [button ($ this)]
	                        	(when (not (= option (.data button "option"))) (.addClass button "notSelected")))))))
                (-> ($ "#event-panel") (.addClass "selected"))
                (-> ($ ".endTurn") (.addClass "active") (.removeClass "inactive"))
                (swap! event-options-selected-during-turn conj option)
                (key option)))


(defn- option-button
       [click-fn advance-turn-fn only-option? [option-kw {title :title effect :effect} :as option]]
       (let [morrison-index (effect {:morrison 1} 1)
             button-colour (option-colour morrison-index)
             base-on-click-handler (comp click-fn (partial on-event-choice-selection option))
             on-click-handler (if only-option? (comp advance-turn-fn base-on-click-handler) base-on-click-handler)]
            (-> ($ "<button>")
              (.addClass "button")
              (.addClass button-colour)
              (.text title)
              (.on "click" on-click-handler)
              (.data "option" option))))

(defn- trim-str
  [text size]
  (if (< (count text) size) text (str (subs text 0 size) "...")))

(defn- link
  [url]
  (-> ($ "<a>")
      (.attr "href" url)
      (.html (trim-str url 30))))

(defn- img
  [name]
  (-> ($ "<img>")
      (.attr "src" (str "img/" name))))

(defn- li
  [html]
  (-> ($ "<li>")
      (.html html)))

(defn- ul
  [html]
  (-> ($ "<ul>")
      (.html html)))

(defn- make-links
  [urls]
  (to-array (ul (to-array (map (fn [url] (li (to-array (link url)))) urls)))))

(defn- show-event 
       [{:keys [title content options media event-date links]} apply-event-choice-fn advance-turn-fn]
       (let [option-buttons (to-array (map (partial option-button apply-event-choice-fn advance-turn-fn (= 1 (count options))) options))
             content-div ($ "#event-panel")
             end-turn-button ($ ".endTurn")
             image-name (if (empty? (:name media)) "" (:name media))
             event-links (make-links links)
             header (if-not (empty? event-date) "Notes on actual events" "")]           
            (-> content-div (.removeClass "selected"))
            (-> content-div (.find "header h2") (.text title))
            (-> content-div (.find "section") (.html content))
            (-> content-div (.find "aside .image") (.empty) (.append (img image-name)))
            (-> content-div (.find "aside .date") (.html event-date))            (-> content-div (.find "aside .links") (.empty) (.append event-links))
            (-> content-div (.find "aside h3") (.html header))
            (-> content-div (.find "footer") (.empty) (.append option-buttons))
            (-> end-turn-button (.addClass "inactive") (.removeClass "active"))))

(defn- apply-end-turn-handler 
       [{options :options} advance-turn-fn]
       (let [end-turn-button ($ ".endTurn button")]
            (.off end-turn-button "click")
            (if (>= 1 (count options)) 
                (-> end-turn-button (.closest ".endTurn") (.addClass "inactive") (.removeClass "active"))
                (-> end-turn-button .show (.on "click" advance-turn-fn)))))
 		
(defn- apply-reset-handler
  [reset-fn]
  (let [reset-link ($ "#reset a")]
    (.off reset-link "click")
    (.on reset-link "click"
         (fn []
           (-> ($ ".gaugesPanel") (.removeClass "active") (.addClass "inactive"))
           (-> ($ "#event-panel") (.addClass "welcome"))
           (reset-fn))))) 


(defn- apply-about-us-handler
	[{current-event :next-event} orig-apply-option-fn orig-advance-fn]
  	(let [about-us-link ($ "#about-us a")]
        (swap! event-options-selected-during-turn empty)
	    (.off about-us-link "click")
	    (.on about-us-link "click"
	         (fn []
	           (show-event about/about-us
              			(fn [option] option)
                 		(fn [_] (when (not (seq @event-options-selected-during-turn)) (show-event current-event orig-apply-option-fn orig-advance-fn))))))))


(defn- lever-values 
       "Collect the lever values"
       []
       {:levers {:offshore-intake (-> (.val ($ "#offshore-intake")) js/parseInt)
                 :detention-proportion (-> (.val ($ "#detention-proportion"))  js/parseFloat)}})

(defn- init-get-user-media
       []
       (set! (.-getUserMedia js/navigator)
         (or (.-getUserMedia js/navigator)
           (.-webkitGetUserMedia js/navigator)
           (.-mozGetUserMedia js/navigator)
           (.-msGetUserMedia js/navigator))))

(defn- init-window-url
       []
       (set! (.-URL js/window)
         (or (.-URL js/window)
           (.-webkitURL js/window))))

(defn- get-user-media 
       [on-accept-fn on-reject-fn]
       (.getUserMedia js/navigator (clj->js {:video true :audio false}) on-accept-fn on-reject-fn))

(defn- init-player-avatar
       []
       (do (init-window-url)
           (init-get-user-media)
           (get-user-media 
             (fn [media-stream]
                 (let [video (-> ($ "<video>") (.attr "src" (-> js/window .-URL (.createObjectURL media-stream))))]
                      (do 
                        (-> ($ ".profile")
	                        (.empty)
	                        (.append video))
                        (.play (aget video 0)))))
             (fn []
                 (let [image (-> ($ "<img>") (.attr "src" "http://upload.wikimedia.org/wikipedia/commons/1/18/Scott_Morrison.jpg"))]
                      (-> ($ ".profile")
                        (.empty)
                        (.append image)))))))


(defmulti apply-turn-modifications :turn)
(defmethod apply-turn-modifications :default [state] nil)

(defmethod apply-turn-modifications 0 [state]
           (-> ($ ".endTurn") (.removeClass "active") (.removeClass "inactive")))
(defmethod apply-turn-modifications 1 [state]           
           (let [random-boat (rand-nth (:boats state))
                 marker (.gmap3 ($ map-selector) (clj->js {:get (clj->js {:id (:name random-boat)})}))]
           		(.gmap3 
	              ($ map-selector)
	              (clj->js {:clear (clj->js {:name ["infowindow"]})
	                        :infowindow (clj->js 
	                        	{:anchor marker 
	                             :options (clj->js 
                                         {:content "<p><strong>\"Help\" us!</strong><br/>Click on one of us during a turn and act as you see fit.</p>"})})}))))


(defn display [state apply-event-choice-fn advance-turn-fn reset-fn boat-action-fn]
      (do 
        (say "turn" (str (-> state :turn)))
        (show-boats state boat-action-fn)
        (update-levers state)
        (update-gauges state)
        (show-event (:next-event state) apply-event-choice-fn advance-turn-fn)
        (apply-end-turn-handler (:next-event state) advance-turn-fn)
        (apply-reset-handler reset-fn)
        (apply-about-us-handler state apply-event-choice-fn advance-turn-fn)
        (apply-turn-modifications state)))

;TODO: re-enable the player avatar once it works consistently on most browsers
($ init-map)
