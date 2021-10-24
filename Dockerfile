FROM openjdk:11
EXPOSE 8082
ADD target/cdd-project.jar cdd-project.jar
ENTRYPOINT ["java","-jar","/cdd-project.jar"]