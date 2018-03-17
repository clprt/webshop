#!/usr/bin/env bash

docker rm -f mysql_webshop
docker run --name mysql_webshop -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=webshop -e MYSQL_USER=wsuser -e MYSQL_PASSWORD=wsuser -p 33306:3306 -d mysql:5.6