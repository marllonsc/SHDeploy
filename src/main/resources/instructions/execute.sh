cd /home/wolffsc/SHDeploy
mvn clean package
java -jar -Dserver.port=8081 target/ShDeploy.jar
