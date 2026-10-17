"# spring-adminhr" 

## Requirements

- JDK 25
- PostgreSQL for running the application outside the test suite

## Build and test

```powershell
.\mvnw.cmd clean verify
```

The application uses Spring Boot 4.1.1. The context-load test uses an in-memory H2 database and does not require PostgreSQL.

## API documentation

The OpenAPI 3.1 specification is in [openapi.yaml](./openapi.yaml). Open it with the OpenAPI extension in VS Code. When the application is running, springdoc also serves the generated API documentation at `http://localhost:8080/swagger-ui.html` and the OpenAPI JSON at `http://localhost:8080/v3/api-docs`.

## Run with Docker Compose

```powershell
docker compose up --build
```

Set `POSTGRES_PASSWORD` in the environment to override the Compose database password."
