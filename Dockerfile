# -------------------------
# Build Stage
# -------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy Maven configuration and source code
COPY pom.xml . 
COPY src ./src

# Ensure Vaadin frontend is prepared before packaging
RUN mvnw vaadin:prepare-frontend

# Build the project with production settings (including frontend build)
RUN mvnw clean package -Pproduction -DskipTests

# -------------------------
# Run Stage
# -------------------------
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Spring Boot default is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
