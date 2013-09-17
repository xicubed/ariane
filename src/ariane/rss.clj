(ns ariane.rss
  "Namespace for parsing RSS feeds."
  (:require [clojure.zip :as zip]
            [clojure.data.zip.xml :as zipxml]
            [clj-http.client :as client]
            [clj-time.core :as ct]
            [clj-time.format :as ctf]
            [overtone.at-at :as ot]))

(def lastrun (atom (slurp "lastrun.txt") ))

(defn updatedate
  [x]
  (spit "lastrun.txt" x)
  )

(defn getlastrun
  []
  (slurp "lastrun.txt")
  )

; (def fixedlast (slurp "lastrun.txt"))
(def now (atom ""))

; for date calcs
(def custom-formatter (ctf/formatter "EEE, dd MMM yyyy HH:mm:ss Z"))

(defn- add-map-entry
  "Add key/value entry to map only if value is not nil."
  [to [key value]]
  (if (and value
           (not-empty value))
    (assoc to key value)
    to))

(defn- links
  [links]
  (vec (for [link links]

         (let [link-spec {:href
                          (first (zipxml/xml-> link zipxml/text))}]
           (if-let [rel (first (zipxml/xml-> link (zipxml/attr :rel)))]
             (assoc link-spec :rel rel)
             link-spec)
            )


         ))  )

(defn- infos
  [root]

  (reset! now (first (zipxml/xml-> root :lastBuildDate zipxml/text))      )
  (spit "lastrun.txt" (first (zipxml/xml-> root :lastBuildDate zipxml/text)) )

  )

(defn- authors
  [authors]
  (vec (for [author authors]
         {:name (first (zipxml/xml-> author :name zipxml/text))
          :uri (first (zipxml/xml-> author :uri zipxml/text))})))

(defn- entries
  [root]
  (for [entry (zipxml/xml-> root :item)]
    (if (ct/before? (ctf/parse custom-formatter (str @lastrun)) (ctf/parse custom-formatter (first (zipxml/xml-> entry :pubDate zipxml/text))))
        (client/get "http://127.0.0.1:5000" {:query-params {"url" (first (zipxml/xml-> entry :link zipxml/text) ), "title" (first (zipxml/xml-> entry :title zipxml/text))}}  )
        (reset! lastrun @now)
    )
  )
)

(defn update
  []
  ;(swap! lastrun (fn [lastrun] (lr)))
  (reset! lastrun @now)
  nil
  )

(defn parse-rss
  "Parse Atom feed generated by ariane.core/parse. "
  [feed]
  (let [root (zip/xml-zip feed)
        channel (first (zipxml/xml-> root :channel))]
    {:infos (infos channel)
     :entries (entries channel)
    })
  )
