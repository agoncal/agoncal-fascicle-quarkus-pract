#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:1.13.2.Final:create \
    -DplatformVersion=1.13.2.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=dummy
# end::adocBootstrap[]
