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
				:address "1 macquarie st, sydney"
				:options (clj->js {
							:color "#476DD5"
							:zoom 4
							:mapTypeId js/google.maps.MapTypeId.ROADMAP,
					       	:scrollwheel false
					        :center "-25.085875,134.284057"
					        :draggable false
					        :disableDefaultUI true})})})))

