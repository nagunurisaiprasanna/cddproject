# Install Tomcat & openjdk 8 (has java and javac)
FROM tomcat:jdk8-openjdk

# Copy source files to tomcat folder structure
COPY . /user/acer/tomcat/webapps/

# -cp, Adding compile time classpath as Tomcat's /lib/servlet-api.jar file.
# - d, destination output location.
#RUN ["javac", "-cp", ".:/user/acer/tomcat/lib/servlet-api.jar", "-d", "/user/acer/tomcat/webapps/myApp/WEB-INF/classes/", "/usr/local/tomcat/webapps/myApp/src/TestingServlet.java"]

# Serve Tomcat
EXPOSE 8080
CMD ["catalina.sh", "run"]
