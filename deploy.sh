#!/bin/bash

VERSION=${1}
HEROKU_EMAIL=${2}
HEROKU_API_KEY=${3}
TERRAFORM_PG_BACKEND=${4}
DOCKERHUB_USN=${5:-stevicdule}
CONTAINER_NAME=${6:-terraform-deploy}
STAGE=${7:-stage}

APP_NAME_AGENT_PRODUCTS=agent-product-service-proba
APP_NAME_AGENT_SHOPPING=agent-shop-service-proba
APP_NAME_AGENT_REPORT=agent-report-service-proba
APP_NAME_AGENT_GATEWAY=agent-gateway-proba

APP_IMAGE_NAME_AGENT_PRODUCT=${DOCKERHUB_USN}/${APP_NAME_AGENT_PRODUCTS}:${VERSION}
APP_IMAGE_NAME_AGENT_SHOPPING=${DOCKERHUB_USN}/${APP_NAME_AGENT_SHOPPING}:${VERSION}
APP_IMAGE_NAME_AGENT_REPORT=${DOCKERHUB_USN}/${APP_NAME_AGENT_REPORT}:${VERSION}
APP_IMAGE_NAME_AGENT_GATEWAY=${DOCKERHUB_USN}/${APP_NAME_AGENT_GATEWAY}:${VERSION}

APP_NAME_AGENT_PRODUCT_HEROKU=dule-agent-product-${STAGE}
APP_NAME_AGENT_SHOPPING_HEROKU=dule-agent-shopping-${STAGE}
APP_NAME_AGENT_REPORT_HEROKU=dule-agent-report-${STAGE}
APP_NAME_AGENT_GATEWAY_HEROKU=dule-agent-gateway-${STAGE}

docker create \
  --workdir /deployment \
  --entrypoint sh \
  --env APP_IMAGE_NAME_AGENT_PRODUCT="${APP_IMAGE_NAME_AGENT_PRODUCT}" \
  --env APP_IMAGE_NAME_AGENT_SHOPPING="${APP_IMAGE_NAME_AGENT_SHOPPING}" \
  --env APP_IMAGE_NAME_AGENT_REPORT="${APP_IMAGE_NAME_AGENT_REPORT}" \
  --env APP_IMAGE_NAME_AGENT_GATEWAY="${APP_IMAGE_NAME_AGENT_GATEWAY}" \
  --env APP_NAME_AGENT_PRODUCTS="${APP_NAME_AGENT_PRODUCT_HEROKU}" \
  --env APP_NAME_AGENT_SHOPPING="${APP_NAME_AGENT_SHOPPING_HEROKU}" \
  --env APP_NAME_AGENT_REPORT="${APP_NAME_AGENT_REPORT_HEROKU}" \
  --env APP_NAME_AGENT_GATEWAY="${APP_NAME_AGENT_GATEWAY_HEROKU}" \
  --env HEROKU_API_KEY="${HEROKU_API_KEY}" \
  --env HEROKU_EMAIL="${HEROKU_EMAIL}" \
  --env TERRAFORM_PG_BACKEND="${TERRAFORM_PG_BACKEND}" \
  --name "$CONTAINER_NAME" \
  danijelradakovic/heroku-terraform \
  deploy.sh

docker cp deployment/. "${CONTAINER_NAME}":/deployment/
docker start -i "${CONTAINER_NAME}"
docker rm "${CONTAINER_NAME}"