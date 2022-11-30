FROM maven:3.8.6-openjdk-18-slim as build_stage
LABEL type="vlad/en-mngmnt-queue-producer"
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
COPY sensor.csv .
RUN mvn clean install

FROM openjdk:18-slim
LABEL type="vlad/en-mngmnt-queue-producer"
WORKDIR /app
COPY --from=build_stage /app/target/*.jar /app/app.jar
COPY --from=build_stage /app/sensor.csv /app
EXPOSE 8080
CMD java -jar app.jar