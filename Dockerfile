FROM tomcat:latest

COPY /target/pik-konsultacje-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/pik.war

#COPY /target/server.xml /usr/local/tomcat/conf/server.xml

#COPY /target/server.xml /usr/local/tomcat/conf/web.xml

#COPY /target/pik-server.p12 /usr/local/tomcat/conf/pik-server.p12


