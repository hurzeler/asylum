(ns asylum.boats
  (:require [clojure.set :as s])
  (:use [jayq.util :only [log]]))

(def countries
  #{"Eritrea" "Ethiopia" "Somalia" "Sudan" "Ivory Coast" "Liberia" "Nigeria" "Sierra Leone"
    "Togo" "Algeria" "Western Sahara" "Central African Republic" "Republic of Congo" "Rwanda"
    "Uganda" "Angola" "Zimbabwe" "Iraq" "Afghanistan" "Iran" "Lebanon" "Palestine" "Kazakhstan"
    "Turkmenistan" "Uzbekistan" "Chechnya" "Belarus" "Ukraine" "Turkey" "Bangladesh" "Nepal"
    "Pakistan" "Sri Lanka" "Tibet" "Burma" "China" "Indonesia" "Laos" "North Korea" "Vietnam"})

(defn rand-countries []
  (let [n (inc (rand-int 3))]
    (reduce (fn [acc _] (conj acc (rand-nth (s/difference countries acc)))) #{} (range n))))

(defn rand-breakdown []
  (let [n (+ 50 (rand-int 101))
        children-percentage (+ 50 (rand-int 20))
        woman-percentage (+ 45 (rand-int 10))
        children (int (* children-percentage 0.01 n))
        adults (- n children)
        women (int (* adults 0.01 woman-percentage))]
    {:children children 
     :women women
     :men (- adults women)}))

(defn rand-boat-id []
  (str "SIV-" (apply str (rest (str (+ 10000 (rand-int 10000)))))))

(defn rand-boat []
  {:name (rand-boat-id)
   :countries (rand-countries)
   :breakdown (rand-breakdown)})

(defn random-boats [morrison]
  (let [n (cond
           (<= 0 morrison 0.2) (rand-int 2)
           (<= 0.2 morrison 0.8) (inc (rand-int 5))
           :else (rand-int 2))]
    (map (fn [_] (rand-boat)) (range n))))

(defn add-boats [state]
  (let [m (:morrison state)
        boats (set (random-boats m))]
    (assoc state :boats boats)))
