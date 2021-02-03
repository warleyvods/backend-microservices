FROM openjdk

EXPOSE 8081

WORKDIR /app

COPY target/discovery-server.jar /app/discovery-server.jar

ENTRYPOINT ["java", "-jar", "discovery-server.jar"]
