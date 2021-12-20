#!/bin/sh

java -Dwebdriver.gecko.driver=./drivers/geckodriver \
    -Dwebdriver.chrome.driver=./drivers/chromedriver \
    -jar selenium-server-standalone-3.141.59.jar \
    -role node \
    -timeout 600 \
    -nodeConfig nodeConfig.json
