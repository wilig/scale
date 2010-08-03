(ns scale.core
  (:use compojure.core, ring.adapter.jetty, hiccup.core
	[clojure.contrib.sql :as sql])
  (:import javax.sql.DataSource, org.postgresql.ds.PGPoolingDataSource))

(declare scale-app)

(def rows-to-show 10)

(def db-conn {:classname "org.postgresql.Driver"
	      :subprotocol "postgresql"
	      :subname "//localhost:5432/scale"
	      :user "scale"
	      :password "scale"})

(def db-pool {:datasource (doto (new org.postgresql.ds.PGPoolingDataSource)
			    (.setServerName   "localhost")
			    (.setDatabaseName "scale")
			    (.setUser         "scale")
			    (.setPassword     "scale")
			    (.setMaxConnections 1))})

(defn show-data []
  (sql/with-connection db-conn
    (let [count ((sql/with-query-results rs ["select count(*) from scale"] (first rs)) :count)]
      (sql/with-query-results rs [(str "select * from scale limit " rows-to-show)]
	(html
	 [:div (str "There are " count " rows, here are the first " rows-to-show)]
	 [:table
	  (map (fn[row] (html [:tr (map (fn[col] (html [:td col])) row)])) rs)])))))
	


(def server (doto (Thread. #(run-jetty #'scale-app {:port 8080})) .start))

(defroutes scale-app
  (GET "/" [] (show-data)))



