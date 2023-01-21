#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn -U io.quarkus:quarkus-maven-plugin:3.0.0.Alpha3:create \
    -DplatformVersion=3.0.0.Alpha3 \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=rest-number \
    -DprojectVersion=3.0.0-SNAPSHOT \
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
