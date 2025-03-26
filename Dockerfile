# 1) Build stage: use a Maven image to compile and package the Spring Boot app
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the Maven project files into the container
COPY pom.xml .
# Download any needed dependencies; this helps with caching in Docker
RUN mvn dependency:go-offline -B

# Now copy the entire source and build the JAR
COPY /src ./src
RUN mvn clean package -DskipTests

# 2) Runtime stage: use a lightweight JRE to run the built application
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]