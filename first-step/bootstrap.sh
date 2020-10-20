#!/usr/bin/env bash
mvn io.quarkus:quarkus-maven-plugin:1.8.3.Final:create \
    -DplatformVersion=1.8.3.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=first-step \
    -DclassName="org.agoncal.fascicle.quarkus.firststep.AuthorResource" \
    -Dpath="/authors"
