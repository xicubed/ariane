(ns ariane.browser

  (:require [ariane.core :as ac]
            [clojure.test]
            [clj-webdriver.taxi]))

(defn square [x] (* x x))

;; put this in your ~/.profile, reload it w/source ~/.profile, restart intellij then access in clojure with (System/getenv "FLIPBOARD_USERNAME")
;; export FLIPBOARD_USERNAME='flipboardusername'
;; export FLIPBOARD_PASSWORD='flipboardpassword'

;; (use 'clj-webdriver.taxi)
;; (.getTime (new java.util.Date))
;; (set-driver! {:browser :chrome} (str "https://share.flipboard.com/bookmarklet/popout?v=2&title=&url=https%3A%2F%2Fwww.google.com%2Fwebhp%3Fsourceid%3Dchrome-instant%26espv%3D210%26ie%3DUTF-8&t=" (.getTime (new java.util.Date)))
;; (input-text "#username" (System/getenv "FLIPBOARD_USERNAME"))
;; (input-text "#password" (System/getenv "FLIPBOARD_PASSWORD"))
;; (submit (find-element {:tag :button, :text "Sign In"}))
;; to click the name of the magazine... in this case mal9000
;; (click (find-element {:tag :h1, :text "FLIPBOARD_MAGAZINE" }))
;; (click (find-element {:tag :button, :text "Add" }))
;; to quit the driver
;; (quit)


