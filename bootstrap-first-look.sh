#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:3.0.0.Alpha2:create \
    -DplatformVersion=3.0.0.Alpha2 \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=first-look \
    -DprojectVersion=3.0.0-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.firstlook.AuthorResource" \
    -Dpath="authors"
