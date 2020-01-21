#!/bin/bash

restart() {
	pkill -f 'node_server'
	java -jar -Dspring.config.location=/home/pi/.config/node_server/application.properties target/node_server-*.jar &
}

init() {
	while true; do
		if [[ `git status --porcelain` ]]; then
			echo 'no changes'
		else
			git pull
			mvn -DskipTests package
			restart
		fi
		sleep 60
	done
}

cd backend
restart
#init
