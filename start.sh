if git pull 2>&1 | grep "Current branch master is up to date."; then
	echo "up to date";
else
	cd backend
	mvn package
	pkill -f 'node_server'
	java -jar -Dspring.config.location=~/.config/node_server/application.properties target/node_server-*.jar
fi
exit
