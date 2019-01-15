#!/bin/sh
~/bin/apache-maven-3.6.0/bin/mvn clean clover:setup test clover:aggregate clover:clover
