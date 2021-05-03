#!/bin/bash

echo "> 새 애플리케이션 배포"

rm -f test.log

nohup java -jar ./build/libs/test.jar >> ./test.log &