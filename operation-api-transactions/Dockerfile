FROM openjdk:17
VOLUME /tmp
EXPOSE 8002
ADD ./target/operation-api-transactions-0.0.1-SNAPSHOT.jar service-transactions.jar
ENTRYPOINT ["java","-jar","/service-transactions.jar"]