(ns tic-tac-toe.core
  (:require [clojure.string :as str]
            [tic-tac-toe.interface :as interface]
            [tic-tac-toe.move :as move]
            [tic-tac-toe.rules :as rules])
  (:gen-class))

(defn first-integer
  "Return the first integer found if the first char is an integer
  otherwise return false"
  [input]
  (if-let [integer (re-find #"\A-?\d+" input)]
    (Integer. integer)))

(defn play
  " Ask for MOVE
  Have two different methods AI/Human Move
  If Human: Prompt / Else: Move"
  [board player]
  (let [place (first-integer (read-line))]
    (move/move place board player)))

(defn start
  "- Present the board
  - Verify if it is the end of the game
  - Asks the user for an input
  - Tries to perform the user input"
  []

  (loop [board  (apply str (repeat 9 "-"))
         player \x]
    (interface/print-interface board player)
    (if (not (rules/end-of-the-game? board))
      (let [[board player] (play board player)]
        (recur board player)))))

(defn -main []
  (println "====++++====\nTIC TAC TOE\n====++++====")
  (start))
