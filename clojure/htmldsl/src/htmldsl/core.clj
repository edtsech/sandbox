(ns htmldsl.core
  (:use [clojure.string :only (join)]))

(defn open-tag [tag & [attrs]]
  (str "<" (name tag) attrs ">"))

(defn close-tag [tag]
  (str "</" (name tag) ">"))

(defn attr [a]
  (str (name (first a)) "=\"" (second a) "\""))

(defn attrs [as]
  (str " " (join " " (map #(attr %) as)) " " ))

(defn wrap-tag
  ([tag-name]
    ; [:p]
    [(open-tag tag-name)
    (close-tag tag-name)])
  ([tag-name content-or-attributes]
    (if (map? content-or-attributes)
      ; [:p {:class "big"}]
      [(open-tag tag-name (attrs content-or-attributes))
      (close-tag tag-name)]
      ; [:p [:span]]
      [(open-tag tag-name)
       (if (string? content-or-attributes)
         content-or-attributes
         (join (apply wrap-tag content-or-attributes)))
       (close-tag tag-name)]))
  ([tag-name attributes content]
    ; [:p {:class "big"} [:span "asd"]]
    [(open-tag tag-name (attrs attributes))
     (if (string? content) content (join (apply wrap-tag content)))
     (close-tag tag-name)]))

(defn el [vec]
  (join (apply wrap-tag vec)))
