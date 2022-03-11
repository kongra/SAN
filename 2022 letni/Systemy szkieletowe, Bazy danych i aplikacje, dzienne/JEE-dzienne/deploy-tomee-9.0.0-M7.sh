#!/bin/sh
mvn clean package
cp target/JEE-dzienne.war /media/kongra/Devel/Javasoft/apache-tomee-plume-9.0.0-M7/webapps
