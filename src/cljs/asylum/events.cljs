(ns asylum.events)

(def s {:morrison 0.5 :turn 1 :population 100})

(def dummy-event
  {:constraints {:morrison [0 1]
                 :turn [1 6]}
   :effectss 1
   :effect (dsr-factor :population -0.2)})

(def event-store
  [dummy-event])

(defn in-range [n [x y]]
  (<= x n y))

(defn does-apply? [state evt]
  (let [{:keys [morrison turn]} state
        {constraints :constraints} evt]
    (and
     (in-range morrison (:morrison constraints))
     (in-range turn (:turn constraints)))))

(defn choose-event [state]
  (let [{:keys [morrison turn]} state]
    (rand-nth (filter (partial does-apply? state) event-store))))

(defn apply-event [state]
  (let [evt (choose-event state)
        effect (:effect evt)]
    (effect state)))

(defn- polynomial [& coeffiecients]
  (fn [x] (reduce + (map-indexed (fn [pow co] (* co (Math/pow x pow))) (reverse coeffiecients)))))

(defn- poly-factor [ks & coefficients]
  (let [ks (if (coll? ks) ks [ks])]
   (fn [system] (update-in system ks (apply polynomial coefficients)))))

(defn- linear-factor [ks factor]
  (poly-factor ks factor 0))

(defn- damped-step-response
  "Takes up to 2 arguments (mu, rho) and returns a damped step response function f(x) = mp * x * (1-e^(-rho*t) * (sin(mu*t +phi)/sin(phi)"
  ([]
     (damped-step-response 3 0.5))
  ([mu]
     (damped-step-response mu 0.5))
  ([mu rho]
     (fn [value, step] (let [phi (/ Math/PI 2)]
                 (-> (* (s :turn) mu)
                  (+ phi)
                  (Math/sin)
                  (/ (Math/sin phi)) 
                  (* (Math/exp (unchecked-negate (* rho (s :turn)))))
                  (unchecked-negate)
                  (+ 1)
                  (*  (* step value))
                  (+ value)))
       )))

(defn- dsr-factor [ks step & params]
    (let [ks (if (coll? ks) ks [ks])]
     (fn [system]
       (update-in system ks #((apply damped-step-response params) % step )))))
