(ns clj-meetup.groups
  (:require [clj-meetup.core :as core]))

(defn get-group-information
  "Return a keyword map of all group iformation
   for a given topic and zipcode"
  [topic zipcode]
  (->
   @(core/request
     :get
     (core/build-api-request
      (:group-info core/method-urls)
      {:topic topic
       :zip zipcode}))
   :body
   core/load-json))
