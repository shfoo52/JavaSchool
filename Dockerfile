# -------------------------
# Build Stage
# -------------------------
FROM maven:3.9.0-openjdk-17-slim AS build
WORKDIR /app
# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src
# Build the project and package it as a JAR; tests are skipped for speed
RUN mvn clean package -DskipTests

# -------------------------
# Run Stage
# -------------------------
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the built JAR from the build stage to this image
COPY --from=build /app/target/*.jar app.jar
# Expose the port that your Spring Boot app listens on (usually 8080)
EXPOSE 8080
# Use the ENTRYPOINT to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]