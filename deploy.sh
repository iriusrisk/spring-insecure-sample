mvn compile
mvn war:war
cp target/insecure.war /opt/tomcat-prod/webapps/
