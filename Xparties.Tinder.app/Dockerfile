FROM openjdk:23-jdk AS build
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk-slim
COPY --from=build /target/Xparties.Tinder-0.0.1-SNAPSHOT.jar Xparties.Tinder.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Xparties.Tinder.jar"]
