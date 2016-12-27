(ns tic-tac-toe.move
  (:require [tic-tac-toe.validation :as validation]))

(defn perform-move
  "Given a board, a player and a place
  returns a string with the new board state"
  [board place player]
  (str (subs board 0 place) player (subs board (inc place))))

(defn move
  "Try to perform the user movement.
  Returns the current board and player"
  [user-input board player]
  (if (validation/valid-input? user-input board)
    [(perform-move board user-input player) (if (= \x player) \o \x)]
    (do (println user-input " is an invalid movement. Try again.")
        [board player])))
