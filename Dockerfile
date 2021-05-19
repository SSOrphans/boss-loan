FROM openjdk
COPY ./boss-loan-service/target/boss-loan-service-0.0.1-SNAPSHOT.jar /app/
CMD java -jar /app/boss-loan-service-0.0.1-SNAPSHOT.jar