FROM openjdk:17.0.2-jdk-slim-buster
WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]