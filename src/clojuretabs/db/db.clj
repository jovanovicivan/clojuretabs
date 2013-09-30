(ns clojuretabs.db.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:refer-clojure :exclude [sort find])
  (:use monger.query))

(defn login [username password]
  (mg/connect!)
  (mg/set-db!(mg/get-db "guitar"))
  (mc/find-maps "users" {:username username, :password password}))

(defn find-artist-by-name [name]
  (mg/connect!)
  (mg/set-db!(mg/get-db "guitar"))
  (mc/find-maps "artist" {:name name}))

(defn find-last-three-artists []
  (mg/connect!)
  (mg/set-db!(mg/get-db "guitar"))
  (with-collection "artist"
    (find {})
    (fields [:name :image :bio])
    (sort (sorted-map :_id -1))
    (limit 3)))
  
(defn register [firstname lastname username email password]
  (mg/connect!)
  (mg/set-db!(mg/get-db "guitar"))
  (mc/insert "users" {:firstname firstname, :lastname lastname, :username username, :email email, :password password}))

(defn insert-artist [name bio image]
  (mg/connect!)
  (mg/set-db!(mg/get-db "guitar"))
  (mc/insert "artist" {:name name, :bio bio, :image image}))
  
(defn insert-dummy-data []
  (mg/connect!)
  (mg/set-db!(mg/get-db "guitar"))
  (mc/insert "artist" {:name "Eric Clapton", :bio "Eric Patrick Clapton, CBE, (born 30 March
	1945) is an English musician, singer and songwriter. He is the only three-time i
	nductee to the Rock and Roll Hall of Fame: once as a solo artist, and separately
	 as a member of the Yardbirds and Cream. Clapton has been referred to as one of
	the most important and influential guitarists of all time. Clapton ranked second
	 in Rolling Stone magazine's list of the 100 Greatest Guitarists of All Time and
	 fourth in Gibson's Top 50 Guitarists of All Time.", :image "images/EricClapton.jpg"})
	  (mc/insert "artist" {:name "Foo Fighters", :bio "The Foo Fighters are an American rock band
	, formed in Seattle in 1994. It was founded by Nirvana drummer Dave Grohl as a o
	ne-man project following the death of Kurt Cobain and the resulting dissolution
	of his previous band. The group got its name from the UFOs and various aerial ph
	enomena that were reported by Allied aircraft pilots in World War II, which were
	 known collectively as foo fighters. Prior to the release of Foo Fighters' 1995
	debut album Foo Fighters, which featured Grohl as the only official member, Groh
	l recruited bassist Nate Mendel and drummer William Goldsmith, both formerly of
	Sunny Day Real Estate, as well as fellow Nirvana touring bandmate Pat Smear as g
	uitarist to complete the lineup. The band began with performances in Portland, O
	regon. Goldsmith quit during the recording of the group's second album, The Colo
	ur and the Shape (1997) when most of the drum parts were re-recorded by Grohl hi
	mself. Smear's departure followed soon afterward.", :image "images/FooFighters.jpg"})
	  (mc/insert "artist" {:name "AC/DC", :bio "AC/DC are an Australian hard rock band, formed in Novemb
	er 1973 by brothers Malcolm and Angus Young, who have remained constant members.
	 Commonly referred to as a hard rock or blues rock band, they are also considere
	d pioneers of heavy metal and are sometimes classified as such, though they have
	 always dubbed their music as simply rock and roll. To date they are one of the
	highest-grossing bands of all time.", :image "images/acdc.png"})
  (mc/insert "users" {:firstname "Ivan", :lastname "Jovanovic", :username "admin", :email "ivan@ivan.com", :password "admin"}))

  
