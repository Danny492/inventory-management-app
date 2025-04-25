FROM amazoncorretto:21-alpine-jdk

COPY target/InventoryApp-0.0.1-SNAPSHOT.jar /ap1-v1.jar

ENTRYPOINT ["java", "-jar", "/ap1-v1.jar"]