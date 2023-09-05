FROM openjdk:17-jdk-alpine
EXPOSE 8081
ADD target/ideas_app-0.0.1-SNAPSHOT.jar ideas.jar
ENTRYPOINT ["java","-jar","/ideas.jar"]