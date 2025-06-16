FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/app-0.0.1-SNAPSHOT.jar orderservice.jar
ENTRYPOINT ["java", "-jar", "orderservice.jar"]