#!/bin/sh
mvn clean install
docker container rm spring-boot-webapp
docker build -t boilerplates/spring-boot-webapp-0.0.1 .
docker run -p 80:8000 --name spring-boot-webapp -e JAVA_OPTS=-Dserver.port=8000 boilerplates/spring-boot-webapp-0.0.1
