(ns tic-tac-toe.core
  (:require [clojure.string :as str])
  (:gen-class))

;; PRINT
(defn printable-board
  "Get the board ready to be printed"
  [board]
  (vec (re-seq #".{1,3}" board)))

(defn print-interface
  [board player]
  (run! println (printable-board board))
  (println "------------------------\nIt is player [" player "]'s"
           "turn.\nPick your place using one number between 0 and 8"))

;; VERIFY GAME STATUS
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

;; USER INPUT - VALIDATION
(def not-nil? (complement nil?))

(defn first-to-integer
  "Return the first integer found if the first char is an integer
  otherwise return false"
  [input]
  (if-let [integer (re-find #"\A-?\d+" input)]
    (Integer. integer)))

(defn valid-input?
  "User input has to be an Integer, respect the board size
  and the place has to be empty"
  [user-input board]
  (and
    (not-nil? user-input)
    (integer? user-input)
    (>= user-input 0)
    (<= user-input 8)
    (= \- (get board user-input))))

;; MOVE
(defn perform-move [board place player]
  (str (subs board 0 place) player (subs board (inc place))))

(defn move
  "Try to perform the user movement.
  Returns the current board and player"
  [user-input board player]
  (if (valid-input? user-input board)
    [(perform-move board user-input player) (if (= \x player) \o \x)]
    (do (println user-input " is an invalid movement. Try again.")
        [board player])))

;; EXECUTE THE GAME
(defn start []
  (loop [board  (apply str (repeat 9 "-"))
         player \x]
    (print-interface board player)
    (if (not (end-of-the-game? board))
      (let [[board player] (move (first-to-integer (read-line)) board player)]
        (recur board player)))))

(defn -main []
  (println "====++++====\nTIC TAC TOE\n====++++====")
  (start))
