#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn -U io.quarkus:quarkus-maven-plugin:2.5.0.Final:create \
    -DplatformVersion=2.5.0.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=rest-book \
    -DprojectVersion=2.0.0-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.book.BookResource" \
    -Dpath="/api/books" \
    -Dextensions="resteasy, resteasy-jsonb, hibernate-orm-panache, jdbc-postgresql, hibernate-validator, smallrye-openapi"
# end::adocBootstrap[]

cd rest-book

# tag::adocDependencyFaultTolerance[]
./mvnw quarkus:add-extension -Dextensions="rest-client, smallrye-fault-tolerance"
# end::adocDependencyFaultTolerance[]

# tag::adocDependencyObservability[]
./mvnw quarkus:add-extension -Dextensions="smallrye-health, smallrye-metrics"
# end::adocDependencyObservability[]

# tag::adocDependencyDocker[]
./mvnw quarkus:add-extension -Dextensions="container-image-docker"
# end::adocDependencyDocker[]
