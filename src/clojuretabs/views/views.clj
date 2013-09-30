(ns clojuretabs.views.views
  (:require [net.cgrand.enlive-html :as html]
    		[noir.session :as session]
			[clojuretabs.db.db :as db]
			[clojuretabs.service.service :as service]
    ))

(def not-nil? (complement nil?))
		
(html/deftemplate tabs "clojuretabs/views/tabs.html" [song]
  [:.found-songs] (html/content (clojure.string/replace (str (service/find-tab song)) "\r\n" "<br/>" ))
  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "login-not-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "login-not-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible")))

(html/deftemplate artists "clojuretabs/views/artists.html" [artist]
  [:#createArtist] (if (nil? (session/get :username)) (html/remove-class "loggedIn") (html/remove-class "notLoggedIn")) 
  [:#createArtist] (if (not-nil? (session/get :username)) (html/add-class "loggedIn") (html/add-class "notLoggedIn")) 
  
  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "login-not-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "login-not-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible"))
                    
  [:.found-artists] (html/content (service/find-artist artist))
  )

(html/deftemplate main-template "clojuretabs/views/clojure.html"
  []
  [:#img1] (html/set-attr :src (:image (nth (db/find-last-three-artists) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-last-three-artists) 1)))
  [:#img3] (html/set-attr :src (:image (nth (db/find-last-three-artists) 2)))
  
  [:#name1] (html/content (:name (nth (db/find-last-three-artists) 0)))
  [:#name2] (html/content (:name (nth (db/find-last-three-artists) 1)))
  [:#name3] (html/content (:name (nth (db/find-last-three-artists) 2)))
  
  [:#bio1] (html/content (:bio (nth (db/find-last-three-artists) 0)))
  [:#bio2] (html/content (:bio (nth (db/find-last-three-artists) 1)))
  [:#bio3] (html/content (:bio (nth (db/find-last-three-artists) 2)))
  
  [:#logoutBtn] (if (nil? (session/get :username)) (html/remove-class "login-visible") (html/remove-class "login-not-visible"))
  [:#logoutBtn] (if (not-nil? (session/get :username)) (html/add-class "login-visible") (html/add-class "login-not-visible"))
  [:#loginBtn] (if (nil? (session/get :username)) (html/remove-class "login-not-visible") (html/remove-class "login-visible"))
  [:#loginBtn] (if (not-nil? (session/get :username)) (html/add-class "login-not-visible") (html/add-class "login-visible"))
  )
  
(html/deftemplate main-template-after-login "clojuretabs/views/clojure.html"
  [username]
  [:#welcomeMsg] (html/content (str "Welcome " username))
  [:#loginBtn] (html/remove-class "login-visible")
  [:#loginBtn] (html/add-class "login-not-visible")
  [:#logoutBtn] (html/remove-class "login-not-visible")
  [:#logoutBtn] (html/add-class "login-visible")
  
  [:#img1] (html/set-attr :src (:image (nth (db/find-last-three-artists) 0)))
  [:#img2] (html/set-attr :src (:image (nth (db/find-last-three-artists) 1)))
  [:#img3] (html/set-attr :src (:image (nth (db/find-last-three-artists) 2)))
  
  [:#name1] (html/content (:name (nth (db/find-last-three-artists) 0)))
  [:#name2] (html/content (:name (nth (db/find-last-three-artists) 1)))
  [:#name3] (html/content (:name (nth (db/find-last-three-artists) 2)))
  
  [:#bio1] (html/content (:bio (nth (db/find-last-three-artists) 0)))
  [:#bio2] (html/content (:bio (nth (db/find-last-three-artists) 1)))
  [:#bio3] (html/content (:bio (nth (db/find-last-three-artists) 2))))
  
(html/deftemplate main-template-after-register "clojuretabs/views/clojure.html"
  [username]
  [:#welcomeMsg] (html/content (str "Welcome " username ". You are successfully registered.")))

  
