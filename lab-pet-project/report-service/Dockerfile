FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /build
COPY .mvn .mvn
COPY mvnw mvnw
COPY pom.xml pom.xml
COPY src src
RUN chmod +x mvnw || true
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY --from=builder /build/target/report-service-0.0.1-SNAPSHOT.jar /app/report-service.jar
EXPOSE 8082
CMD ["java", "-jar", "/app/report-service.jar"]
