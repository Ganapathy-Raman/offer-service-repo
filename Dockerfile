FROM openjdk:18
WORKDIR /app
COPY ./target/tap-offerservice.jar /app
EXPOSE 5370
CMD ["java", "-jar", "tap-offerservice.jar"]