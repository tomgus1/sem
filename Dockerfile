FROM openjdk:latest
COPY ./target/sem-0.1.0.4-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-0.1.0.4-jar-with-dependencies.jar"]