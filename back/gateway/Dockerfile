FROM openjdk:11-jdk

WORKDIR /home/spring

COPY build/libs/*.jar /home/spring/gateway-0.0.1-SNAPSHOT.jar

EXPOSE 8083

CMD ["java", "-jar", "/home/spring/gateway-0.0.1-SNAPSHOT.jar"]