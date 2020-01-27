#!/bin/bash


set -e

while getopts ":l" option;
do
 case $option in 
 l)
  local_flag=1
  echo "set -l option: starting local docker-compose env with docker-compose.yml.local"
  ;;
 esac
done

if [ $OPTIND -eq 1 ]; then echo "no options passed: starting docker-compose env with docker-compose.yml"; fi

root=$( cd .. && pwd)

cd $root/app/speiseplan

echo "########### building servlets ############"
mvn package

echo "########### deploy servlets to application server ###########"
cp $root/app/speiseplan/target/speiseplan.war $root/docker_env/webapps/

echo "########### starting docker container ###########"
if [[ ! -z $(docker ps | grep  'tomcat\|mysql\|caddy') ]] 
    then echo "already running"
    else 
	if [[ $local_flag -eq 1 ]]
	then sudo docker-compose -f $root/docker_env/docker-compose.yml.local up -d
	else sudo docker-compose -f $root/docker_env/docker-compose.yml up -d
	fi
fi

