#!/usr/bin/env bash
mvn io.quarkus:quarkus-maven-plugin:1.8.3.Final:create \
    -DplatformVersion=1.8.3.Final \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=rest-number \
    -DprojectVersion=1.1-SNAPSHOT \
    -DclassName="org.agoncal.fascicle.quarkus.BookResource" \
    -Dpath="/api/books" \
    -Dextensions="resteasy-jsonb"

cd rest-book

./mvnw quarkus:add-extension -Dextensions="hibernate-validator"
