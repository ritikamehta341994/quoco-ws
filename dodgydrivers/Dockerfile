FROM openjdk:11.0.8-jre
ENV HOSTNAME=localhost
COPY target/dodgydrivers-0.0.1-shaded.jar /dodgydrivers.jar
CMD java -jar /dodgydrivers.jar $HOSTNAME
