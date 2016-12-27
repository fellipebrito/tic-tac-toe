(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.core :refer :all]))

(def empty-board "---------")

(deftest input-test
  (testing "adds an x to the bottom left corner"
    (is (= "------x--" (perform-move empty-board 6 \x))))
  (testing "adds an x to the upper left corner"
    (is (= "x--------" (perform-move empty-board 0 \x))))
  (testing "adds an x to the bottom right corner"
    (is (= "--------x" (perform-move empty-board 8 \x))))
  (testing "adds an x to the upper right corner"
    (is (= "--x------" (perform-move empty-board 2 \x))))
  (testing "adds an x to the center"
    (is (= "----x----" (perform-move empty-board 4 \x)))))


(deftest first-to-integer-test
  (testing "Returns the first integer found only if the first char is an integer"
    (is (= 1 (first-to-integer "1abc")))
    (is (nil? (first-to-integer "abc1")))
    (is (nil? (first-to-integer "abc1")))))

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

(deftest move-test
  (testing "First movement of the game"
    (is (= ["x--------" \o] (move 0 empty-board \x))))
  (testing "Changes to the next player"
    (is (= ["ooxox----" \x] (move 0 "-oxox----" \o))))
  (testing "An invalid movement"
    (is (= ["xox------" \o] (move 0 "xox------" \o)))))

(deftest start-test
  (testing "It is a win"
    (is (.contains (with-out-str (with-in-str "0\n1\n2\n3\n4\n5\n6\n" (-main)))
                   "You Win!"))))
