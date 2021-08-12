FROM maven:3.6.3-ibmjava-8-alpine AS appBuild

LABEL maintainer="stevicdule@gmail.com"

WORKDIR /usr/src/report_service
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/report_service/target/report_service.jar ./
EXPOSE 8080
CMD java -jar report_service.jar
