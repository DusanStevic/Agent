#!/bin/bash

VERSION=${1}
DOCKERHUB_USERNAME=${2}
DOCKERHUB_PASSWORD=${3}


#GATEWAY=${DOCKERHUB_USERNAME}/gateway:${VERSION}
PRODUCTS=${DOCKERHUB_USERNAME}/products:${VERSION}
#PURCHASES=${DOCKERHUB_USERNAME}/purchases:${VERSION}
#REPORTS=${DOCKERHUB_USERNAME}/reports:${VERSION}

#DOCKER_BUILDKIT=1 docker build -t ${GATEWAY} --no-cache -f ./gateway/Dockerfile .

cd AgentProductService
DOCKER_BUILDKIT=1 docker build -t ${PRODUCTS} --no-cache .

#cd ../purchases
#DOCKER_BUILDKIT=1 docker build -t ${PURCHASES} --no-cache .

#cd ../reports
#DOCKER_BUILDKIT=1 docker build -t ${REPORTS} --no-cache .


#docker login --username ${DOCKERHUB_USERNAME} --password=${DOCKERHUB_PASSWORD}
docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD }
#docker login -u ${2} -p ${3}
#docker push ${GATEWAY}
docker push ${PRODUCTS}
#docker push ${PURCHASES}
#docker push ${REPORTS}