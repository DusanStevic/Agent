# Stage 1: Build an Angular Docker Image
FROM stevicdule/npm-base-agent-frontend:1.0.0-dev as build
# Create app directory. This directory must have the same name as the directory in Dockerfile.base file /app.
# Our application will be copied in the same directory where npm dependencies are installed
WORKDIR /app
# No need to download again npm dependencies,
# you will use dependencies from npm base image that already have installed those dependencies
# COPY package*.json /app/
# RUN npm install
COPY . /app
ARG NODE_ENV
RUN npm run build -- --outputPath=./dist/out --configuration ${NODE_ENV}
# Stage 2, use the compiled app, ready for production with Nginx
FROM nginx
COPY --from=build /app/dist/out/ /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
