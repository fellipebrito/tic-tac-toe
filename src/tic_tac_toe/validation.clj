(ns tic-tac-toe.validation)

(def not-nil? (complement nil?))

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
