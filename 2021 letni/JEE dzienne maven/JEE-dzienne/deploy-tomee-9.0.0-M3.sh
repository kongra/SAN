#!/bin/sh
mvn clean package
cp target/JEE-dzienne.war ~/Javasoft/apache-tomee-plume-9.0.0-M3/webapps
