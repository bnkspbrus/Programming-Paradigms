;(ns linear)
;(defn m*m [& ms]
;  (loop [mat (first ms) oth (rest ms)]
;    (let [next (transpose (first oth))]
;      (recur (mapv scalar mat next) (rest oth)))))

;(defn mat_op [f ms]
;  (loop [mat (first ms) oth (rest ms)]
;    (if (empty? oth)
;      mat
;      (recur (mapv (fn [a b] (v_op f [a b])) mat (first oth)) (rest oth)))))

(defn vec_op [f]
  (fn [& vs]
    (reduce (fn [a b] (mapv f a b)) (first vs) (rest vs))))

(def v+ (vec_op +))

(def v- (vec_op -))

(def v* (vec_op *))

(def vd (vec_op /))

(defn transpose [m]
  (apply mapv vector m))

(defn scalar [& vs]
  (let [fact (apply v* vs)]
    (reduce + 0 fact)))

(defn v*s [vec s]
  (mapv (fn [a] (* a s)) vec))

(defn mat_op [f]
  (vec_op (vec_op f)))

(def m+ (mat_op +))

(def m- (mat_op -))

(def m* (mat_op *))

(def md (mat_op /))

(defn m*s [mat s]
  (mapv (fn [v] (v*s v s)) mat))

(defn m*v [mat vec]
  (mapv (fn [v] (scalar v vec)) mat))

(defn m*m [& ms]
  (letfn [(multiply [m1 m2] (mapv (fn [m] (m*v (transpose m2) m)) m1))]
    (reduce multiply (first ms) (rest ms))))

(defn det [a b c d]
  (- (* a d) (* b c)))

(defn cub_op [f]
  (mat_op (vec_op f)))

(def c+ (cub_op +))
(def c- (cub_op -))
(def c* (cub_op *))
(def cd (cub_op /))

(defn vect [v1 v2]
  (loop [i 0 res []]
    (if (== i 3) res
      (let [left (mod (+ i 1) 3) right (mod (+ i 2) 3)
            v (det (v1 left) (v1 right) (v2 left) (v2 right))]
        (recur (inc i) (conj res v))))))



