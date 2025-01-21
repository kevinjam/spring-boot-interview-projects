# Library Management API

## Overview
The Library Management API provides endpoints for managing a collection of books. It allows clients to perform CRUD (Create, Read, Update, Delete) operations, as well as advanced features like searching, pagination, and sorting.

This API is built using **Spring Boot** with **PostgreSQL** as the database. It also supports in-memory storage for testing or simpler setups.

---

## Features
- Add new books to the library.
- Retrieve book details by ID or list all books.
- Update book details.
- Delete books from the library.
- Search books by title, author, or genre.
- Paginate and sort book listings.

---

## Technologies Used
- **Java 23**
- **Spring Boot 3.4.1**
- **PostgreSQL** for data persistence
- **Jakarta Validation** for input validation
- **Spring Data JPA** for database interactions
- **Docker Compose Support** for setting up PostgreSQL

---

## Prerequisites
1. **Java 23** or higher installed.
2. **Docker** and **Docker Compose** installed.
3. An IDE like IntelliJ IDEA or VS Code (optional).
4. **Postman** or any REST client for testing (optional).
5. [Postman Collection](https://github.com/kevinjam/Library/blob/main/librarypostman_collection.json)
---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/kevinjam/Library.git
cd Library
```

### 2. Set Up the Database with Docker Compose
The `compose.yaml` file is provided to set up PostgreSQL easily. Run the following command:

```bash
docker compose up -d
```

This will start a PostgreSQL container with the following default configuration:
- **Database Name**: `library_db`
- **Username**: `postgres`
- **Password**: `postgres`
- **Port**: `5432`

You can modify these settings in the `compose.yaml` file if needed.

### 3. Configure the Application
Update the `application.yaml` file if you changed the database configuration:

```properties
spring:
    application:
        name: Library

    datasource:
        driver-class-name: org.postgresql.Driver
        url: jdbc:postgresql://localhost:5432/library_db
        username: postgres
        password: postgres
    jpa:
        hibernate:
        ddl-auto: update
        show-sql: true

```

### 4. Build and Run the Application
Using Maven:
```bash
./mvnw spring-boot:run
```

Using your IDE:
- Open the project.
- Locate the `BooksApplication` class.
- Run the `main` method.

The application will start on `http://localhost:8080/api/v1/books` by default.

---

## Endpoints

### **1. Create a Book**
**POST** `/api/v1/books`

Request Body:
```json
{
  "title": "Book Title",
  "author": "Author Name",
  "year": 2023,
  "isbn": "123-456-789",
  "genre": "Genre"
}
```

Response:
```json
{
  "id": 1,
  "title": "Book Title",
  "author": "Author Name",
  "year": 2023,
  "isbn": "123-456-789",
  "genre": "Genre"
}
```

---

### **2. Get All Books**
**GET** `/api/v1/books`

Query Parameters (Optional):
- `page`: Page number (default: 0)
- `size`: Page size (default: 10)
- `sortBy`: Field to sort by (default: `id`)
- `sortDirection`: `asc` or `desc` (default: `asc`)

Example:
```http
GET /api/v1/books?page=0&size=5&sortBy=year&sortDirection=desc
```

Response:
```json
{
  "content": [
    {
      "id": 1,
      "title": "Book Title",
      "author": "Author Name",
      "year": 2023,
      "isbn": "123-456-789",
      "genre": "Genre"
    }
  ],
  "totalElements": 50,
  "totalPages": 10,
  "size": 5,
  "number": 0
}
```

---

### **3. Get Book by ID**
**GET** `/api/v1/books/{id}`

Response:
```json
{
  "id": 1,
  "title": "Book Title",
  "author": "Author Name",
  "year": 2023,
  "isbn": "123-456-789",
  "genre": "Genre"
}
```

---

### **4. Update a Book**
**PUT** `/api/v1/books/{id}`

Request Body:
```json
{
  "title": "Updated Title",
  "author": "Updated Author",
  "year": 2024,
  "isbn": "123-456-789",
  "genre": "Updated Genre"
}
```

Response:
```json
{
  "id": 1,
  "title": "Updated Title",
  "author": "Updated Author",
  "year": 2024,
  "isbn": "123-456-789",
  "genre": "Updated Genre"
}
```

---

### **5. Delete a Book**
**DELETE** `/api/v1/books/{id}`

Response:
```json
{
  "message": "Book deleted successfully."
}
```

---

### **6. Search Books**
**GET** `/api/v1/books/search`

Query Parameters:
- `query`: Keyword to search in title, author, or genre.
- `page`: Page number (default: 0).
- `size`: Page size (default: 10).
- `sortBy`: Field to sort by (default: `id`).
- `sortDirection`: `asc` or `desc` (default: `asc`).

Example:
```http
GET /api/v1/books/search?query=Harry&page=0&size=5&sortBy=title&sortDirection=asc
```

Response:
```json
{
  "content": [
    {
      "id": 1,
      "title": "Harry Potter and the Philosopher's Stone",
      "author": "J.K. Rowling",
      "year": 1997,
      "isbn": "9780747532699",
      "genre": "Fantasy"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "size": 5,
  "number": 0
}
```

---

## Error Handling
- **400 Bad Request**: For validation errors.
- **404 Not Found**: When the requested book does not exist.
- **500 Internal Server Error**: For unexpected errors.
