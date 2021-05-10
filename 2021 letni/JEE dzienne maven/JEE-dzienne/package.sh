#!/bin/sh
mvn clean package -Dtest=!edu.san.test.jmh.JmhRunnerTest
