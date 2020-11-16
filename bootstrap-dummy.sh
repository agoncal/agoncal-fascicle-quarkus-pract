#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:1.9.1.Final:create \
    -DplatformVersion=1.9.1.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=dummy
# end::adocBootstrap[]
