(ns nats-request.core
(:import [rpc Rpc$Request
          io.nats.client MessageHandler]))

(declare request-chunked request-single)

(def ^:private default-options
  {:timeout 1
   :chunksize 64
   :max-msg-size 768000})

(defn handle [conn path handler]
  (let [dispatcher (.createDispatcher conn handler)]
    (.subscribe dispatcher path)))

(defn request [conn path payload & [opt]]
  (let [{:keys [timeout chunksize max-msg-size]} (merge default-options opt)]
    (if (> (count payload) max-msg-size)
      (request-chunked [conn path payload chunksize])
      (request-single  [conn path payload]))))


(defn ^:private request-single
  [conn path payload]
  ; describe to 'reuest-scoped-topic'
  ; send {:request correlation-id
  ;       :response-inbox unique-topic
  ;       :payload payload}
  ; wait 'timeout' seconds for response
  (println "request-single-msg called")) 

(defn ^:private request-chunked
  "Sends a request in chunks of size 'chunksize'.
   The handler should get 1 request on the main
   path per request. A chunked request will send
   an initial request containing the inbox for the
   response. The response-inbox will get a message
   with the request-inboxes topic.
  "
  [conn path payload chunksize]
  ; describe to 'request-scoped-topic'
  ; send {:request correlation-id :response-inbox unique-topic}
  ; wait for {:request-inbox}
  ; send chunks to 'request-inbox'
  ; wait 'timeout' seconds for response
  (println "request-chunked-msg called"))

