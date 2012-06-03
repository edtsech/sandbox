(ns htmldsl.test.core
  (:use [htmldsl.core])
  (:use [clojure.test]))

(def html [:p {:class "big"}
            [:span "asd"]])

(deftest open-tag-test
  (is (= "<p>" (open-tag "p")))
  (is (= "<p class=\"big\" >" (open-tag "p" " class=\"big\" "))))

(deftest close-tag-test
  (is (= "</p>" (close-tag "p"))))

(deftest attr-test
  (is (= "a=\"asd\"" (attr [:a "asd"]))))

(deftest attrs-test
  (is (= " a=\"1\" b=\"2\" " (attrs {:a 1 :b 2}))))

(deftest el-test
  (is (= "<p></p>" (el [:p])))
  (is (= "<p>asd</p>" (el [:p "asd"])))
  (is (= "<p><span></span></p>" (el [:p [:span]])))
  (is (= "<p><span>asd</span></p>" (el [:p [:span "asd"]])))
  (is (= "<p class=\"big\" ><span>asd</span></p>" (el html))))
