FROM openjdk:11.0.14-jre-slim
COPY build/libs/file-service-1.0.0.jar file-service.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=local", "file-service.jar"]