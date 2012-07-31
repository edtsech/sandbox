(ns euler.problem2)

(defn- lazy-fib
  ([] (lazy-fib 0 1))
  ([a b] (lazy-seq (cons a (lazy-fib b (+ a b))))))

(defn problem2 []
  (reduce + (take-while #(< % 4000000)
                         (filter even? (lazy-fib 0 1)))))

