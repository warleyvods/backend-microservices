FROM openjdk

WORKDIR /app

ENV SPRING_CLOUD_CONFIG="http://config-server:8083"
ENV EUREKA_ENV_NET="http://discovery:8081"

COPY target/suggestion-music.jar /app/suggestion-music.jar

ENTRYPOINT ["java", "-jar", "suggestion-music.jar"]
