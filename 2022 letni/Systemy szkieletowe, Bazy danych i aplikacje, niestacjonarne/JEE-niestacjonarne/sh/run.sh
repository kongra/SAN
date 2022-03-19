#!/bin/sh
MAVEN_OPTS="-server -Xms256m -Xmx1024m"
mvn exec:java -Dexec.mainClass="edu.san.Main" -Djee2.password=jee12345
