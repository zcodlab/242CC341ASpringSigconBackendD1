# Fase 1: Construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Ejecución
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/242CC341ASpringSigconBackendD1-0.0.1-SNAPSHOT.jar app.jar
#COPY .env .env
#ENV SPRING_CONFIG_IMPORT=optional:file:.env[.properties]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
