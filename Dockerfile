ARG VERSION=8u151

FROM openjdk:${VERSION}-jdk as BUILD

COPY . /app

WORKDIR /app

RUN ./gradlew build

CMD ["./gradlew", "run"]