FROM openjdk:11.0.8-jre
ENV HOSTNAME=localhost
COPY target/girlpower-0.0.1-shaded.jar /girlpower.jar
CMD java -jar /girlpower.jar $HOSTNAME
