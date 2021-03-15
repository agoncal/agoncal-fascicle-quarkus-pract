#!/usr/bin/env bash
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create \
    -DplatformVersion=1.12.2.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=first-step \
    -DprojectVersion=1.1-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.firststep.AuthorResource" \
    -Dpath="/authors"
