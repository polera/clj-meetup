(ns clj-meetup.core
  (:require [org.httpkit.client :as http]
            [cheshire.core :as json]
            [environ.core :refer [env]]))

(def base-url "http://api.meetup.com")

(def method-urls {;; groups
                  :group-info "2/groups.json"
                  ;; status
                  :status "status"})

(def api-key
  (env :meetup-api-key))

(defn build-api-request
  "Builds a request URL for the meetup API"
  ([api-method]
   {:url (format
          "%s/%s/"
          base-url
          api-method)
    :params {}})
  ([api-method args]
   {:url (format
          "%s/%s/"
          base-url
          api-method)
    :params args}))

(defn request
  "Make an API request for a given method and arguments"
  [http-method api-request]
  (let [request-type (condp = http-method
                       :post http/post
                       :get  http/get)
        params       (assoc (:params api-request)
                       :key api-key)
        url          (:url api-request)]


    (request-type url
                  {:accept :json
                   :query-params params})))

(defn load-json
  [payload]
  (json/parse-string payload true))
