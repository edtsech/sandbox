(ns euler.problem3)

(defn- mod-eq-zero? [x y]
  (zero? (mod x y)))

(defn- simple? [x]
  (if (or (mod-eq-zero? x 5)
          (mod-eq-zero? x 2))
    false
    (empty? (take 1 (filter #(mod-eq-zero? x %) (range 2 (Math/sqrt x)))))))

(defn- lazy-reverse-range [x]
  (lazy-seq (cons x (lazy-reverse-range (- x 1)))))

(defn problem3 [x]
  (first (filter #(and (mod-eq-zero? x %) (simple? %))
                  (lazy-reverse-range (Math/round (Math/sqrt x))))))
