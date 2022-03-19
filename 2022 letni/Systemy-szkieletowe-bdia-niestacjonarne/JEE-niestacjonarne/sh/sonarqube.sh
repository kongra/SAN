#!/bin/sh
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=JEE-niestacjonarne-1 \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=4b24fd3a4274ddf58b0bc538b9e80aa0ae74807b
