#!/bin/sh
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=JEE-niestacjonarne-1 \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=cf3ee64e75470373ff40406f2077dd4d52513824
