#!/bin/sh

java -Dwebdriver.gecko.driver=./drivers/geckodriver \
    -jar selenium-server-standalone-3.13.0.jar \
    -role standalone
