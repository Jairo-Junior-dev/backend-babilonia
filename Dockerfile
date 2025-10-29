# Etapa 1: build da aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: imagem final
FROM eclipse-temurin:21-jdk

# Argumentos passados no build
ARG USERNAME_DB
ARG PASSWORD_DB
ARG URL_DB
ARG AUTHORIZATION_SERVE
ARG REALM
ARG CLIENT_ID
ARG USERNAME
ARG PASSWORD
ARG SERVER_URL

# Promove os ARGs para variáveis de ambiente na imagem
ENV USERNAME_DB=${USERNAME_DB}
ENV PASSWORD_DB=${PASSWORD_DB}
ENV URL_DB=${URL_DB}
ENV AUTHORIZATION_SERVE=${AUTHORIZATION_SERVE}
ENV REALM=${REALM}
ENV CLIENT_ID=${CLIENT_ID}
ENV USERNAME=${USERNAME}
ENV PASSWORD=${PASSWORD}
ENV SERVER_URL=${SERVER_URL}

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
