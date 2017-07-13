#!/bin/bash

echo dirname $(readlink -f $BASH_SOURCE);
DOCKER_DIR=`dirname $(readlink -f $BASH_SOURCE)`;
PHP_RUNTIME_DIR=`readlink -f "$DOCKER_DIR/../"`;
ANTLR_DIR=`readlink -f "$DOCKER_DIR/../../../"`;

IMAGE_NAME="antlr4/runtime-php7-test";

pushd $DOCKER_DIR > /dev/null

docker build --tag "${IMAGE_NAME}" . ;

docker run -i --tty \
    --volume "${ANTLR_DIR}:/opt/project" \
    --volume "${PHP_RUNTIME_DIR}/build/m2-repo-cache:/root/.m2/repository" \
    "${IMAGE_NAME}";

popd > /dev/null;
