# TaskApi

TaskApi is a Spring Boot-based RESTful API project designed to explore and understand the core technologies of Spring. It serves as a learning platform for implementing best practices in API development, including security, authentication, and deployment strategies.
Builted with **Spring Boot**, **MySQL**, and **Docker**.

## 🚀 Features

- RESTful API using Spring Boot
- JWT-based authentication
- Secure endpoints with Spring Security
- Clean architecture and best practices

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
$env:JWT_SECRET="your-256bit-or-longer-jwt-secret"
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
SECRET=your-256bit-or-longer-jwt-secret
```

* **Docker MySQL** (`.env.docker`):

```env
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/mydb
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=1234
SECRET=your-256bit-or-longer-jwt-secret
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
$env:JWT_SECRET="your-256bit-or-longer-jwt-secret"
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

### Auth
| Method | Endpoint       | Description             | Auth Required |
| ------ | -------------- | ----------------------- | ------------- |
| POST   | /auth/register | Register a new user     | No            |
| POST   | /auth/login    | Login and receive JWT   | No            |

### Tasks
| Method | Endpoint       | Description             | Auth Required |
| ------ | -------------- | ----------------------- | ------------- |
| GET    | /tasks         | List all tasks          | Yes           |
| GET    | /tasks/{id}    | Get task by ID          | Yes           |
| POST   | /tasks         | Create a new task       | Yes           |
| PUT    | /tasks/{id}    | Update an existing task | Yes           |
| DELETE | /tasks/{id}    | Delete a task           | Yes           |

### User
| Method | Endpoint          | Description      | Auth Required |
|--------|-------------------|------------------|---------------|
| GET    | /user/{username}} | Get the username | Yes           |

---

## Authentication
### Swagger
* Use `/auth/register` to create a new user.
* Use `/auth/login` to obtain a **JWT token**.
* Include the JWT in the **Authorization header** for protected endpoints:

```
Authorization: Bearer <your-JWT-here>
```

### Postman Quick Guide

1. **Login to get a JWT**

* **Endpoint:** `POST /auth/login`
* **Body (JSON):**

```json
{
  "username": "your_username",
  "password": "your_password"
}
```

* **Response (JSON):**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

> Copy the value of `"token"` — this is your JWT.

---

2. **Use JWT to access protected endpoints**

* Go to the **Headers** tab in Postman for any protected endpoint (e.g., `/tasks`)
* Add a header:

```
Key: Authorization
Value: Bearer <your-JWT-here>
```

* Example:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
```

* Send the request. If the token is valid, you’ll get a successful response.
* If missing or invalid → `401 Unauthorized`.

---

## 📝 Notes

* **Environment variables**:

    * Use `.env` files to separate local vs Docker environments (`.env.local`, `.env.docker`).
    * Always set a strong `SECRET` for JWT signing (256-bit or longer).
    * IDE environment variables override session variables—useful for switching environments quickly.

* **JWT Authentication**:

    * `/auth/register` → create a new user.
    * `/auth/login` → returns a JWT token.
    * Protected endpoints require `Authorization: Bearer <JWT>` header.
    * Tokens are typically **short-lived**; login again to refresh.
    * Store tokens securely in Postman or frontend apps.

* **Postman / Swagger Testing**:

    * Postman: set JWT in Authorization header to test protected endpoints.
    * Swagger UI: configure security scheme to use JWT via **Authorize button**.
    * Both tools make testing and exploration of your API easier.

* **Security**:

    * Passwords are hashed with Bcrypt for safe storage.
    * JWT ensures secure access to endpoints without exposing passwords.
    * Always use HTTPS in production to protect tokens in transit.

* **Development tips**:

    * Use `./mvnw clean install` and `./mvnw spring-boot:run` for consistent builds.
    * Docker helps replicate environments across machines.
    * Keep README updated for collaborators to test and use the API easily.


