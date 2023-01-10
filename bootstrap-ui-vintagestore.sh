#!/usr/bin/env bash
mvn io.quarkus:quarkus-maven-plugin:3.0.0.Alpha2:create \
    -DplatformVersion=3.0.0.Alpha2 \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=ui-vintagestore \
    -DprojectVersion=3.0.0-SNAPSHOT \
    -Dextensions="undertow, docker"
