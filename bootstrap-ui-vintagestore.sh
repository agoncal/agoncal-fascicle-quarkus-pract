#!/usr/bin/env bash
mvn io.quarkus:quarkus-maven-plugin:3.0.0.Alpha3:create \
    -DplatformVersion=3.0.0.Alpha3 \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=ui-vintagestore \
    -DprojectVersion=3.0.0-SNAPSHOT \
    -Dextensions="undertow, docker"
