FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /target/vitta-challenge-ui-application-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=ci  -jar /app.jar" ]