FROM openjdk:21-jdk-slim
LABEL authors="barathisridhar"

WORKDIR /app

COPY target/persist-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "persist-0.0.1-SNAPSHOT.jar"]