#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:1.8.3.Final:create \
    -DplatformVersion=1.8.3.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=rest-number \
    -DprojectVersion=1.1-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.number.NumberResource" \
    -Dpath="/api/numbers" \
    -Dextensions="resteasy, resteasy-jsonb, smallrye-openapi"
# end::adocBootstrap[]

cd rest-number

./mvnw quarkus:add-extension -Dextensions="hibernate-validator"
