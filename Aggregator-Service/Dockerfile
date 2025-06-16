FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/Aggregator-Service-0.0.1-SNAPSHOT.jar aggserv.jar
ENTRYPOINT ["java", "-jar", "aggserv.jar"]