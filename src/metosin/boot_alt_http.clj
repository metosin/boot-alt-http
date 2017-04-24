(ns metosin.boot-alt-http
  {:boot/export-tasks true}
  (:require [boot.pod :as pod]
            [boot.core :as core]))

(def ^:private deps
  '[[http-kit "2.2.0"]
    [ring/ring-core "1.5.1"]])

(core/deftask serve
  "Run http-kit in a pod to serve files from classpath.

  If you only want the server to be only accessible from local computer, change the
  ip option to 127.0.0.1. The default 0.0.0.0 listen address means that server
  listens on all local interfaces, which means that unless you have firewall blocking
  the connections, the server is accessible from other computers. The default
  can be useful when you need to access the server from e.g. VirtualBox."
  [i ip           VAL str   "IP to listen on, default 0.0.0.0"
   p port         VAL int   "Port to be used, default is to use a free port"
   r prefixes     VAL #{str} "Classpath prefixes to serve files from, default is public"
   d directories  VAL #{str} "Directories to serve files from"]
  (let [p (-> (core/get-env)
              (update-in [:dependencies] into deps)
              pod/make-pod
              future)
        opts (into {} (remove (comp nil? val)
                              *opts*))]

    (core/cleanup
      (pod/with-call-in @p
        (metosin.boot-alt-http.impl/stop)))

    (fn [handler]
      (fn [fileset]
        (pod/with-call-in @p
          (metosin.boot-alt-http.impl/maybe-start ~opts))
        (handler fileset)))))
