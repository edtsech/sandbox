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
  ([tag]
    ; [:p]
    [(open-tag tag)
    (close-tag tag)])
  ([tag content-or-attrs]
    (if (map? content-or-attrs)
      ; [:p {:class "big"}]
      [(open-tag tag (attrs content-or-attrs))
      (close-tag tag)]
      ; [:p [:span]]
      [(open-tag tag)
       (if (string? content-or-attrs)
         content-or-attrs
         (join (apply wrap-tag content-or-attrs)))
       (close-tag tag)]))
  ([tag attributes content]
    ; [:p {:class "big"} [:span "asd"]]
    [(open-tag tag (attrs attributes))
     (if (string? content) content (join (apply wrap-tag content)))
     (close-tag tag)]))

(defn el [vec]
  (join (apply wrap-tag vec)))
