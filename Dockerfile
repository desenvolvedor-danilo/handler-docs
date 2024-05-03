FROM openjdk:22-slim
COPY . .
EXPOSE 8080
COPY /target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]