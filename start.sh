if [[ `git status --porcelain` ]]; then
  # Changes
else
	git pull
	cd backend
	mvn package
	pkill -f 'node_server'
	java -jar -Dspring.config.location=~/.config/node_server/application.properties target/node_server-*.jar
fi

exit
