(ns xml-sandbox.core
  (:require
    [clojure.zip :as zip]
    [clojure.data.zip.xml :as zx]
    [clojure.data.zip :as dz]
    [clojure.data.xml :as xml]))

(def soap (zip/xml-zip (xml/parse-str (slurp "soap.xml"))))

; ((:fornavn "per" :efternavn "claus")  (:fornavn "knux" :efternavn "prutt"))

(defn xml->map
 [zxml]
 (let [nodes (zx/xml-> zxml :Body :GetSpeech :Request)]
   (mapv  #(zx/xml-> % dz/children (juxt (comp :tag zip/node) zx/text)) nodes)))
