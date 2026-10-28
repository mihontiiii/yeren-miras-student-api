# yeren-miras-student-api

Student API: Spring Boot CRUD application for the DevOps midterm task.

- Author: Miras Yeren
- Group: IT2-2308
- Student ID: 41517

## Stack

- Java 21, Spring Boot 3.3.5, Gradle
- Spring Data JPA, PostgreSQL 16
- Docker, Docker Compose, Jenkins

## Run locally

```
docker compose up --build
```

Application: http://localhost:8081/api/info

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | /api/info | Student info |
| GET | /api/students | List all students |
| GET | /api/students/{id} | Get student by ID |
| GET | /api/students/search?lastName={name} | Search students by last name (Miras Yeren, 41517) |
| POST | /api/students | Create student |
| PUT | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |