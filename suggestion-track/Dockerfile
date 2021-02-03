FROM openjdk

WORKDIR /app

ENV SPRING_CLOUD_CONFIG="http://config-server:8083"
ENV EUREKA_ENV_NET="http://discovery:8081"

COPY target/suggestion-track-0.0.1-SNAPSHOT.jar /app/suggestion-track.jar

ENTRYPOINT ["java", "-jar", "suggestion-track.jar"]
