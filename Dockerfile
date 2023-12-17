FROM gradle:latest AS builder
WORKDIR /home/gradle/project
COPY . .
RUN gradle build -x test --no-daemon

FROM openjdk:latest
WORKDIR /app
EXPOSE 8080
COPY --from=builder /home/gradle/project/build/libs/*.jar ./app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
