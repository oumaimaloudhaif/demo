#Stages build
FROM maven:3.8.4-openjdk-17
ADD . /
WORKDIR /
RUN ls -l
RUN mvn  clean install


#Stages run
# 1.Select the image related to the project
FROM amazoncorretto:17-alpine-jdk
VOLUME /tmp
#2.This command will copy the generated jar module to the jar existing to the registry
COPY --from=0 "/target/demo-0.0.1-SNAPSHOT.jar" demo-1.0.0.jar
#3.Expose the inner port of the application
EXPOSE 8080
#4.It's the entrypoint to run the docker image
ENTRYPOINT ["java","-jar","/demo-1.0.0.jar"]
