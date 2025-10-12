FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/tweet-service-1.0.0.jar tweet-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "tweet-service.jar"]