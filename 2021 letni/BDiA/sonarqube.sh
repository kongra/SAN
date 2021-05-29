#!/bin/sh
mvn sonar:sonar \
  -Dsonar.projectKey=BDiA \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=3076bbcb308207580a821ed61f7efdc0c48b513f
  
