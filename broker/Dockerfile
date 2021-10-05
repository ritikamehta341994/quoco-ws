FROM openjdk:11.0.8-jre
ENV HOSTNAME=localhost
COPY target/broker-0.0.1-shaded.jar /broker.jar
CMD java -jar /broker.jar $HOSTNAME
