FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY commons commons
RUN --mount=type=cache,target=/root/.m2 \
    mvn -f commons/pom.xml -B -q clean install -Dmaven.test.skip=true

COPY sales-service sales-service
RUN --mount=type=cache,target=/root/.m2 \
    mvn -f sales-service/pom.xml -B -q clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /workspace/sales-service/target/*.jar app.jar

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
