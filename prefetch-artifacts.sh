#!/usr/bin/env bash

__WORKSPACE="$(dirname "$0")"

function mvnf() {
    "$__WORKSPACE/mvnw" -f "$__WORKSPACE/pom.xml" "$@"
    return $?
}
function main() {
    echo "Prefetch Maven dependencies, sources and javadoc" &&
    mvnf de.qaware.maven:go-offline-maven-plugin:resolve-dependencies -DdownloadSources -DdownloadJavadoc -U &&
    echo "Prefetch UI dependencies" &&
    mvnf -pl ui-vintagestore/pom.xml generate-resources -P npm-install,install-node-and-npm &&
    echo "Prefetch Docker images" &&
    docker pull postgres:12.1 &&
    docker pull prom/prometheus:v2.25.1 &&
    docker pull registry.access.redhat.com/ubi8/ubi-minimal:8.4 &&
    docker pull quay.io/quarkus/quarkus-distroless-image:1.0 &&
    echo "Prefetch finished"
    return $?
}

main "$@"
exit $?
