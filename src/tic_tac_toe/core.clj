(ns tic-tac-toe.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn new-board [] "---------")

(defn print-board [board]
  (println "\n")
  (println (str/join "\n" [(subs board 0 3)
                           (subs board 3 6)
                           (subs board 6 9)]))
  (println "\n"))

(defn matches [matcher board]
  (map first (filter #(= (second %) matcher)
             (map-indexed vector board))))

(defn win? [board]
  (let [winner '(true true true)]
    (some true?
          [(= winner (map (fn [x] (.contains (matches \x board) x)) [0 3 6]))
           (= winner (map (fn [x] (.contains (matches \x board) x)) [1 4 7]))
           (= winner (map (fn [x] (.contains (matches \x board) x)) [2 5 8]))
           (= winner (map (fn [x] (.contains (matches \x board) x)) [0 4 8]))
           (= winner (map (fn [x] (.contains (matches \x board) x)) [2 4 6]))
           (= winner (map (fn [x] (.contains (matches \x board) x)) [0 1 2]))
           (= winner (map (fn [x] (.contains (matches \x board) x)) [3 4 5]))
           (= winner (map (fn [x] (.contains (matches \x board) x)) [6 7 8]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [0 3 6]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [1 4 7]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [2 5 8]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [0 4 8]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [2 4 6]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [0 1 2]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [3 4 5]))
           (= winner (map (fn [x] (.contains (matches \o board) x)) [6 7 8]))])))

(defn draw? [board]
  (if (< (count (matches \- board)) 3)
    (not (win? (str/replace board "-" "x")))
    (not (win? (str/replace board "-" "o")))))

(defn input [board place player]
  (str (subs board 0 place) player (subs board (inc place))))

(def not-nil? (complement nil?))

(defn to-integer
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

(defn move
  "Try to perform the user movement.
  Returns the current board and player"
  [user-input board player]
  (if (valid-input? user-input board)
    [(input board user-input player) (if (= \x player) \o \x)]
    (do
      (println user-input " is an invalid movement. Try again.")
      [board player])))

(defn end-of-the-game? [board]
  (if (win? board)
    (do
      (println "You Win!")
      true)
    (if (draw? board)
      (do
        (println "Draw!")
        true))))

(defn start []
  (loop [board  (new-board)
         player \x]
    (print-board board)
    (println "------------------------\nIt is player [" player "]'s"
             "turn.\nPick your place using one number between 0 and 8")
    (if (not (end-of-the-game? board))
      (let [[board player] (move (to-integer (read-line)) board player)]
        (recur board player)))))

(defn -main []
  (println "====++++====\nTIC TAC TOE\n====++++====")
  (start))
