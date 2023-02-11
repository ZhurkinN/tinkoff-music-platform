FROM openjdk:17

ADD /target/tinkoff-music-platform-0.0.1-SNAPSHOT.jar backend.jar

ENTRYPOINT ["java", "-jar", "backend.jar"]

