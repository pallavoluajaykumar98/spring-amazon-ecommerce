FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .

EXPOSE 8080

CMD ["java", "-jar", "ecommerce-0.0.1-SNAPSHOT.jar"]