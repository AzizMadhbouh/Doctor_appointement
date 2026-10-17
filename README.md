# Doctor Appointment API

A **Spring Boot** REST API for managing doctors and patients in an appointment-booking
system, backed by **PostgreSQL** via Spring Data JPA. Ships with a Docker Compose
database so it runs locally in one command.

![Java](https://img.shields.io/badge/java-17-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/build-maven-C71A36?logo=apachemaven&logoColor=white)

---

## Table of contents

- [Overview](#overview)
- [Architecture](#architecture)
- [API reference](#api-reference)
- [Tech stack](#tech-stack)
- [Project structure](#project-structure)
- [Getting started](#getting-started)
- [Configuration](#configuration)
- [Data model](#data-model)
- [Roadmap](#roadmap)

---

## Overview

The service exposes CRUD endpoints for two core entities — **Doctors** and **Patients** —
using the standard Spring layered architecture (`Controller → Service → Repository → Entity`).
Entities are persisted to PostgreSQL with Hibernate (`ddl-auto: update`, so tables are
created/updated automatically on startup).

> **Status:** work in progress. Doctor and Patient `create`/`read` endpoints are
> implemented; appointment scheduling/availability is scaffolded (`schedules/Availability`)
> but not yet wired to a controller — see [Roadmap](#roadmap).

---

## Architecture

```
Client ──HTTP──▶ @RestController ──▶ @Service ──▶ Repository (JPA) ──▶ PostgreSQL
                 DoctorController     DoctorService    DoctorRepo
                 PatientController    Patientservice   PatientRepo
```

Package layout follows the standard Spring convention: one package per domain
(`doctors`, `patient`, `schedules`).

---

## API reference

Base URL: `http://localhost:8080`

### Doctors

| Method | Path | Description |
|---|---|---|
| `GET` | `/doctors` | List all doctors |
| `GET` | `/doctors/{id}` | Get a doctor by id |
| `POST` | `/doctors` | Create a doctor |

**Create a doctor**

```bash
curl -X POST http://localhost:8080/doctors \
  -H "Content-Type: application/json" \
  -d '{"name":"Dr. Smith","speciality":"Cardiology","adresse":"12 Main St"}'
```

### Patients

| Method | Path | Description |
|---|---|---|
| `GET` | `/patient` | List all patients |
| `GET` | `/patient/{id}` | Get a patient by id |
| `POST` | `/patient` | Create a patient |

**Create a patient**

```bash
curl -X POST http://localhost:8080/patient \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","phone":"+216 50 000 000","email":"jane@example.com"}'
```

---

## Tech stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.2 |
| Web | Spring Web (REST) |
| Persistence | Spring Data JPA / Hibernate |
| Database | PostgreSQL 16 |
| Build | Maven (wrapper included) |
| Containerization | Docker Compose (database) |
| Testing | Spring Boot Test |

---

## Project structure

```
Doctor_appointement/
├── docker-compose.yml               # PostgreSQL 16
├── pom.xml
├── mvnw / mvnw.cmd / .mvn/          # Maven wrapper
└── src/
    ├── main/
    │   ├── java/com/test/demo/
    │   │   ├── DemoApplication.java         # @SpringBootApplication entry point
    │   │   ├── doctors/
    │   │   │   ├── DoctorEntity.java        # @Entity → table "doctors"
    │   │   │   ├── Doctor.java              # plain DTO
    │   │   │   ├── DoctorController.java    # /doctors
    │   │   │   ├── DoctorService.java
    │   │   │   └── DoctorRepo.java
    │   │   ├── patient/
    │   │   │   ├── PatientEntity.java       # @Entity → table "patients"
    │   │   │   ├── PatientController.java   # /patient
    │   │   │   ├── Patientservice.java
    │   │   │   └── PatientRepo.java
    │   │   └── schedules/
    │   │       └── Availability.java        # startat / endat (scaffold)
    │   └── resources/
    │       ├── application.properties
    │       └── application.yml              # datasource + JPA config
    └── test/java/com/test/demo/
        └── DemoApplicationTests.java
```

---

## Getting started

### Prerequisites

- Java 17+
- Docker & Docker Compose (for the database)
- Maven is optional — the wrapper (`./mvnw`) is included

### 1. Start PostgreSQL

```bash
docker compose up -d
```

This starts PostgreSQL 16 on port `5432` with database `doctor_booking`
(user `doctor_user` / password `doctor_pass`).

### 2. Run the application

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

The API is then available at `http://localhost:8080`.

### 3. Run the tests

```bash
./mvnw test
```

---

## Configuration

`src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/doctor_booking
    username: doctor_user
    password: doctor_pass
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

> The compose file and `application.yml` share the same default credentials so the app
> connects without extra setup. **For any real deployment, move these credentials to
> environment variables / a secret store — they must not stay hard-coded.**

---

## Data model

**`doctors`**

| Field | Type |
|---|---|
| id | Long (identity PK) |
| name | String |
| speciality | String |
| adresse | String |

**`patients`**

| Field | Type |
|---|---|
| id | Long (identity PK) |
| name | String |
| phone | String |
| email | String |

Tables are created automatically by Hibernate on first run (`ddl-auto: update`).

---

## Roadmap

- [ ] Wire up appointment booking and the `Availability` schedule model
- [ ] Add `PUT` / `DELETE` endpoints for doctors and patients
- [ ] Bean validation (`@Valid`) on request bodies
- [ ] DTO ↔ entity mapping (avoid exposing entities directly)
- [ ] Global exception handling (`@ControllerAdvice`) — replace `RuntimeException`
- [ ] Integration tests with Testcontainers
- [ ] Authentication & authorization
- [ ] Externalize DB credentials to environment variables
