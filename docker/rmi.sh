#!/bin/bash

docker image ls | grep '<none>' | awk '{print $3}' | xargs docker rmi