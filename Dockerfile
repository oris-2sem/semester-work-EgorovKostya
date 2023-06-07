FROM openjdk:17
COPY build/libs/kinopoisk-0.0.1-SNAPSHOT.war /usr/local/lib/app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.war"]