FROM openjdk:8-jdk-alpine
ARG USER
RUN addgroup -S ${USER} && adduser -S ${USER} -G ${USER}
USER ${USER}:${USER}
COPY target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]