FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/pik-konsultacje-0.0.1-SNAPSHOT.war pik.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/pik.war"]