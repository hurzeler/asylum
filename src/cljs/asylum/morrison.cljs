(ns asylum.morrison)

(def fudge-factor 0.3)

(defn fudge [x]
  (let [diff (- (* fudge-factor (rand)) (* fudge-factor 0.5))
        x (+ x diff)]
    (max 0 (min 1 x))))

(defn linear [min-x max-x x min-y max-y]
  (let [delta-x (- max-x min-x)
        delta-y (- max-y min-y)
        prop (/ (- x min-x) delta-x)]
    (+ min-y (* prop delta-y))))

(defn popularity [x]
  (let [x (fudge x)]
    (cond
     (<= 0 x 0.75) (linear 0 0.75 x 0 1)
     (<= 0.75 x 1) (linear 0.75 1 x 1 0.8))))

(defn transient-deaths [x]
  (let [x (fudge x)]
   (cond
    (<= 0 x 0.2) (linear 0 0.2 x 0 10)
    (<= 0.2 x 0.6) (linear 0.2 0.6 x 10 200)
    (<= 0.6 x 0.8) (linear 0.6 0.8 x 200 50)
    (<= 0.8 x 1) (linear 0.8 1 x 50 0))))

(defn in-situ-deaths [x]
  (let [x (fudge x)]
    (* 5000 (.pow js/Math x 2))))

(defn deaths [x]
  (let [deaths (+ (transient-deaths x) (in-situ-deaths x))
        deaths (int (max 1 deaths))
;;        deaths-log (/ (.log js/Math deaths) (.log js/Math 5200))
        ]
    deaths))
