# Repository Summary
* Docker  
* Docker Compose
* Microservices-architecture (Spring Boot - Java Framework)
* Microservices Communication using Feign client as REST client
* H2 In-Memory Database (one service one H2 In-Memory Database)
* Platform-Provided Service Discovery (Deployment infrastructure take care of service discovery)
* Service-discovery (Docker-DNS)
* API-gateway (NGINX)
* Shell Scripting

# Agent Instructions
**Start Docker Daemon:** If you're using Docker for Windows, Then simply start the desktop app installed in:
```shell
C:\Program Files\Docker\Docker\Docker Desktop.exe
```
Position yourself using **Git Bash** in the folder where `docker-compose.yml` file is:
```
cd DOCKER-COMPOSE_FILE_PATH
```
Mapping environment variables from the environment file to the docker-compose.yml file (Check how your docker-compose.yml will finally look like, after environment variables substitution)
```shell
docker-compose --env-file .env config
```
To set up infrastructure run the following command in **Git Bash**:
```shell
docker-compose up --build
```
Docker-compose services/containers listing (one service one container)
```shell
docker-compose ps
```
```shell
docker ps
```
Request:
```GET http://localhost:8080/api/consumer/hello```  
```
http://localhost:8080/api/consumer/hello
```
Response:
```
Hello from consumer service with ip address 172.18.0.3!
```
Request:
```GET http://localhost:8080/api/kitchen/hello```  
```
http://localhost:8080/api/kitchen/hello
```
Response:
```
Hello from kitchen service with ip address 172.18.0.4!
```
Request:
```GET http://localhost:8080/api/order/hello```  
```
http://localhost:8080/api/order/hello
```
Response:
```
Hello from order service with ip address 172.18.0.2!
```
Fill H2 In-Memory Database with data using shell script. Run the following command in **Git Bash**
```
./web_requests.sh
```
Request:
```GET http://localhost:8080/api/consumer/verify/1```  
```
http://localhost:8080/api/consumer/verify/1
```
Response:
```
true
```
Check if data is added to H2 In-Memory Database using the **H2 dashboard**

**Consumer-Service** H2 In-Memory Database
```
http://localhost:9000/h2-console
```
```
JDBC URL:jdbc:h2:mem:myDb → paste jdbc:h2:mem:myDb  → Test Connection  → Connect → Click CONSUMER table → Run
```
**Order-Service** H2 In-Memory Database
```
http://localhost:9001/h2-console
```
```
JDBC URL:jdbc:h2:mem:myDb → paste jdbc:h2:mem:myDb  → Test Connection  → Connect → Click ORDER table → Run
```
**Kitchen-Service** H2 In-Memory Database
```
http://localhost:9002/h2-console
```
```
JDBC URL:jdbc:h2:mem:myDb → paste jdbc:h2:mem:myDb  → Test Connection  → Connect → Click TICKET table → Run
```
To destroy infrastructure run the following command in **Git Bash**:
```shell
docker-compose down -v
```

