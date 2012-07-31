(ns euler.test.core
  (:use [euler.core])
  (:use [euler.problem1])
  (:use [euler.problem2])
  (:use [euler.problem3])
  (:use [clojure.test]))

(deftest problem1-test
         (is 23     (problem1 10))
         (is 233168 (problem1 1000)))

(deftest problem2-test
         (is 4613732 (problem2)))

(deftest problem3-test
         (is 6857 (problem3 600851475143)))
