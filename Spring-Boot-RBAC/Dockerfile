# === Stage 1: Build ===
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app

# copy maven config and source
COPY pom.xml .
COPY src ./src

# build without running tests
RUN mvn clean package -DskipTests

# === Stage 2: Package ===
FROM eclipse-temurin:17-jre
WORKDIR /app

# copy the fat JAR from builder
COPY --from=builder /app/target/*.jar app.jar

# non-root user (optional)
USER 1000

ENTRYPOINT ["java","-jar","/app/app.jar"]
