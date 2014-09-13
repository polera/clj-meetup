(ns clj-meetup.core
  (:require [org.httpkit.client :as http]
			[cheshire.core :as json]
			[environ.core :refer [env]]))

(def base-url "http://api.meetup.com")

(def api-key
  (env :meetup-api-key))

(defn build-api-request
  "Builds a request URL for the meetup API"
  [api-method args]
  {:url (format
		 "%s/%s.json/"
		 base-url
		 api-method)
   :params args})

(defn request
  "Make an API request for a given method and arguments"
  [http-method api-request]
  (try
	(let [request-type (condp = http-method
						 :post http/post
						 :get  http/get)
		  params       (assoc (:params api-request)
						 :key api-key)
		  url          (:url api-request)]


	  (request-type url
					{:accept :json
					 :query-params params}))))

(defn load-json
  [payload]
  (json/parse-string payload true))
