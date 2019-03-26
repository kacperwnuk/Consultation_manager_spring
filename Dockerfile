FROM tomcat:latest

COPY /target/pik-konsultacje-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/pik.war

