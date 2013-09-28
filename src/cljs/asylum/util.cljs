(ns asylum.util)

(defn ->js-obj
	"Convert a clojure map to a javascript object.
	Ripped off from: https://gist.github.com/mjg123/1098417"
	[m]
	(when m 
		(let [out (js-obj)]
			(doall 
				(map #(aset out (name (first %)) (second %)) m))
			out)))