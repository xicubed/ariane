(ns ariane.core
  "Generic namespace for feed type autodetection and parsing."
  (:require [clojure.xml :as xml]
            [ariane.rss :as rss]
            [ariane.atom :as atom]
            [clj-time.core :as ct]
            [clj-time.format :as ctf]
            [overtone.at-at :as ot]))


; scheduling
; set the feed you want to post
(def my-url "http://news.boisestate.edu/update/feed/")
(def my-pool (ot/mk-pool))
;; (ot/every 60000 (parse my-url) my-pool :desc "polls periodically")

;; scheduling stuff https://github.com/overtone/at-at




;(ot/show-schedule my-pool)

; to stop
; (overtone.at-at/stop-and-reset-pool! ariane.rss/my-pool)
; or force
; (overtone.at-at/stop-and-reset-pool! ariane.rss/my-pool :strategy :kill)

(defn- rss-feed?
  [feed]
  (-> feed :tag (= :rss)))

(defn- atom-feed?
  [feed]
  (-> feed :tag (= :feed)))

(defn parse
  "Detects the feed type and calls the appropriate parser."
  [source]
  (let [feed (xml/parse source)]
    (cond 
     (rss-feed? feed) (rss/parse-rss feed)
     (atom-feed? feed) (atom/parse-atom feed)
     :else (println "Unsupported format")))
 )

(defn runme
  []
  (println (parse my-url)))
  ;(do (parse my-url)))

(ot/every 360000 runme my-pool :desc "polls periodically" :initial-delay 10000)