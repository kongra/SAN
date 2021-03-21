#!/bin/sh
MAVEN_OPTS="-Xms256m -Xmx1024m"
mvn compile exec:java -Dexec.mainClass="bdia.Main"
