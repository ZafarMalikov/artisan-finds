FROM ubuntu:latest AS build
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk
EXPOSE 8082
WORKDIR /app

COPY --from=build /app/build/libs/artisan_finds-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
