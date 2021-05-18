#!/bin/bash

echo "> 새 애플리케이션 배포"

today=$(date "+%Y%m%d%H%M")

nohup java -jar ./build/libs/test.jar >> ./${today}.log &