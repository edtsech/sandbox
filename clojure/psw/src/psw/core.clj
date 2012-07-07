(ns psw.core)

(defn partition-starts-with
  [f coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (let [fst (first s)
           fv (f fst)
           run (cons fst (take-while (if fv
                                      #(not= fv (f %))
                                      #(= fv (f %)))
                                     (rest s)))]
       (cons run (partition-starts-with f (drop (count run) s)))))))
