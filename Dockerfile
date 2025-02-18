FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/advshop

RUN ./gradlew clean bootJar

FROM eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=advshop
ARG USER_UID=1000  # Set a default value for USER_UID

# Create the group and user directly (more robust)
RUN addgroup -g ${USER_UID} ${USER_NAME} && \
    adduser -u ${USER_UID} -G ${USER_NAME} -D -h /opt/advshop ${USER_NAME}

USER ${USER_NAME}

COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/advshop/build/libs/*.jar app.jar

WORKDIR /opt/advshop

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]