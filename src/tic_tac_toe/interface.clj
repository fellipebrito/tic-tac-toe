(ns tic-tac-toe.interface)

(defn printable-board
  "Get the board ready to be printed"
  [board]
  (vec (re-seq #".{1,3}" board)))

(defn print-interface
  "Print the board and a message to the user"
  [board player]
  (run! println (printable-board board))
  (println "------------------------\nIt is player [" player "]'s"
           "turn.\nPick your place using one number between 0 and 8"))
