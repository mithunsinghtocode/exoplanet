# Java 11 with Maven installed docker image
FROM adoptopenjdk/maven-openjdk11

WORKDIR /opt/app

# Copy the files to the container
COPY . .

RUN mvn package

ARG JAR_FILE=target/exoplanet.jar

# cp exoplanet.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]