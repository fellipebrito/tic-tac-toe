(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.core :refer :all]))

(def empty-board "---------")

(deftest new-board-test
  (testing "generates a new board"
    (is (= (new-board) "---------"))))

(deftest print-board-test
  (testing "print the current board"
    (is (= (with-out-str (print-board (new-board))) "\n\n---\n---\n---\n\n\n"))))

(deftest input-test
  (testing "adds an x to the bottom left corner"
    (is (= "------x--" (input empty-board 6 \x))))
  (testing "adds an x to the upper left corner"
    (is (= "x--------" (input empty-board 0 \x))))
  (testing "adds an x to the bottom right corner"
    (is (= "--------x" (input empty-board 8 \x))))
  (testing "adds an x to the upper right corner"
    (is (= "--x------" (input empty-board 2 \x))))
  (testing "adds an x to the center"
    (is (= "----x----" (input empty-board 4 \x)))))


(deftest matches-test
  (testing "find all matches of a matcher in a given board"
    (is (= '(0 2 4 8) (matches \x "x-x-x---x")))))

(deftest win-test
  (testing "return true in all the eight possible cases of victory"
    (is (true? (win? "x--x--x--")))
    (is (true? (win? "-o--o--o-")))
    (is (true? (win? "--x--x--x")))
    (is (true? (win? "x---x---x")))
    (is (true? (win? "--o-o-o--")))
    (is (true? (win? "ooo------")))
    (is (true? (win? "---xxx---")))
    (is (true? (win? "------ooo"))))
  (testing "false if does not match one of the eight possible cases of victory"
    (is (nil? (win? "x--o--x--")))))

(deftest draw-test
  (testing "true if the game is a drawn"
    (is (true?  (draw? "oxoxxo-ox"))))
  (testing "false if the game has at least 3 empty spots"
    (is (false? (draw? "oxo--o-ox"))))
  (testing "true if the game is new"
    (is (false? (draw? empty-board)))))

(deftest to-integer-test
  (testing "Returns the first integer found only if the first char is an integer"
    (is (= 1 (to-integer "1abc")))
    (is (nil? (to-integer "abc1")))
    (is (nil? (to-integer "abc1")))))

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

(deftest end-of-the-game-test
  (testing "It is a win"
    (is (true? (end-of-the-game? "xxx------"))))
  (testing "It is a drawn"
    (is (true? (end-of-the-game? "oxoxxo-ox"))))
  (testing "It is a drawn"
    (is (true? (end-of-the-game? "oxoxxo-ox")))))

(deftest start-test
  (testing "It is a win"
    (is (nil? (with-in-str "0\n1\n2\n3\n4\n5\n6\n" (-main)))))
  (testing "It is a win"
    (is (nil? (with-in-str "0\n1\n2\n3\n4\n5\n6\n" (start))))))
