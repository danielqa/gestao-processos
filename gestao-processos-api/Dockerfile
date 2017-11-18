FROM gradle:alpine
MAINTAINER Daniel Queiroz

COPY ./gestao-processos-api /usr/src/gestao-processos-api
WORKDIR /usr/src/gestao-processos-api

USER root
ENTRYPOINT gradle bootRun --stacktrace

EXPOSE 8080