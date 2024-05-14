FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/todo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
