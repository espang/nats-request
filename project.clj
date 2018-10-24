(defproject nats-request "0.1.0"
  :description "Adds a layer on top of nats to support Request-Reply with any payload sizes."
  :url "https://github.com/espang/nats-request"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.nats/jnats "2.0.0"]])
