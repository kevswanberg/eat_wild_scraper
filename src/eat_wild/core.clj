(ns eat-wild.core
    (:require [net.cgrand.enlive-html :as html]
              [org.httpkit.client :as http]))

(defn get-dom
  []
  (html/html-snippet
      (:body @(http/get "http://www.eatwild.com/products/index.html" {:insecure? true}))))

(defn extract-states
    [dom]
    (map
        (comp :href :attrs) (html/select dom [:blockquote :a])))



(defn get-farm-dom
      [state]
      (html/html-snippet(:body @(http/get (str "http://www.eatwild.com/products/" state) {:insecure? true}))))

(defn extract-farms
   [dom]
   (map
        (comp first :content) (html/select dom [:p.bodyMargin])))

;; (defn -main
;;     []
;;     (let [states (extract-states (get-dom))]
;;         (println states)))

(defn -main
      []
      (let [paragraphs (extract-farms (get-farm-dom "alaska.html"))]
      (println (first paragraphs))))