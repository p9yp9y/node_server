git pull
cd backend
mvn package
java -jar target/*.jar -Dspring.config.location=~/.config/node_server/application.properties 
