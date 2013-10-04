(ns ariane.atom
  "Namespace for parsing Atom feeds."
  (:require [clojure.zip :as zip]
            [clojure.data.zip.xml :as zipxml]
            ))

;; TODO
 ; modify as was done with rss.clj


(defn- add-map-entry
  "Add key/value entry to map only if value is not nil."
  [to [key value]]
  (if value
    (assoc to key value)
    to))

(defn- links
  [links]
  (vec (for [link links]
         (let [link-spec {:href 
                          (first (zipxml/xml-> link (zipxml/attr :href)))}]
           (if-let [rel (first (zipxml/xml-> link (zipxml/attr :rel)))]
             (assoc link-spec :rel rel)
             link-spec)))))

(defn- infos
  [root]
  (-> {}
      (add-map-entry [:title (first (zipxml/xml-> root :title zipxml/text))])
      (add-map-entry [:description (first (zipxml/xml-> root :summary zipxml/text))])
      (add-map-entry [:updated (first (zipxml/xml-> root :updated zipxml/text))])
      (add-map-entry [:links (links (zipxml/xml-> root :link))])))

(defn- authors
  [authors]
  (vec (for [author authors]
         {:name (first (zipxml/xml-> author :name zipxml/text))
          :uri (first (zipxml/xml-> author :uri zipxml/text))})))

(defn- entries 
  [root]
  (for [entry (zipxml/xml-> root :entry)]
    (-> {}
        (add-map-entry [:id (first (zipxml/xml-> entry :id zipxml/text))])
        (add-map-entry [:title (first (zipxml/xml-> entry :title zipxml/text))])
        (add-map-entry [:links (links (zipxml/xml-> entry :link))])
        (add-map-entry [:updated (first (zipxml/xml-> entry :updated zipxml/text))])
        (add-map-entry [:authors (authors (zipxml/xml-> entry :author))])
        (add-map-entry [:description 
                        {:type (first (zipxml/xml-> entry :content (zipxml/attr :type)))
                         :content (first (zipxml/xml-> entry :content zipxml/text))}]))))

(defn parse-atom
  "Parse Atom feed generated by ariane.core/parse. "
  [feed]
  (let [root (zip/xml-zip feed)]
    {:infos (infos root) 
     :entries (entries root)}))
