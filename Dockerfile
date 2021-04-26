FROM openjdk:8-jdk-alpine
COPY target/retroboard.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]