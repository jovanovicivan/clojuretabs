(ns clojuretabs.service.service
  (:require [clj-http.client :as client]))
  
(def api-key {"Guitarparty-Api-Key" "a25709be19803fcd39c29414b673513acc21aaaf"})

(defn find-tab [song]
	(:body (get (:objects (:body (client/get (str "http://api.guitarparty.com/v2/songs/?query=" song) 
                         {:headers api-key
                          :as :json}))) 0)))
                          
(defn find-artist [artist]
	(:bio (get (:objects (:body (client/get (str "http://api.guitarparty.com/v2/artists/?query=" artist) 
                         {:headers api-key
                          :as :json}))) 0)))