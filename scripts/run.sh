#!/bin/bash
# Run with fast restart and live reload if springboot devtools is installed
../mvnw -f .. clean spring-boot:run -Dspring-boot.run.profiles=dev