# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Dropwizard 5.0.0-alpha.3 microservice example running on Java 21. Produces an executable uber JAR via Maven Shade plugin.

## Build & Test Commands

```bash
# Build (includes running tests)
./mvnw clean package

# Run tests only
./mvnw test

# Run a single test class
./mvnw test -Dtest=RootResourceTest

# Run a single test method
./mvnw test -Dtest=RootResourceTest#testGetRoot

# Run the application locally
java -jar target/dropwizard-java-example.jar server src/main/resources/config.yaml

# Docker build and run
make image   # builds Docker image
make run     # runs container (ports 8080, 8081)
make up      # builds and runs
```

## Architecture

The app follows standard Dropwizard structure with a single entry point `App.java` that registers all components:

- **Resources** (JAX-RS endpoints in `resource/`): `RootResource` (GET `/`) and `ProbeResource` (GET `/probe/live`, `/probe/ready`)
- **Filter** (`DiagnosticContextFilter`): Adds UUID-based request tracing via SLF4J MDC
- **Health Check** (`DefaultHealthCheck`): Registered on admin port 8081
- **Configuration** (`AppConfig`): Extends Dropwizard `Configuration`, loaded from `src/main/resources/config.yaml`

Package: `dropwizard.java.example` (under `src/main/java/dropwizard/java/example/`)

## Testing

Tests are integration tests using `DropwizardAppExtension` which starts the full application with random ports. They use JUnit 5 and the Jersey HTTP client to make real requests against endpoints.

## Ports

- 8080: Application endpoints
- 8081: Admin/metrics/healthcheck (Dropwizard admin)
