FROM gradle:latest AS builder
WORKDIR /home/gradle/project
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle clean build --no-daemon > /dev/null 2>&1 || true
COPY . .
RUN gradle build -x test

FROM openjdk:latest
WORKDIR /app
EXPOSE 8080
COPY --from=builder /home/gradle/project/build/libs/*.jar ./app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
