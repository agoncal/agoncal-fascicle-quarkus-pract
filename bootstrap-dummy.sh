#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:2.0.0.Final:create \
    -DplatformVersion=2.0.0.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=dummy
# end::adocBootstrap[]
