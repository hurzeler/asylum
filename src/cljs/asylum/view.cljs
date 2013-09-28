(ns asylum.view
	(:use [jayq.core :only [$]]
		[jayq.util :only [log]]))



(defn init-map 
	"Initialise the map background that forms the basis for the asylum UI"
	[map-selector]
	(.gmap3 
		($ map-selector)
		(clj->js {
			:map (clj->js {
				:address "NT, australia"
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
						:stylers [(clj->js { :visibility "simplified" })]
						})
					]})})})))



(defn say
  ([something]
     (say "greeting" something))
  ([id something]
     (.text ($ (str "#" id)) something)))

(defn display [state]
  (say "turn" (str (-> state :turn))))

