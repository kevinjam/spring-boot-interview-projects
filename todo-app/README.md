### README.md for To-Do Application

---

# **To-Do Application**

This is a RESTful API built using Spring Boot for managing a simple To-Do application. The API supports creating, reading, updating, and deleting tasks, with authentication and role-based access control.

---

## **Features**
- User Authentication and Authorization (JWT).
- Role-based access control (`ROLE_USER`, `ROLE_ADMIN`).
- CRUD operations for tasks.
- Integration with H2 in-memory database.
- Secure password storage using BCrypt.
- Fully secured endpoints with token-based authentication.

---

## **Technologies Used**
- Java 17
- Spring Boot 3.4.1
- Spring Security
- H2 Database
- JWT (JSON Web Token)
- Maven
- [Postman Collection](https://github.com/kevinjam/TODO-Spring-boot/blob/main/postman_collection.json)


---

## **Setup Instructions**

1. Clone the repository:
   ```bash
   git clone https://github.com/kevinjam/TODO-Spring-boot
   cd todo-app
   ```

2. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Access the H2 database console (optional):
    - URL: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: `<leave empty>`

4. Test the API using Postman or any REST client.

---

## **API Endpoints**

### **Authentication Endpoints**
| HTTP Method | Endpoint          | Description                     |
|-------------|-------------------|---------------------------------|
| POST        | `/api/auth/register` | Register a new user              |
| POST        | `/api/auth/login`    | Login and generate JWT token     |

### **Task Endpoints**
| HTTP Method | Endpoint          | Description                     | Authorization |
|-------------|-------------------|---------------------------------|---------------|
| GET         | `/api/tasks`       | Retrieve all tasks              | `ROLE_USER` or `ROLE_ADMIN` |
| POST        | `/api/tasks`       | Create a new task               | `ROLE_USER` or `ROLE_ADMIN` |
| GET         | `/api/tasks/{id}`  | Retrieve a task by ID           | `ROLE_USER` or `ROLE_ADMIN` |
| PUT         | `/api/tasks/{id}`  | Update an existing task         | `ROLE_USER` or `ROLE_ADMIN` |
| DELETE      | `/api/tasks/{id}`  | Delete a task by ID             | `ROLE_ADMIN` |

---

## **Postman JSON Example**
Here’s a sample Postman collection for testing the API:

### **Authentication**
#### Register a User:
```json
POST /api/auth/register
Content-Type: application/json
{
  "username": "testuser",
  "email": "testuser@example.com",
  "password": "password123"
}
```

#### Login:
```json
POST /api/auth/login
Content-Type: application/json
{
  "username": "testuser",
  "password": "password123"
}
```

---

### **Task Management**
#### Create a Task:
```json
POST /api/tasks
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
{
  "title": "Learn Spring Boot",
  "description": "Understand how to build a REST API using Spring Boot.",
  "completed": false
}
```

#### Retrieve All Tasks:
```json
GET /api/tasks
Authorization: Bearer <JWT_TOKEN>
```

#### Retrieve a Task by ID:
```json
GET /api/tasks/{id}
Authorization: Bearer <JWT_TOKEN>
```

#### Update a Task:
```json
PUT /api/tasks/{id}
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
{
  "title": "Learn Spring Boot - Advanced",
  "description": "Dive deeper into Spring Boot features.",
  "completed": true
}
```

#### Delete a Task:
```json
DELETE /api/tasks/{id}
Authorization: Bearer <JWT_TOKEN>
```

---

## **H2 Database Schema**

```sql
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    completed BOOLEAN NOT NULL
);
```

---

## **Security Configuration**
- **Public Endpoints**: `/api/auth/**`
- **Protected Endpoints**:
    - Requires a valid JWT in the `Authorization` header (`Bearer <TOKEN>`).
    - Access is restricted based on roles:
        - `ROLE_USER`: Access to `/api/tasks/**`.
        - `ROLE_ADMIN`: Additional access to delete tasks.

---

## **Future Improvements**
- Add user-specific tasks (e.g., tasks belong to a specific user).
- Implement email notifications for task deadlines.
- Deploy the application to a cloud platform (e.g., AWS, Azure).
---