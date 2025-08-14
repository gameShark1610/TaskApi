# TaskApi

TaskApi is a Spring Boot-based RESTful API project designed to explore and understand the core technologies of Spring. It serves as a learning platform for implementing best practices in API development, including security, authentication, and deployment strategies.
Builted with **Spring Boot**, **MySQL**, and **Docker**.

## 🚀 Features

- RESTful API using Spring Boot
- JWT-based authentication
- User model and role-based access control
- Secure endpoints with Spring Security
- Clean architecture and best practices
- Future deployment plans using Azure

## Prerequisites

* Java 17+
* Maven (or use Maven Wrapper `./mvnw`)
* MySQL
* Docker (optional)
* IDE (IntelliJ, VS Code, etc.)

---

## ⚡ Fast Start

1. **Clone the repo**

```bash
git clone https://github.com/gameShark1610/TaskApi.git
cd TaskApi
```

2. **Set environment variables for local (Powershell session)**

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/mydb"
$env:SPRING_DATASOURCE_USERNAME="root"
$env:SPRING_DATASOURCE_PASSWORD="1234"
```

3. **Build and run the app**

```bash
./mvnw clean install
./mvnw spring-boot:run
```

4. **Open API docs in browser**

```
http://localhost:8080/swagger-ui/index.html
```

---

## Setup

### 1. Environment variables

Create `.env` files for different environments:

* **Local MySQL** (`.env.local`):

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydb
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=1234
```

* **Docker MySQL** (`.env.docker`):

```env
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/mydb
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=1234
```

> You can also create `.env.template` with placeholders for collaborators.

### 2. Configure Spring to use `.env`

In `application.properties`:

```properties
# Choose one depending on environment
spring.config.import=optional:file:.env.local
# spring.config.import=optional:file:.env.docker

spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```

### 3. Load environment variables

**Powershell (temporary for current session):**

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/mydb"
$env:SPRING_DATASOURCE_USERNAME="root"
$env:SPRING_DATASOURCE_PASSWORD="1234"
```

**IDE (faster, per run configuration)**

* IntelliJ: *Run → Edit Configurations → Environment Variables → Add your keys*
* VS Code: *launch.json → env section*

> IDE variables override session variables, useful for switching between local and Docker.

---

### 4. Build and run

**Using Maven Wrapper (recommended):**

```bash
./mvnw clean install
./mvnw spring-boot:run
```

**Or, using global Maven (if installed):**

```bash
mvn clean install
mvn spring-boot:run
```

---

### 5. (Optional) Run with Docker

```bash
docker-compose up --build
```

---

## Access API Docs (OpenAPI / Swagger UI)

Once the application is running, access:

```
http://localhost:8080/swagger-ui.html
```

or, if using Springdoc:

```
http://localhost:8080/swagger-ui/index.html
```

---

## API Endpoints

| Method | Endpoint    | Description             |
| ------ | ----------- | ----------------------- |
| GET    | /tasks      | List all tasks          |
| GET    | /tasks/{id} | Get task by ID          |
| POST   | /tasks      | Create a new task       |
| PUT    | /tasks/{id} | Update an existing task |
| DELETE | /tasks/{id} | Delete a task           |

---

## Notes

* `.env` files separate local vs Docker environments
* Powershell or IDE environment variables make testing faster
* OpenAPI/Swagger provides an easy way to explore and test your API

---

### Authentication (planned)
