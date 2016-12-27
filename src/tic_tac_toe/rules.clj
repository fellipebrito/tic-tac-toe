(ns tic-tac-toe.rules
  (:require [clojure.string :as str]))

(def possible-win-scenarios
  [[0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6] [0 1 2] [3 4 5] [6 7 8]])

(defn test-possible-scenarios [board]
  (map (fn [player]
         (map #(every? #{player} %)
              (map (fn [x] (map #(get board  %) x))
                   possible-win-scenarios)))
       [\x \o]))

(defn winner? [board]
  (some true? (flatten (test-possible-scenarios board))))

(defn game-over? [board]
  (not-any? winner? (map #(str/replace board "-" %) ["x" "o"])))

(defn end-of-the-game? [board]
  (if (winner? board)
    (do (println "You Win!")
        true)
    (if (game-over? board)
      (do (println "Game Over!")
          true))))


