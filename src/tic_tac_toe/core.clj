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
  (def winner '(true true true))
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
         (= winner (map (fn [x] (.contains (matches \o board) x)) [6 7 8]))]))

(defn draw? [board]
  (and (< (count (matches \- board)) 3)
       (not (win? (str/replace board "-" "x")))
       (not (win? (str/replace board "-" "o")))))

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
