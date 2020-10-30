#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:1.9.1.Final:create \
    -DplatformVersion=1.9.1.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=rest-number \
    -DprojectVersion=1.1-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.number.NumberResource" \
    -Dpath="/api/numbers" \
    -Dextensions="resteasy, resteasy-jsonb, smallrye-openapi"
# end::adocBootstrap[]

cd rest-number

# tag::adocDependencyFaultTolerance[]
./mvnw quarkus:add-extension -Dextensions="smallrye-fault-tolerance"
# end::adocDependencyFaultTolerance[]

# tag::adocDependencyObservability[]
./mvnw quarkus:add-extension -Dextensions="smallrye-health, smallrye-metrics"
# end::adocDependencyObservability[]

# tag::adocDependencyDocker[]
./mvnw quarkus:add-extension -Dextensions="container-image-docker"
# end::adocDependencyDocker[]
