# Use a base image with Java 11 and Maven installed
FROM maven:3.8.4-jdk-11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project's files to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests -Dmaven.compiler.release=11

# Use a base image with Java 11
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/target/your-application.jar .

# Set the command to run the application
CMD ["java", "-jar", "your-application.jar"]
