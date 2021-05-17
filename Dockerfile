FROM maven as stage1
COPY ./ /app/boss-loan-service
WORKDIR /app/boss-loan-service
RUN mvn -q clean package

FROM openjdk
COPY --from=stage1 /app/boss-loan-service /app/boss-loan-service
CMD java -jar /app/boss-loan-service/boss-loan-service/target/boss-loan-service-0.0.1-SNAPSHOT.jar