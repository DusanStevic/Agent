#!/bin/bash

VERSION=${1}
DOCKERHUB_USERNAME=${2}
DOCKERHUB_PASSWORD=${3}


PRODUCTS=${DOCKERHUB_USERNAME}/agent-product-service:${VERSION}
REPORTS=${DOCKERHUB_USERNAME}/agent-report-service:${VERSION}
PURCHASES=${DOCKERHUB_USERNAME}/agent-shop-service:${VERSION}
#GATEWAY=${DOCKERHUB_USERNAME}/gateway:${VERSION}

cd AgentProductService
DOCKER_BUILDKIT=1 docker build -t ${PRODUCTS} --no-cache .

#cd ../reports
#DOCKER_BUILDKIT=1 docker build -t ${REPORTS} --no-cache .

#cd ../purchases
#DOCKER_BUILDKIT=1 docker build -t ${PURCHASES} --no-cache .

#DOCKER_BUILDKIT=1 docker build -t ${GATEWAY} --no-cache -f ./gateway/Dockerfile .

docker login --username ${DOCKERHUB_USERNAME} --password ${DOCKERHUB_PASSWORD}

docker push ${PRODUCTS}
#docker push ${REPORTS}
#docker push ${PURCHASES}
#docker push ${GATEWAY}