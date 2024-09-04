# docker build -t bankdoc .
# docker-compose down
# docker-compose up -d --build

#docker run --name mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin mongo:latest

FROM eclipse-temurin:21-jre-alpine
COPY build/libs/bankdoc-V2.jar /app/bankdoc-V1.jar
ENTRYPOINT ["java", "-jar", "/app/bankdoc-V1.jar"]
EXPOSE 8080
RUN apk --update --no-cache add curl
HEALTHCHECK --interval=1m --timeout=30s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1
LABEL version="0.1" \
    description="Bank microservice using MongoDB including Docker containers and health check test"
