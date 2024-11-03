#!/bin/bash
# Package for production and execute
./mvnw package
java -jar target/garden-0.0.1-SNAPSHOT.jar