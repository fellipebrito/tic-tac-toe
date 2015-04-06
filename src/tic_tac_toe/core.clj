(ns tic-tac-toe.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn new-board [] "---------")

(defn print-board [board]
  (println (str/join "\n" [(subs board 0 3)
                           (subs board 3 6)
                           (subs board 6 9)])))

(defn matches [matcher board]
  (map first (filter #(= (second %) matcher)
             (map-indexed vector board))))

(defn win? [board]
  (def winners [[0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6] [0 1 2] [3 4 5] [6 7 8]])
  (some true?
         (into (map (fn [winner] (every? true? (map (fn [x] (.contains (matches \x board) x)) winner))) winners)
               (map (fn [winner] (every? true? (map (fn [x] (.contains (matches \o board) x)) winner))) winners))))

(defn draw? [board]
  (and (< (count (matches \- board)) 3)
       (not (some true? (map (fn [x] (win? (str/replace board "-" x))) ["x" "o"])))))

(defn input [board place player]
  (if (= \- (get board place))
    (str (subs board 0 place) player (subs board (inc place)))
    false))

(defn -main []
  (println "====++++====\nTIC TAC TOE\n====++++====")
  (loop [board  (new-board)
         player \x]
    (println "\n")
    (print-board board)
    (println "\n")
    (println "------------------------\nIt is player [" player "]'s"
             "turn.\nPick your place: [0 1 2 3]")
    (if (win? board)
      (println "You Win!")
      (if (draw? board)
        (println "Draw!")
        (recur (input board (Integer. (read-line)) player)
               (if (= \x player) \o \x))))))

;; validate user input (it should be a integer < 9)
