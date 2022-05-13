FROM adoptopenjdk/openjdk11
ENV THE_APP_JAR notification-srv-0.0.1-SNAPSHOT.jar
ENV THE_APP_JAR_PATH notification-srv/target/$THE_APP_JAR
RUN mkdir /opt/app
COPY $THE_APP_JAR_PATH /opt/app
CMD ["sh", "-c", "java -jar /opt/app/$THE_APP_JAR"]
LABEL "project"="notification"
