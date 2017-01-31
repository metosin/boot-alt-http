(ns metosin.boot-alt-http.impl
  (:require [org.httpkit.server :as http-kit]
            [ring.middleware.resource :as resource]
            [ring.middleware.content-type :as content-type]
            [ring.middleware.not-modified :as not-modified]
            [metosin.boot-alt-http.ring-utils :as u]
            [boot.util :as util]))

(def server (atom nil))

(defn create-handler [{:keys [prefixes]
                       :or {prefixes #{"public"}}}]
  (-> (reduce
        resource/wrap-resource
        (constantly nil)
        prefixes)
      u/wrap-last-modified
      content-type/wrap-content-type
      not-modified/wrap-not-modified
      u/wrap-index
      u/wrap-not-found))

(defn start [{:keys [ip port] :as opts}]
  (let [server (http-kit/run-server (create-handler opts)
                                    {:ip (or ip "0.0.0.0")
                                     :port (or port 0)})
        ip (or ip "0.0.0.0")
        port (:local-port (meta server))]
    (util/info "HTTP server listening at http://%s:%s\n" ip port)
    server))

(defn stop []
  (when-let [s @server]
    (util/dbug "Closing HTTP server at port %s" (:local-port (meta s)))
    (s)))

(defn maybe-start [opts]
  (swap! server (fn [server]
                  (if server
                    server
                    (start opts)))))

(comment
  (maybe-start {:prefixes #{"public"}})
  (stop))
