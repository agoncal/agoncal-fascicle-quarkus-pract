#!/usr/bin/env bash
# tag::adocSnippet[]
export DEST=src/main/resources/META-INF/resources
./node_modules/.bin/ng build --prod --base-href "."
rm -Rf ${DEST}/ui-vintagestore
mkdir -p ${DEST}/ui-vintagestore
cp -R dist/* ${DEST}
# end::adocSnippet[]

