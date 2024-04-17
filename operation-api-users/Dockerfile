FROM openjdk:17
VOLUME /tmp
EXPOSE 8001
ADD ./target/operation-api-users-0.0.1-SNAPSHOT.jar service-users.jar
ENTRYPOINT ["java","-jar","/service-users.jar"]