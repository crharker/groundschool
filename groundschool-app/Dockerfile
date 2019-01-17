FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/groundschool-app-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
