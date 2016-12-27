(ns tic-tac-toe.move-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.move :refer :all]))

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


(deftest move-test
  (testing "First movement of the game"
    (is (= ["x--------" \o] (move 0 empty-board \x))))
  (testing "Changes to the next player"
    (is (= ["ooxox----" \x] (move 0 "-oxox----" \o))))
  (testing "An invalid movement"
    (is (= ["xox------" \o] (move 0 "xox------" \o)))))
