FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/media-service-1.0.0.jar media-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "media-service.jar"]