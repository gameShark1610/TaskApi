# TaskApi

TaskApi is a Spring Boot-based RESTful API project designed to explore and understand the core technologies of Spring. It serves as a learning platform for implementing best practices in API development, including security, authentication, and deployment strategies.

## 🚀 Features

- RESTful API using Spring Boot
- JWT-based authentication
- User model and role-based access control
- Secure endpoints with Spring Security
- Clean architecture and best practices
- Future deployment plans using Azure

## ⚙️ Setup Instructions

To run this project locally, follow these steps:

1. **Clone the repository**
   ```bash
   git clone https://github.com/gameShark1610/TaskApi.git
   cd TaskApi
   ```
2. **Build the project**
   ```bash
   ./mvnw clean install
   ```
3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
4. **Access the API**
   ```bash
   http://localhost:8080
   ```
## ⚙️ API Endpoints
### Tasks
- GET /api/tasks – Get all tasks
- POST /api/tasks – Create a new task
- GET /api/tasks/{id} – Get task by ID
- PUT /api/tasks/{id} – Update task
- DELETE /api/tasks/{id} – Delete task
### Authentication (planned)
