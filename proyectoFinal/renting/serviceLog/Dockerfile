FROM openjdk:11.0-oracle
COPY "./target/serviceLog-1.0-SNAPSHOT.jar" "appservicelog.jar"
EXPOSE 8094
ENTRYPOINT ["java","-jar", "appservice.jar"]