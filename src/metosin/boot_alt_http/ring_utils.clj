(ns metosin.boot-alt-http.ring-utils
  (:require [ring.util.response :as response])
  (:import [java.util Date TimeZone Locale]
           [java.text SimpleDateFormat]
           [java.io File]))

(defn wrap-index [handler]
  (fn [req]
    (or (handler req)
        ;; If URI sends in "/" serve the index, if available
        (if (.endsWith (:uri req) "/")
          (handler (update req :uri str "index.html")))
        ;; If URI doens't end in "/" but index.html is available, redirect
        (if (handler (update req :uri str "/index.html"))
          (response/redirect (str (:uri req) "/"))))))

(defn wrap-not-found [handler]
  (fn [req]
    (or (handler req)
        {:status 404 :body "Not Found"})))

(defn wrap-last-modified [handler]
  (let [f (doto (SimpleDateFormat. "EEE, dd MMM yyyy HH:mm:ss z" Locale/US)
            (.setTimeZone (TimeZone/getTimeZone "GMT")))]
    (fn [req]
      (let [resp (handler req)]
        (if (instance? File (:body resp))
          (let [mod-time (Date. (.lastModified ^File (:body resp)))]
            (response/header resp "Last-Modified" (.format f mod-time)))
          resp)))))
