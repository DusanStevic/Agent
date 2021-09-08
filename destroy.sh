#!/bin/bash

APP_NAME=${1}
HEROKU_EMAIL=${2}
HEROKU_API_KEY=${3}
TERRAFORM_PG_BACKEND=${4}
CONTAINER_NAME=${5:-terraform-destroy}

docker create \
  --workdir /deployment \
  --entrypoint sh \
  --env APP_NAME="${APP_NAME}" \
  --env HEROKU_API_KEY="${HEROKU_API_KEY}" \
  --env HEROKU_EMAIL="${HEROKU_EMAIL}" \
  --env TERRAFORM_PG_BACKEND="${TERRAFORM_PG_BACKEND}" \
  --name "$CONTAINER_NAME" \
  danijelradakovic/heroku-terraform \
  destroy.sh

docker cp deployment/. "${CONTAINER_NAME}":/deployment/
docker start -i "${CONTAINER_NAME}"
docker rm "${CONTAINER_NAME}"