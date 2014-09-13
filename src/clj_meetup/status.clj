(ns clj-meetup.status
  (:require [clj-meetup.core :as core]))

(defn status-to-keyword
  [status]
  (condp = (:status status)
    "ok" :ok
    "notice" :notice
    "other" :other))


(defn get-status []
  (->
   @(core/request
     :get
     (core/build-api-request
      (:status core/method-urls)))
   :body
   core/load-json
   status-to-keyword
   ))
