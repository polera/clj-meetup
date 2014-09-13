(defproject clj-meetup "0.1.0-SNAPSHOT"
  :description "A Clojure library for the meetup.com API"
  :url "https://github.com/polera/clj-meetup"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
				 [http-kit "2.1.16"]
				 [cheshire "5.3.1"]
				 [environ/environ.core "0.3.1"]])