FROM openjdk:17-jdk-alpine

# Run application as non-root user
# https://security.stackexchange.com/questions/106860/can-a-root-user-inside-a-docker-lxc-break-the-security-of-the-whole-system
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Add --build-arg=AZURE_STORAGE_CONNECTION_STRING=$CURRENT_ENV_NAME to docker build to set this var
ARG AZURE_STORAGE_CONNECTION_STRING

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV spring_profiles_active prod
ENV AZURE_STORAGE_CONNECTION_STRING $AZURE_STORAGE_CONNECTION_STRING

ENTRYPOINT ["java", "-jar", "/app.jar"]
