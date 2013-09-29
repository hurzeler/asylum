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
                                  :address "Ghan, NT, Australia"
                                  :options (clj->js {
                                                     :color "#476DD5"
                                                     :zoom 4
                                                     :mapTypeId js/google.maps.MapTypeId.ROADMAP,
                                                     :scrollwheel false
                                                     :center "-25.085875,134.284057"
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
      [-20.220966 113.540037]
      [-22.187405 109.76074]
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

(def boat-marker-opts {:options {:icon "img/boatPin.png"}})

(defn- show-boats
       "Given a number of boats to display, randomly place them on a journey to australia"
       [num-boats]
       (let [boats-coords (take num-boats (shuffle possible-boat-coords))
             boats (map #(clj->js (merge {:latLng %} boat-marker-opts)) boats-coords)]
            (.gmap3 
              ($ map-selector)
              (clj->js {
                        :clear (clj->js {:name ["marker"]})
                        :marker (clj->js {:values boats})}))))


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
            (-> ($ ".popularityValue") (.text popularity) (.css "width" popularity))
            (-> ($ ".deathsValue") (.text deaths) (.css "width" deaths-width))))


(defn- option-colour 
       [{morrison-index :morrison}]       
       (if (> morrison-index 1) "orange" "blue")) 

(defmulti on-event-choice-selection key)

(defmethod on-event-choice-selection :continue [option]
           (-> ($ ".gaugesPanel") (.removeClass "inactive") (.addClass "active"))
           :continue)

(defmethod on-event-choice-selection :default [option] (key option))


(defn- option-button
       [click-fn [option-kw {title :title effect :effect} :as option]]
       (let [morrison-index (effect {:morrison 1} 1)
             button-colour (option-colour morrison-index)]
            (-> ($ "<button>")
              (.addClass "button")
              (.addClass button-colour)
              (.text title)
              (.on "click" (comp click-fn (partial on-event-choice-selection option))))))

(defn- show-event 
       [{:keys [title content options]} apply-event-choice-fn]
       (let [option-buttons (to-array (map (partial option-button apply-event-choice-fn) options))
             content-div ($ "#event-panel")]           
            (-> content-div (.find "header h2") (.text title))
            (-> content-div (.find "section") (.html content))
            (-> content-div (.find "footer") (.empty) (.append option-buttons))))

(defn display [state apply-event-choice-fn]
      (do 
        (say "turn" (str (-> state :turn)))
        (show-boats (-> state :current :transit))
        (update-levers state)
        (update-gauges state)
        (show-event (:next-event state) apply-event-choice-fn)))


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


($ (comp init-map init-player-avatar))
