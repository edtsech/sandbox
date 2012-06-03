(ns htmldsl.core
  (:use [clojure.string :only (join)]))

(defn open-tag [tag & [attrs]]
  (str "<" tag attrs ">"))

(defn close-tag [tag]
  (str "</" tag ">"))

(defn attr [a]
  (str (name (first a)) "=\"" (second a) "\""))

(defn attrs [as]
  (str " " (join " " (map #(attr %) as)) " " ))

(defn el [vec]
  (let [tag (name (first vec))
        sec (second vec)]
        (join ""
          (cond
            ; [:p [:span "Content"]]
            (vector? sec)
              [(open-tag tag)
               (el sec)
               (close-tag tag)]
            ; [:p {:class "red"} "Content"] OR [:p {:class "red"} [:span "Content"]]
            (map? sec)
              (let [trd (nth vec 2)]
                [(open-tag tag (attrs sec))
                (if (vector? trd) (el trd) trd)
                (close-tag tag)])
            ; [:p "Content"]
            :else [(open-tag tag)
                   sec
                   (close-tag tag)]))))

