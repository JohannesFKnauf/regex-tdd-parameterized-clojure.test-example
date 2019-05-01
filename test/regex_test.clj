(ns regex-test
  (:require [clojure.test :refer [deftest is]]))

(deftest regex-test
  (let [regex-under-test #"[-+]?\d+(\.\d+)?([eE][-+]?\d+)?"
        matching (partial re-matches regex-under-test)
        not-matching (comp not matching)]
    (do
      (is (not-matching "")             "empty string")
      (is (not-matching "a")            "single non-digit")
      (is (matching     "1")            "single digit")
      (is (matching     "123")          "integer")
      (is (matching     "-123")         "integer, negative sign")
      (is (matching     "+123")         "integer, positive sign")
      (is (matching     "123.12")       "float")
      (is (not-matching "123.12e")      "float with exponent extension but no value")
      (is (matching     "123.12e12")    "float with exponent")
      (is (matching     "123.12E12")    "float with uppercase exponent")
      (is (not-matching "123.12e12.12") "float with non-integer exponent")
      (is (matching     "123.12e+12")   "float with exponent, positive sign")
      (is (matching     "123.12e-12")   "float with exponent, negative sign"))))
