git pull
cd backend
mvn package
java -jar -Dspring.config.location=~/.config/node_server/application.properties target/*.jar
