FROM java:8

VOLUME /tmp

ADD "target/spring-boot-admin-client-1.0-SNAPSHOT.jar" app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 81

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]