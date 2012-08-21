(ns psw.test.core
  (:use [psw.core])
  (:use [clojure.test]))

(def coll '(1 1 2 2 3 2 1 2 3 1))

(deftest partition-starts-with-test
         (is (= '((1) (1 2 2 3 2) (1 2 3) (1))
                (partition-starts-with #(= % 1) coll)))
         (is (= '((1 2) (3 4) (5 6))
                 (partition-starts-with odd? '(1 2 3 4 5 6))))
         (is (= '((1) (2 3) (4 5) (6))
                 (partition-starts-with even? '(1 2 3 4 5 6))))
         (is (= '((1) (1) (1 2 2) (3) (3 4 4))
                 (partition-starts-with odd? '(1 1 1 2 2 3 3 4 4))))
         (is (= '((1 1 1) (2) (2 3 3) (4) (4))
                 (partition-starts-with even? '(1 1 1 2 2 3 3 4 4))))
         (is (= '((2) (2 3 3) (4) (4))
                 (partition-starts-with even? (drop-while odd? '(1 1 1 2 2 3 3 4 4))))))
