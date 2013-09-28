(ns asylum.view
    (:use [jayq.core :only [$]]
      [jayq.util :only [log]]))


(def map-selector "#map")

(defn init-map 
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

(def boat-marker-opts {:options {:icon "/img/boatPin.png"}})

(defn show-boats
      "Given a number of boats to display, randomly place them on a journey to australia"
      [num-boats]
      (let [boats-coords (take num-boats (shuffle possible-boat-coords))
            boats (map #(clj->js (merge {:latLng %} boat-marker-opts)) boats-coords)]
           (.gmap3 
             ($ map-selector)
             
             (clj->js {
                       :clear (clj->js {:name ["marker"]})
                       :marker (clj->js {:values boats})
                       }))))


(defn say
      ([something]
       (say "greeting" something))
      ([id something]
       (.text ($ (str "#" id)) something)))

(defn display [state]
      (do 
        (say "turn" (str (-> state :turn)))
        (show-boats (-> state :current :transit))))

