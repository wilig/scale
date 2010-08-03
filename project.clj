(defproject scale "0.0.1-SNAPSHOT"
  :description "Simple performance test using Clojure, Compojure and Postgres"
  :dependencies [[org.clojure/clojure "1.2.0-beta1"]
                 [org.clojure/clojure-contrib "1.2.0-beta1"]
		 [compojure "0.4.1"]
		 [hiccup "0.2.6"]
		 [ring/ring "0.2.5"]
		 [postgresql/postgresql "8.4-701.jdbc4"]]
  :dev-dependencies [[leiningen/lein-swank "1.1.0"]
		     [swank-clojure "1.2.1"]])
