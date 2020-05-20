FROM openjdk:8-jdk-alpine
ENV INPUT_QUEUE=input
ENV OUTPUT_QUEUE=output
ENV spring.activemq.broker-url=tcp://localhost:61616
VOLUME /tmp
COPY build/libs/erprocessor-0.1.0.jar app.jar
#RUN echo $INPUT_QUEUE
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]   