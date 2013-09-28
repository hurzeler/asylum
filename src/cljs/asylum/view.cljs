(ns asylum.view
	(:use [jayq.core :only [$]]
		  [jayq.util :only [log]]))


(defn init-map 
	"Initialise the map background that forms the basis for the asylum UI"
	[map-selector]
	(.gmap3 
		($ map-selector)
		(clj->js {
			"options" (clj->js {
						"color" "#476DD5"
						"zoom" 4
						"mapTypeId" js/google.maps.MapTypeId.SATELLITE,
			        	"mapTypeControl" false
				        "navigationControl" false
				       	"scrollwheel" false
				        "streetViewControl" false
					})

			})
	))

