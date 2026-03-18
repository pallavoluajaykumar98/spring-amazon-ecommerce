FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

EXPOSE 8070

CMD ["java", "-jar", "ecommerce-0.0.1-SNAPSHOT.jar"]
