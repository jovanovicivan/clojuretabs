(ns clojuretabs.util.util
  (:require [noir.session :as session]
            [clojuretabs.views.views :as views]
			[clojuretabs.db.db :as db]
			[clojuretabs.service.service :as service]))
  

 
(defn process-login [username]
 (session/put! :username username) 
 (views/main-template-after-login username))    

(defn check-login [username password]
  (if (nil? (first (db/login username password))) 
    (str "ERROR") 
    (process-login username) 
))

(defn format-tab-response [tab]
  (str (clojure.string/replace (apply str (views/tabs tab)) #"&lt;|&gt;" {"&lt;" "<" "&gt;" ">" }))) 
  
(defn format-artist-response [artist]
  (str (clojure.string/replace (apply str (views/artists artist)) #"&lt;|&gt;|&quot;" {"&lt;" "<" "&gt;" ">" "&quot;" "\"" }))) 
