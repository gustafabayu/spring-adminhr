"# spring-adminhr" 

## Requirements

- JDK 25
- PostgreSQL for running the application outside the test suite

## Build and test

```powershell
.\mvnw.cmd clean verify
```

The application uses Spring Boot 4.1.1. The context-load test uses an in-memory H2 database and does not require PostgreSQL.

## Run with Docker Compose

```powershell
docker compose up --build
```

Set `POSTGRES_PASSWORD` in the environment to override the Compose database password."
