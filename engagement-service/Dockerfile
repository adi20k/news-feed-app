FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/engagement-service-1.0.0.jar engagement-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "engagement-service.jar"]