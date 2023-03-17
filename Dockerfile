FROM openjdk:latest
COPY ./target/sem-0.1.0.5-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-0.1.0.5-jar-with-dependencies.jar"]
