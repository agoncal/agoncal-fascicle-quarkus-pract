#!/usr/bin/env bash
cp pom.xml pom.xml.back
mv pom_to_be_renamed.xml pom.xml

mvn assembly:single

mv pom.xml pom_to_be_renamed.xml
cp pom.xml.back pom.xml
