FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/BankingCustomerSystem-0.0.1-SNAPSHOT.jar"]
