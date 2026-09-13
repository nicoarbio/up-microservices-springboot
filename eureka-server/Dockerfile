FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY eureka-server eureka-server
RUN --mount=type=cache,target=/root/.m2 \
    mvn -f eureka-server/pom.xml -B -q clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/eureka-server/target/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
