(ns metosin.boot-alt-http
  {:boot/export-tasks true}
  (:require [boot.pod :as pod]
            [boot.core :as core]))

(def ^:private deps
  [['http-kit "2.2.0"]])

(core/deftask alt-http
  "Run http-kit in a pod to serve files from classpath."
  [p port         VAL int   "Port to be used, free port starting from 3000 is selected if not set"
   r prefix       VAL #{str} "Classpath prefixes to serve files from"]
  (let [p (-> (core/get-env)
              (update-in [:dependencies] into deps)
              pod/make-pod
              future)
        opts (into {} (remove (comp nil? val)
                              *opts*))]
    (fn [handler]
      (fn [fileset]
        ;; TODO: The server
        (handler fileset)))))
