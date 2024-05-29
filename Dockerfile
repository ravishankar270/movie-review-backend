FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim 
COPY --from=build /target/MoviesApp-0.0.1-SNAPSHOT.jar MoviesApp.jar
EXPOSE 8080
ENTRYPOINT ["java","jar","MoviesApp.jar"]