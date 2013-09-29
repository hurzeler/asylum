(ns asylum.view
    (:use [jayq.core :only [$]]
          [jayq.util :only [log]]))


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
                                                     :zoom 5
                                                     :mapTypeId js/google.maps.MapTypeId.ROADMAP,
                                                     :scrollwheel false
                                                     :center "-7.188101,133.139647"
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

(defn- kw-to-boat-action-label
       [kw]
       (kw {:sink "Sink"
            :turn-back "Turn back"
            :Rescue "Rescue"
            :grant-citizenship "Grant citizenship"
            }))


(defn- boat-action-handler
       [action action-fn]
       (fn []
           (.gmap3 
              ($ map-selector)
              (clj->js {:clear (clj->js {:name ["infowindow"]})}))
           (action-fn action)))

(defn- boat-info-window-content 
       [boat boat-action-fn]
       (let [total-passengers (apply + (map val (:breakdown boat)))
             info-window-container ($ "<div>")
             header (-> ($ "<header>") 
                      	(.append (-> ($ "<h3>") (.text (:name boat))))
                       	(.append (-> ($ "<span>") (.text (str total-passengers "ppl")))))
             section (-> ($ "<section>")
                       	(.append (-> ($ "<span>") (.addClass "men") (.text (-> boat :breakdown :men))))
                       	(.append (-> ($ "<span>") (.addClass "women") (.text (-> boat :breakdown :women))))
                       	(.append (-> ($ "<span>") (.addClass "children") (.text (-> boat :breakdown :children)))))             
             footer (-> ($ "<footer>") 
                      	(.append 
                         	(to-array (map #(-> ($ "<button>") 
                                            	(.text (kw-to-boat-action-label %)) 
                                             	(.on "click" (boat-action-handler % boat-action-fn))) 
                                      		(:actions boat)))))]
			(-> info-window-container (.append header) (.append section) (.append footer) .html)))


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
      (let [base-marker {:options {:icon "img/boatPin.png"}}
           	 action-handler {:events {:click (boat-click-handler boat boat-action-fn)}}]
           (merge base-marker action-handler)))

(defn- show-boats
       "Given a number of boats to display, randomly place them on a journey to australia"
       [{boats :boats} boat-action-fn]
       (let [boats-coords (take (count boats) (shuffle possible-boat-coords))
             boats-with-coords (zipmap boats-coords (map (partial boat-marker boat-action-fn) boats))
             boats (map #(clj->js (merge {:latLng (key %)} (val %))) boats-with-coords)]
            (.gmap3 
              ($ map-selector)
              (clj->js {
                        :clear (clj->js {:name ["marker"]})
                        :marker (clj->js {:values (to-array boats)})}))))


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

(defmulti on-event-choice-selection key)
(defmethod on-event-choice-selection :continue [option]
           (-> ($ ".gaugesPanel") (.removeClass "inactive") (.addClass "active"))
           (-> ($ "#event-panel") (.removeClass "welcome"))
           :continue)
(defmethod on-event-choice-selection :default [option]                
           (let [buttons ($ "#event-panel footer button")]
                (-> buttons  
                  (.each (fn [elem] 
	                      (this-as this
	                        (let [button ($ this)]
	                        	(when (not (= option (.data button "option"))) (.addClass button "notSelected")))))))
                (-> ($ "#event-panel") (.addClass "selected"))
                (-> ($ ".endTurn") (.addClass "active") (.removeClass "inactive"))
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

(defn- show-event 
       [{:keys [title content options media]} apply-event-choice-fn advance-turn-fn]
       (let [option-buttons (to-array (map (partial option-button apply-event-choice-fn advance-turn-fn (= 1 (count options))) options))
             content-div ($ "#event-panel")
             end-turn-button ($ ".endTurn")
             image (if (empty? (:name media)) "" (str "img/" (:name media)) )]           
            (-> content-div (.removeClass "selected"))
            (-> content-div (.find "header h2") (.text title))
            (-> content-div (.find "section") (.html content))             
            (-> content-div (.find "aside") (.find "img") (.attr "src" image))
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
  (let [reset-link ($ ".reset a")]
    (.off reset-link "click")
    (.on reset-link "click"
         (fn []
           (-> ($ ".gaugesPanel") (.removeClass "active") (.addClass "inactive"))
           (-> ($ "#event-panel") (.addClass "welcome"))
           (reset-fn))))) 

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


(defn display [state apply-event-choice-fn advance-turn-fn reset-fn boat-action-fn]
      (do 
        (say "turn" (str (-> state :turn)))
        (show-boats state boat-action-fn)
        (update-levers state)
        (update-gauges state)
        (show-event (:next-event state) apply-event-choice-fn advance-turn-fn)
        (apply-end-turn-handler (:next-event state) advance-turn-fn)
        (apply-reset-handler reset-fn)))

($ (comp init-map init-player-avatar))
