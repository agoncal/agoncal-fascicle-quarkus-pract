#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:1.9.1.Final:create \
    -DplatformVersion=1.9.1.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=event-inventory \
    -DprojectVersion=1.1-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.inventory.InventoryResource" \
    -Dpath="/api/inventory" \
    -Dextensions="resteasy, resteasy-jsonb, mongodb-panache, smallrye-openapi, kafka"
# end::adocBootstrap[]

cd event-inventory

# tag::adocDependencyFaultTolerance[]
./mvnw quarkus:add-extension -Dextensions="rest-client, smallrye-fault-tolerance"
# end::adocDependencyFaultTolerance[]
