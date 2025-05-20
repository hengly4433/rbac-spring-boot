# === 1. Build stage ===
FROM maven:3-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy pom.xml and download dependencies first (layer caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy actual source code after dependencies
COPY src ./src

# Build the project with Maven (skip tests for speed)
RUN mvn clean package spring-boot:repackage -DskipTests -B

# === 2. Runtime stage ===
FROM eclipse-temurin:21-jre AS runner
WORKDIR /app

# Copy the fat JAR (Spring Boot repackaged JAR) from builder
COPY --from=builder /app/target/Spring-Boot-RBAC-0.0.1-SNAPSHOT.jar app.jar

# OPTIONAL: run as non-root user (recommended for containers)
USER 1000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
