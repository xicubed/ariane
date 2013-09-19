(ns ariane.sched

  (:require [ariane.core :as ac]
            [clojurewerkz.quartzite.scheduler :as qs]
            [clojurewerkz.quartzite.triggers :as t]
            [clojurewerkz.quartzite.jobs :as j]
            [clojurewerkz.quartzite.jobs :refer [defjob]]
            [clojurewerkz.quartzite.schedule.daily-interval :refer [schedule monday-through-friday starting-daily-at time-of-day ending-daily-at with-interval-in-minutes]]))

(defrecord NoOpJob []
  org.quartz.Job
  (execute [this ctx]
    (println (ac/parse "http://news.boisestate.edu/update/feed/"))
    ))

(defn -main
[& m]
(qs/initialize)
(qs/start)
  (when (qs/started?)
    (println "The scheduler is running."))
(let [job (j/build
            (j/of-type NoOpJob)
            (j/with-identity (j/key "jobs.noop.1")))
      trigger (t/build
                (t/with-identity (t/key "triggers.1"))
                (t/start-now)
                (t/with-schedule (schedule
                                   (with-interval-in-minutes 15)
                                   (monday-through-friday)
                                   (starting-daily-at (time-of-day 8 00 00))
                                   (ending-daily-at (time-of-day 18 00 00)))))]
  (qs/schedule job trigger)))


