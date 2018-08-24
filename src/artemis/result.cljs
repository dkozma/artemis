(ns artemis.result
  (:require [clojure.set :refer [rename-keys]]))

(defn with-errors
  "Decorates a result with a sequence of error maps."
  {:added "0.1.0"}
  [result errors]
  (if (empty? errors)
    result
    (update result :errors concat errors)))

(defn result->message
  "Takes a result and returns a map containing at least a `:data`."
  {:added "0.1.0"}
  [result op-map]
  (let [kw-op-map (when op-map
                    (reduce-kv #(assoc %1 %2 (keyword %3)) {} op-map))
        rename    #(rename-keys % kw-op-map)]
    (-> result (update :data (if op-map rename identity)))))
