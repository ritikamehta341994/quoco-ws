FROM openjdk:11.0.8-jre
ENV HOSTNAME=localhost
COPY target/client-0.0.1-shaded.jar /client.jar
CMD java -jar /client.jar $HOSTNAME
