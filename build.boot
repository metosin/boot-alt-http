(def +version+ "0.2.0")

(set-env!
  :resource-paths #{"src"}
  :dependencies   '[[org.clojure/clojure "1.9.0" :scope "provided"]
                    [boot/core "2.7.2" :scope "provided"]
                    [http-kit "2.2.0" :scope "test"]
                    [ring/ring-core "1.6.3" :scope "test"]])

(task-options!
  pom {:project     'metosin/boot-alt-http
       :version     +version+
       :description ""
       :url         "https://github.com/metosin/boot-alt-http"
       :scm         {:url "https://github.com/metosin/boot-alt-http"}
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build []
  (comp
    (pom)
    (jar)
    (install)))

(deftask dev []
  (comp
   (watch)
   (repl :server true)
   (build)
   (target)))

(deftask deploy []
  (comp
   (build)
   (push :repo "clojars" :gpg-sign (not (.endsWith +version+ "-SNAPSHOT")))))
