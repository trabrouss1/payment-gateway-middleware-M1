FROM openjdk:21

VOLUME /tmp

EXPOSE 8085

COPY target/*.jar  app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]