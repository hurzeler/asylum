(ns asylum.event-engine)

(defn get-turn [state]
  (:turn state))

(defn- polynomial [& coeffiecients]
  (fn [x] (reduce + (map-indexed (fn [pow co] (* co (Math/pow x pow))) (reverse coeffiecients)))))

(defn- poly-factor [ks & coefficients]
  (let [ks (if (coll? ks) ks [ks])]
   (fn [system] (update-in system ks (apply polynomial coefficients)))))

(defn- linear-factor [ks factor]
  (poly-factor ks factor 0))

(defn- damped-step-response
  "Takes up to 2 arguments (mu, rho) and returns a damped step response function f(x) = mp * x * (1-e^(-rho*t) * (sin(mu*t +phi)/sin(phi)"
  ([state]
     (damped-step-response state 3 0.5))
  ([state mu]
     (damped-step-response state mu 0.5))
  ([state mu rho]
     (fn [value, step] (let [phi (/ Math/PI 2)]
                 (-> (* (get-turn state) mu)
                  (+ phi)
                  (Math/sin)
                  (/ (Math/sin phi)) 
                  (* (Math/exp (unchecked-negate (* rho (get-turn state)))))
                  (unchecked-negate)
                  (+ 1)
                  (*  (* step value))
                  (+ value))))))

(defn- dsr-factor [ks step & params]
    (let [ks (if (coll? ks) ks [ks])]
     (fn [state]
       (update-in state ks #((apply damped-step-response (cons state params)) % step )))))
