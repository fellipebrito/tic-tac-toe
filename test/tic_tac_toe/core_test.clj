(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.core :refer :all]))

(deftest first-integer-test
  (testing "Returns the first integer found only if the first char is an integer"
    (is (= 1 (first-integer "1abc")))
    (is (nil? (first-integer "abc1")))
    (is (nil? (first-integer "abc1")))))

(deftest start-test
  (testing "It is a win"
    (is (.contains (with-out-str (with-in-str "0\n1\n2\n3\n4\n5\n6\n" (-main)))
                   "You Win!"))))
