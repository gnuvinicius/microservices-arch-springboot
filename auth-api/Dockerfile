# FROM maven:3.9.4-amazoncorretto-17-debian AS build-env

# ARG APP_NAME
# ARG USER_HOME_DIR="/root"
# ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# WORKDIR /app
# COPY ./target/${APP_NAME}.jar /app/${APP_NAME}.jar

FROM eclipse-temurin:17-jre-alpine

ARG APP_NAME
ENV JWT_SECRET pdkBtv9driN4lSGTNeOfBK6p5IC6iz
ENV POSTGRES_HOST ${DATABASE_HOST}
ENV POSTGRES_DATABASE garage-auth
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWD ${DATABASE_PASSWORD}

WORKDIR /app
COPY ${APP_NAME}.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar" ]