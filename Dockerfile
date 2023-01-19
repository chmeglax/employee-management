FROM maven:3.6.3-jdk-11-openj9
EXPOSE 8090
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]