# FROM openjdk:8
# RUN mkdir /app
# COPY out/production/docker_test_2/ /app
# WORKDIR /app
# CMD java Main
#FROM openjdk:8
#RUN mkdir /app
#COPY . /app
#WORKDIR /app
#RUN javac src/Main.java
#CMD java -classpath src/ Main
#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
########
#FROM openjdk:8
#EXPOSE 8033
#ARG JAR_FILE
#ADD target/spring-boot-docker.jar spring-boot-docker.jar
#ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar $ARGS
#ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar", "$ARGS"]
##################
FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
COPY /target/*.jar docker-test-6-1.jar
EXPOSE 8033
ENTRYPOINT exec java $JAVA_OPTS -jar /docker-test-6-1.jar
# $ARGS

# java -jar spring-boot-docker.jar -e -GETVAR="Spring"
#docker build -t docker_test_3 .
# docker run  -p 8033:8033 -e GETVAR=test2 springio/gs-spring-boot-docker
#docker run -p 8033:8033 -e GETVAR=suda docker_test_3
