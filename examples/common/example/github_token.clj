(ns example.github-token)

(defmacro github-token []
  (slurp (clojure.java.io/resource ".github-token")))
