FROM openjdk:17-alpine
ADD target/OpenSchoolTask3-0.0.1-SNAPSHOT.jar openSchoolTask3.jar
ENTRYPOINT ["java", "-jar", "openSchoolTask3.jar"]