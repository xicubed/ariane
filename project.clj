(defproject ariane "0.1.0-SNAPSHOT"
  :description "Post RSS feeds to flipthis-webhook"
  :url "https://github.com/xicubed/ariane"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.zip "0.1.1"]
                 [clj-http "0.7.6"]
                 [clj-time "0.6.0"]
                 [overtone/at-at "1.2.0"]
                 [clojurewerkz/quartzite "1.1.0"]
                 [clj-webdriver "0.6.0"]]
  :main ariane.sched)
