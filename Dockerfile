FROM openjdk:17-slim

RUN apt-get update && \
    apt-get install -y g++ && \
    apt-get clean

ARG JAR_FILE=build/libs/compiler-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

RUN chmod +x /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
