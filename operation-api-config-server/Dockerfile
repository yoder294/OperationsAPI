FROM openjdk:17
VOLUME /tmp
EXPOSE 8888
ADD ./target/operation-api-config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]