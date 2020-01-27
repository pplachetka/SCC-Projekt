#!/bin/bash

cd speiseplan
echo " building servlets "
mvn package
echo " deploy servlets to application server "
cp target/speiseplan.war ../docker/webapps/
echo " starting docker container "
sudo docker-compose -f docker/docker-compose.yml up -d
