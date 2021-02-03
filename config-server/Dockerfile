FROM openjdk

EXPOSE 8083

WORKDIR /app

ENV EUREKA_ENV_NET="http://discovery:8081"

COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]
