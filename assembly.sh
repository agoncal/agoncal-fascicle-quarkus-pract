#!/usr/bin/env bash
mv pom_to_be_renamed.xml pom.xml
mv rest-number/pom_to_be_renamed.xml rest-number/pom.xml
mv rest-book/pom_to_be_renamed.xml rest-book/pom.xml

mvn assembly:single
