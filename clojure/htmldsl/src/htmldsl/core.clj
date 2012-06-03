(ns htmldsl.core
  (:use [clojure.string :only (join)]))

(defn open-tag [tag & [attrs]]
  (str "<" tag attrs ">"))

(defn close-tag [tag]
  (str "</" tag ">"))

(defn attr [a]
  (str (name (first a)) "=\"" (second a) "\""))

(defn attrs [as]
  (str " " (join " " (map (fn [x] (attr x)) as)) " " ))

(defn el [vec]
  (let [tag (name (first vec))
        sec (second vec)]
        (join ""
          (cond (vector? sec)
                  [(open-tag tag)
                    (el sec)
                    (close-tag tag)]
                (map? sec)
                  (let [trd (nth vec 2)]
                    [(open-tag tag (attrs sec))
                    (if (vector? trd) (el trd) trd)
                    (close-tag tag)])
            :else [(open-tag tag)
                   sec
                   (close-tag tag)]))))
