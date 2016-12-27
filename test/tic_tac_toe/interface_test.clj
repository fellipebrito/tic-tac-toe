(ns tic-tac-toe.interface-test
  (:require [clojure.test :refer :all]
            [tic-tac-toe.interface :refer :all]))

(deftest printable-board-test
  (testing "print the current board"
    (is (= (printable-board "---------") ["---" "---" "---"]))))

