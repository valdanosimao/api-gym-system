FROM openjdk:11

ADD target/gym-0.0.1-SNAPSHOT.jar gym-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/gym-0.0.1-SNAPSHOT.jar"]



