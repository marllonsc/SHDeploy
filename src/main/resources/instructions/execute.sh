cd /home/wolffsc/SHDeploy
git pull
mvn clean package
java -jar -Dserver.port=8081 target/ShDeploy.jar
