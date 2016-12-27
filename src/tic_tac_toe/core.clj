(ns tic-tac-toe.core
  (:require [clojure.string :as str]
            [tic-tac-toe.interface :as interface]
            [tic-tac-toe.rules :as rules])
  (:gen-class))

;; VALIDATION
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
    (interface/print-interface board player)
    (if (not (rules/end-of-the-game? board))
      (let [[board player] (move (first-to-integer (read-line)) board player)]
        (recur board player)))))

(defn -main []
  (println "====++++====\nTIC TAC TOE\n====++++====")
  (start))
