FROM openjdk

EXPOSE 8080

WORKDIR /app

ENV EUREKA_ENV_NET="http://discovery:8081"

COPY target/gateway-server.jar /app/gateway-server.jar

ENTRYPOINT ["java", "-jar", "gateway-server.jar"]
