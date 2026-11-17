# 🎓 Student Management System

A Java-based web application built with **Spring Boot 3**, **Java 21**, **Thymeleaf**, and **Bootstrap 5**, fully containerized using **Docker** and maintaining student data **directly in the client's browser (Google Chrome `localStorage`)**.

---

## ✨ Features

- **CRUD Operations**:
  - ➕ **Add Student**: Add students with name, email, department, GPA, and phone number.
  - 📋 **View Students**: Responsive table layout with initials avatar, department badge, and GPA indicators.
  - ✏️ **Edit / Update**: Modify existing student profiles with pre-filled inputs.
  - 🗑️ **Delete Student**: Delete records with confirmation prompt.
  - 🔍 **Real-Time Live Search**: Instant search filtering as you type across first name, last name, email, or department.
- **Client-Side Persistence**:
  - Stored directly in **Google Chrome's `localStorage`**.
  - **No external database required**: zero database configuration, zero SQL setup.
  - Data persists across page reloads and browser sessions.
- **Dockerized**: Multi-stage Alpine Docker image (~150MB) running as a non-root user.

---

## 🛠️ Tech Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 21 (LTS) |
| **Backend Framework** | Spring Boot 3.2.5 (Spring MVC) |
| **Templating** | Thymeleaf |
| **Frontend Styling** | Bootstrap 5.3, Bootstrap Icons |
| **Data Storage** | Chrome Browser `localStorage` (Direct Client Storage) |
| **Build Tool** | Apache Maven & Maven Wrapper (`mvnw`) |
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
│   │   │   │   └── StudentController.java        # Web MVC controller
│   │   │   └── StudentManagementApplication.java # Spring Boot application entrypoint
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── index.html                    # Dashboard UI + Chrome LocalStorage CRUD
│   │       └── application.properties            # Clean web configuration
│   └── test/
│       └── java/com/example/studentmanagement/
│           └── StudentManagementApplicationTests.java # Context load test
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

Ensure Java 21 is installed.

**On Windows (CMD / PowerShell):**
```bash
.\mvnw.cmd spring-boot:run
```

**On Linux / macOS:**
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

Open Google Chrome at: **`http://localhost:8081`**

---

### 2. Run with Docker

```bash
# Build the Docker image
docker build -t student-management:latest .

# Run the container
docker run -d -p 8081:8081 --name student-management-app student-management:latest
```

Or with Docker Compose:
```bash
docker compose up --build
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
