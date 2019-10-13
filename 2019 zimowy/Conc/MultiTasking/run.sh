#!/bin/sh
MAVEN_OPTS="-server -Xms256m -Xmx1024m"
mvn exec:java -Dexec.mainClass="multi.Main"
