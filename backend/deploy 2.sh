#!/bin/bash

echo "> 새 애플리케이션 배포"

nohup java -jar ./build/libs/test.jar >> ./test.log &