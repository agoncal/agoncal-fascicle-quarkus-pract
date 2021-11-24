#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:2.5.0.Final:create \
    -DplatformVersion=2.5.0.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=event-inventory \
    -DprojectVersion=2.0.0-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.inventory.InventoryResource" \
    -Dpath="/api/inventory" \
    -Dextensions="resteasy, resteasy-jsonb, mongodb-panache, smallrye-openapi, kafka"
# end::adocBootstrap[]

cd event-inventory

# tag::adocDependencyFaultTolerance[]
./mvnw quarkus:add-extension -Dextensions="rest-client, smallrye-fault-tolerance"
# end::adocDependencyFaultTolerance[]
