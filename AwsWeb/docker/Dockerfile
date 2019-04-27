#FROM maven:3-jdk-7 AS app
#WORKDIR /usr/src/movieplex7
#COPY pom.xml .
#RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
#COPY . .
#RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

#FROM jboss/wildfly
#RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin --silent
#COPY --from=app aws-web-0.0.1-SNAPSHOT.war .
#EXPOSE 8080 9990
#CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

FROM java:8
VOLUME /tmp
ADD aws-web-0.0.1-SNAPSHOT.war app.war
RUN bash -c 'touch /app.war'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-war","/app.war"]
