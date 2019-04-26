#!/bin/sh

java -Dwebdriver.gecko.driver=./drivers/geckodriver \
    -Dwebdriver.chrome.driver=./drivers/chromedriver \
    -jar selenium-server-standalone-3.13.0.jar \
    -role node \
    -nodeConfig nodeConfig.json
    #-timeout 60 \
