while true; do
if [[ `git status --porcelain` ]]; then
	echo 'no changes'
else
	git pull
	cd backend
	mvn package
	pkill -f 'node_server'
	java -jar -Dspring.config.location=~/.config/node_server/application.properties target/node_server-*.jar
fi
sleep 60
done
