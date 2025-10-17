FROM openjdk:21

VOLUME /tmp

EXPOSE 8084

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
