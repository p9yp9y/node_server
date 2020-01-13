#!/bin/bash

restart() {
	pkill -f 'start_node_server.sh'
	java -jar -Dspring.config.location=~/.config/node_server/application.properties target/node_server-*.jar &
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
init
