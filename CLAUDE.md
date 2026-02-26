# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build (produces fat JAR via maven-shade-plugin)
./mvnw clean package

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=RootResourceTest

# Run a single test method
./mvnw test -Dtest=RootResourceTest#testGetRoot

# Run the app locally (requires a built JAR)
java -jar target/dropwizard-java-example.jar server src/main/resources/config.yaml

# Build Docker image
make image   # or: docker build -t dropwizard-java-example:main .

# Run Docker container
make run     # maps ports 8080 and 8081

# Build JAR + Docker image + run
make up
```

## Architecture

This is a [Dropwizard 5.x](https://www.dropwizard.io/) REST service using Java 25, packaged as a self-contained fat JAR.

**Entry point:** `App.java` extends `Application<AppConfig>` and wires everything together in `run()`.

**Key components:**
- `AppConfig` — extends Dropwizard's `Configuration`; mapped from `config.yaml` (Maven resource filtering applies at build time). Currently exposes `appName`.
- `RootResource` (`GET /`) — returns JSON with the app name and a status message.
- `ProbeResource` (`GET /probe/live`, `GET /probe/ready`) — liveness and readiness endpoints for Kubernetes.
- `DiagnosticContextFilter` — JAX-RS request/response filter that puts a UUID into SLF4J MDC per request for log correlation.
- `DefaultHealthCheck` — Dropwizard health check registered at the admin connector (`http://localhost:8081/healthcheck`).

**Ports:**
- `8080` — application (Jersey/JAX-RS)
- `8081` — admin (Dropwizard metrics, health checks)

**Tests** use `DropwizardAppExtension` (JUnit 5) to spin up the full app on random ports, then make real HTTP calls via a Jersey client. Test config is loaded from `src/test/resources/config.yaml` via `ResourceHelpers.resourceFilePath`.

**Deployment:** Kubernetes Helm chart lives in `deployment/k8s/`. Deploy with `cd deployment && make install`.
