FROM openjdk:11
EXPOSE 8082
ADD target/cdd_project.jar cdd_project.jar
ENTRYPOINT ["java","-jar","/cdd_project.jar"]
