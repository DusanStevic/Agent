#!/bin/bash

VERSION=${1}
DOCKERHUB_USERNAME=${2}
DOCKERHUB_PASSWORD=${3}


PRODUCT=${DOCKERHUB_USERNAME}/agent-product-service-proba:${VERSION}
REPORT=${DOCKERHUB_USERNAME}/agent-report-service-proba:${VERSION}
SHOP=${DOCKERHUB_USERNAME}/agent-shop-service-proba:${VERSION}
FRONTEND=${DOCKERHUB_USERNAME}/agent-frontend-proba:${VERSION}

cd AgentProductService
DOCKER_BUILDKIT=1 docker build -t ${PRODUCT} --no-cache .

cd ../AgentReportService
DOCKER_BUILDKIT=1 docker build -t ${REPORT} --no-cache .

cd ../AgentShopService
DOCKER_BUILDKIT=1 docker build -t ${SHOP} --no-cache .

cd ../AgentFrontend
DOCKER_BUILDKIT=1 docker build -t ${FRONTEND} --no-cache .

docker login --username ${DOCKERHUB_USERNAME} --password ${DOCKERHUB_PASSWORD}

docker push ${PRODUCT}
docker push ${REPORT}
docker push ${SHOP}
docker push ${FRONTEND}