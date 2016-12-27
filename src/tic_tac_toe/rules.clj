(ns tic-tac-toe.rules
  (:require [clojure.string :as str]))

(defn test-scenarios
  "Test scenarios looking for one where a player matches every necessary places"
  [board scenarios]
  (map (fn [player]
         (map #(every? #{player} %)
              (map (fn [x] (map #(get board  %) x))
                   scenarios)))
       [\x \o]))

(defn winner?
  "Given the scenarios where a victory may happen, tests it"
  [board]
  (let [possible-win-scenarios [[0 3 6] [1 4 7] [2 5 8]
                                [0 4 8] [2 4 6] [0 1 2]
                                [3 4 5] [6 7 8]]]
    (some true? (flatten (test-scenarios board possible-win-scenarios)))))

(defn game-over?
  "Overwrites every blank space on the given board with a user piece
  then verifies if there is still a chance of a winner in the game"
  [board]
  (not-any? winner? (map #(str/replace board "-" %) ["x" "o"])))

(defn end-of-the-game?
  "Verifies if there is already a winner or a chance of one player
  winning the game"
  [board]
  (if (winner? board)
    (do (println "You Win!")
        true)
    (if (game-over? board)
      (do (println "Game Over!")
          true))))
