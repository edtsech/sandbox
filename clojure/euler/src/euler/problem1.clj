(ns euler.problem1)

(defn problem1 [limit]
  (reduce + (filter #(or (zero? (mod % 3))
                         (zero? (mod % 5)))
                    (range limit))))
