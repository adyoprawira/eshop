FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/eshop  # Changed to /src/eshop

COPY . /src/eshop    # Copy everything to /src/eshop

RUN ./gradlew clean bootJar

FROM eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=eshop  # Changed to eshop
ARG USER_UID=1000

RUN addgroup -g ${USER_UID} ${USER_NAME} && \
    adduser -u ${USER_UID} -G ${USER_NAME} -D -h /opt/eshop ${USER_NAME} # Changed /opt/advshop to /opt/eshop

USER ${USER_NAME}

COPY --from=builder --chown=${USER_UID}:${USER_NAME} /src/eshop/build/libs/*.jar app.jar # Changed /src/advshop to /src/eshop

WORKDIR /opt/eshop  # Changed to /opt/eshop

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]