#!/bin/sh
mvn clean test -Dtest=!multi.JmhRunnerTest -DfailIfNoTests=false
