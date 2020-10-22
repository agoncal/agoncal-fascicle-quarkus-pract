#!/usr/bin/env bash
# tag::adocBootstrap[]
mvn io.quarkus:quarkus-maven-plugin:1.9.0.Final:create \
    -DplatformVersion=1.9.0.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=rest-book \
    -DprojectVersion=1.1-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.BookResource" \
    -Dpath="/api/books" \
    -Dextensions="resteasy-jsonb"
# end::adocBootstrap[]

cd rest-book

./mvnw quarkus:add-extension -Dextensions="hibernate-validator"
