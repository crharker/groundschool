#!/usr/bin/env bash
java -jar -Dspring.profiles.active=local-test,log-dev -Dcucumber.options="--tags @acceptance,@all" target/punchh-adapter-acceptance-tests-1.0.0-SNAPSHOT.jar
