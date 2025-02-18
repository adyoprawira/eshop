FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/eshop

COPY . /src/eshop

RUN ./gradlew clean bootJar

FROM eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=eshop
ARG USER_UID=1000

RUN addgroup -g ${USER_UID} ${USER_NAME} && \
    adduser -u ${USER_UID} -G ${USER_NAME} -D -h /opt/eshop ${USER_NAME}

USER ${USER_NAME}

COPY --from=builder --chown=${USER_UID}:${USER_NAME} /src/eshop/build/libs/*.jar app.jar

WORKDIR /opt/eshop

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]