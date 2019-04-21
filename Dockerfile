FROM tomcat:latest

COPY /target/pik-konsultacje-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/pik.war

COPY /target/server.xml /usr/local/tomcat/conf/server.xml

COPY /target/server.xml /usr/local/tomcat/conf/web.xml

COPY /target/keystore.p12 /usr/local/tomcat/conf/keystore.p12


