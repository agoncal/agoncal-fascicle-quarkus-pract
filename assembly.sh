#!/usr/bin/env bash
cp pom.xml dummy.pom.xml
mv pom_to_be_renamed.xml pom.xml

mvn assembly:single

mv pom.xml pom_to_be_renamed.xml
cp dummy.pom.xml pom.xml

mv dist/agoncal-fascicle-quarkus-practising-1.1-SNAPSHOT.zip dist/agoncal-fascicle-quarkus-practising-1.1.zip
