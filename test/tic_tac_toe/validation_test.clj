(ns tic-tac-toe.validation-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.validation :refer :all]))

(def empty-board "---------")

(deftest valid-input-test
  (testing "The input is an integer"
    (is (true? (valid-input? 1 empty-board)))
    (is (false? (valid-input? nil empty-board)))
    (is (false? (valid-input? "1" empty-board))))
  (testing "The input number is >= 0"
    (is (true? (valid-input? 0 empty-board)))
    (is (true? (valid-input? 5 empty-board)))
    (is (false? (valid-input? -15 empty-board))))
  (testing "The input number is <= 8"
    (is (true? (valid-input? 5 empty-board)))
    (is (true? (valid-input? 8 empty-board)))
    (is (false? (valid-input? 15 empty-board))))
  (testing "The place is not taken yet"
    (is (false? (valid-input? 0 "x--------")))
    (is (false? (valid-input? 8 "--------x")))
    (is (true? (valid-input? 5 "oxoxo-xox")))))


