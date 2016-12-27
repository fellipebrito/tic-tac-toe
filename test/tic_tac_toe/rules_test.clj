(ns tic-tac-toe.rules-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.rules :refer :all]))

(deftest winner-test
  (testing "return true in all the eight possible cases of victory"
    (is (true? (winner? "x--x--x--")))
    (is (true? (winner? "-o--o--o-")))
    (is (true? (winner? "--x--x--x")))
    (is (true? (winner? "x---x---x")))
    (is (true? (winner? "--o-o-o--")))
    (is (true? (winner? "ooo------")))
    (is (true? (winner? "---xxx---")))
    (is (true? (winner? "------ooo"))))
  (testing "false if does not match one of the eight possible cases of victory"
    (is (nil? (winner? "x--o--x--")))))

(deftest game-over-test
  (testing "true if the game is over"
    (is (true?  (game-over? "oxoxxo-ox"))))
  (testing "false if the game has at least 3 empty spots"
    (is (false? (game-over? "oxo--o-ox"))))
  (testing "true if the game is new"
    (is (false? (game-over? "---------")))))

(deftest end-of-the-game-test
  (testing "It is a win"
    (is (= (with-out-str (end-of-the-game? "xxx------")) "You Win!\n")))
  (testing "Game over"
    (is (= (with-out-str (end-of-the-game? "oxoxxo-ox")) "Game Over!\n"))))
