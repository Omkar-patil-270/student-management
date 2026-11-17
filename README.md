# 🎓 Student Management System

A production-ready, full-stack Java web application built with **Spring Boot 3**, **Java 21**, **Thymeleaf**, **Bootstrap 5**, and **H2 In-Memory Database**, fully containerized using **Docker** multi-stage builds.

---

## ✨ Features

- **CRUD Operations**:
  - ➕ **Add Student**: Register students with first name, last name, email, department, GPA, and phone number.
  - 📋 **View Students**: Responsive table layout with initials avatar, department badge, and status indicators.
  - ✏️ **Edit / Update**: Modify existing student profiles with pre-filled inputs and validation checks.
  - 🗑️ **Delete Student**: Delete records with confirmation prompt.
  - 🔍 **Live Search**: Search students dynamically by first name, last name, email, or department.
- **Data Validation**: Client & server-side validation (`@NotBlank`, `@Email`, `@DecimalMin`, `@DecimalMax`).
- **In-Memory H2 Database**: Instant setup with automatic sample data seeding upon startup.
- **Embedded H2 Web Console**: Inspect database tables directly in your browser.
- **Dockerized**: Lightweight multi-stage Alpine Docker image (~180MB) running as a non-root user.

---

## 🛠️ Tech Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 21 (LTS) |
| **Framework** | Spring Boot 3.2.5 |
| **Web & Templating** | Spring MVC, Thymeleaf |
| **Styling & Icons** | Bootstrap 5.3, Bootstrap Icons |
| **Persistence** | Spring Data JPA, Hibernate |
| **Database** | H2 Database (in-memory) |
| **Build Tool** | Apache Maven & Maven Wrapper |
| **Containerization** | Docker, Docker Compose |
| **Version Control** | Git |

---

## 📁 Project Architecture

```
student-management/
├── .mvn/wrapper/                  # Maven wrapper configuration
├── src/
│   ├── main/
│   │   ├── java/com/example/studentmanagement/
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java       # HTTP request mapping & view binding
│   │   │   ├── entity/
│   │   │   │   └── Student.java                 # JPA Entity & validation rules
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java       # Data access with custom JPQL queries
│   │   │   ├── service/
│   │   │   │   ├── StudentService.java          # Service interface
│   │   │   │   └── impl/StudentServiceImpl.java # Business logic & transaction management
│   │   │   └── StudentManagementApplication.java # Spring Boot entrypoint & seed data runner
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── students.html                # Main list, dashboard stats & search
│   │       │   ├── create-student.html          # Add student form
│   │       │   └── edit-student.html            # Edit student form
│   │       └── application.properties           # Spring & H2 configurations
│   └── test/
│       └── java/com/example/studentmanagement/
│           └── StudentManagementApplicationTests.java # Unit & integration tests
├── .dockerignore
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

## 🚀 Getting Started

### 1. Run Locally with Maven Wrapper

Ensure Java 21 is installed and available on your PATH.

**On Windows (CMD / PowerShell):**
```bash
./mvnw.cmd spring-boot:run
```

**On Linux / macOS:**
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

The application will start on: **`http://localhost:8081`**

---

### 2. Run with Docker

Run directly with Docker without needing local Java or Maven installations:

```bash
# Build the Docker image
docker build -t student-management:latest .

# Run the container
docker run -d -p 8081:8081 --name student-management-app student-management:latest
```

---

### 3. Run with Docker Compose

```bash
docker compose up --build
```
To stop the application:
```bash
docker compose down
```

---

## 🗄️ H2 Database Console

Access the built-in database console to inspect SQL tables:
- **URL**: [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
- **JDBC URL**: `jdbc:h2:mem:studentdb`
- **User Name**: `sa`
- **Password**: *(leave blank)*

---

## 🧪 Running Tests

To run the automated integration tests:
```bash
./mvnw.cmd test
```

---

## 🐙 Pushing to GitHub

1. Create a new repository on [GitHub](https://github.com/new) named `student-management`.
2. Connect your local repository and push:
```bash
git remote add origin https://github.com/<YOUR-USERNAME>/student-management.git
git branch -M main
git push -u origin main
```
