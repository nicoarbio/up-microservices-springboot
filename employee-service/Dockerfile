FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY commons commons
RUN --mount=type=cache,target=/root/.m2 \
    mvn -f commons/pom.xml -B -q clean install -Dmaven.test.skip=true

COPY employee-service employee-service
RUN --mount=type=cache,target=/root/.m2 \
    mvn -f employee-service/pom.xml -B -q clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/employee-service/target/*.jar app.jar

EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app.jar"]
