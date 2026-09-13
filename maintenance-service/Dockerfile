FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY commons commons
RUN --mount=type=cache,target=/root/.m2 \
    mvn -f commons/pom.xml -B -q clean install -Dmaven.test.skip=true

COPY maintenance-service maintenance-service
RUN --mount=type=cache,target=/root/.m2 \
    mvn -f maintenance-service/pom.xml -B -q clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/maintenance-service/target/*.jar app.jar

EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
