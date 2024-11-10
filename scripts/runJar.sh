#!/bin/bash
# build, test, package for production and execute
./mvnw clean package -Dspring-boot.run.profiles=prod
java -jar target/garden-0.0.1-SNAPSHOT.jar