## Dropwizard Java Example

[![Build](https://github.com/jecklgamis/dropwizard-java-example/actions/workflows/build.yml/badge.svg)](https://github.com/jecklgamis/dropwizard-java-example/actions/workflows/build.yml)

This is an example Dropwizard app using Java.

Docker: `docker run -p 8080:8080 -p 8081:8081 -it jecklgamis/dropwizard-java-example:main`

## What's In The Box?

* Docker image
* Kubernetes Helm chart

## Requirements

* JDK 21, Docker, Helm, Make

## Building

```bash
./mvnw clean package
docker build -t dropwizard-java-example:main .
```

## Running

```
java -jar target/dropwizard-java-example.jar
docker run -it dropwizard-java-example:main 
```

## Testing

Verify endpoints from your browser or use `curl` command.

* `http://localhost:8080/`
* `http://localhost:8080/probe/ready`
* `http://localhost:8080/probe/live`
* `http://localhost:8081/`

## Deploying To Kubernetes

This assumes you have permission to run kubectl/helm from your machine.

```bash
cd deployment && make install
```


