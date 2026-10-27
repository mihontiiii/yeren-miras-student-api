# yeren-miras-student-api

Student API (Spring Boot, CRUD) for the DevOps midterm task.

- **Student:** Miras Yeren, group IT2-2308, ID 41517
- **Stack:** Java 21, Spring Boot 3.3, Spring Data JPA, PostgreSQL, Gradle
- **Image:** `yeren-miras-student-api:v1.0-yeren-miras-41517`

## Run locally

    ./gradlew bootRun

## Run with Docker Compose

    docker compose up --build

## Endpoints

| Method | URL | Description |
|---|---|---|
| GET | /api/info | Student name and individual data |
| GET | /api/students | All students |
| GET | /api/students/{id} | One student |
| POST | /api/students | Create a student |
| PUT | /api/students/{id} | Update a student |
| DELETE | /api/students/{id} | Delete a student |

## Tests

    ./gradlew test

## Change log

- v1.0-yeren-miras-41517: initial CRUD API, tests, Docker, Jenkins pipeline
