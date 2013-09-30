(ns clojuretabs.core
  (:require [clojuretabs.db.db :as db]
            [clojuretabs.service.service :as service]
			[clojuretabs.views.views :as views]
			[clojuretabs.util.util :as util]         
            [noir.session :as session]
            [noir.server :as server]
            [noir.core :as noir]
            [noir.response :as response]
            [clojure.java.io :as io]))          

(noir/defpage [:post "/logout"] []
  (session/clear!)
  (response/redirect "/"))

(noir/defpage "/" {}
  (views/main-template))

(noir/defpage "/tabs.html" {}
  (util/format-tab-response "Layla")) 

(noir/defpage [:post "/tabs.html"] {:keys [tab-search]}
  (util/format-tab-response tab-search)) 

(noir/defpage [:post "/create-artists"] {:keys [artist-name artist-bio artist-img]}
  (io/copy (io/file (:tempfile artist-img)) (io/file (str  (System/getProperty "user.dir") "/resources/public/" (:filename artist-img) ))) 
  (db/insert-artist artist-name artist-bio (:filename artist-img))
  (response/redirect "/"))   

(noir/defpage "/artists.html" {}
  (util/format-artist-response "Eric+Clapton")) 

(noir/defpage [:post "/artists.html"] {:keys [artist-search]}
  (util/format-artist-response artist-search)) 

(noir/defpage [:post "/register"] {:keys [firstname lastname username email password]}
  (db/register firstname lastname username email password)
  (views/main-template-after-register username)
  (response/redirect "/"))

(noir/defpage [:post "/"] {:keys [username password]}
  (util/check-login username password))

(defn -main []
  (db/insert-dummy-data)
  (server/start 8080)
)
