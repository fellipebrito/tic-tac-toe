(ns tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.core :refer :all]))

(deftest new-board-test
  (testing "generates a new board"
    (is (= (new-board) "---------"))))

(deftest print-board-test
  (testing "print the current board"
    (is (= (with-out-str (print-board (new-board))) "---\n---\n---\n"))))

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
  (testing "return false if does not match one of the eight possible cases of victory"
    (is (nil? (win? "x--o--x--")))))

(deftest draw-test
  (testing "returns true if the game is a drawn"
    (is (true?  (draw? "oxoxxo-ox"))))
  (testing "returns false if the game has at least 3 empty spots"
    (is (false? (draw? "oxo--o-ox"))))
  (testing "returns true if the game is new"
    (is (false? (draw? (new-board))))))

(deftest input-test
  (testing "inputs into a new board"
    (is (= "--x------" (input (new-board) 2 \x))))
  (testing "inputs into an almost full board"
    (is (= "-xo-oxxox" (input "-xo-ox-ox" 6 \x))))
  (testing "cant overwrite a filled field"
    (is (false? (input "-xo-ox-ox" 4 \x)))))
