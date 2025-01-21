# Spring Boot Projects Portfolio for Interview Assignment

This repository consolidates multiple Spring Boot applications into a single, well-organized project. Each subproject serves a distinct purpose and is independently functional, yet they share a common structure to enable easy management and deployment.

### Projects Overview

This repository contains the following Spring Boot applications:

1. **Auth JWT Service (`auth-jwt-service`)**  
   A secure authentication service leveraging JSON Web Tokens (JWT) for user login, token generation, and token refreshing.

2. **Banking System (`banking-system`)**  
   A Simple Command-Line Calculator for banking system allowing users to manage their accounts, view balances, and perform transactions securely and create accounts.

3. **Books API (`books-api`)**  
   A RESTful API for managing a collection of books, enabling users to perform CRUD operations on book records.

4. **To-Do Application (`todo-app`)**  
   A simple to-do list application that allows users to create, update, and delete tasks, with a focus on productivity.

---

### Table of Contents

- [Projects Overview](#projects-overview)
- [Technologies Used](#technologies-used)
- [Installation and Setup](#installation-and-setup)
- [Running the Applications](#running-the-applications)
- [Configuration](#configuration)
- [Directory Structure](#directory-structure)

---

### Technologies Used

Each subproject is built using the following technologies:

- **Spring Boot 3.4.1**
- **Java 23**
- **Maven** (for dependency management and build automation)
- **JWT (JSON Web Token)** for authentication and authorization
- **Spring Data JPA** (for database interaction)
  - **PostgresQL / H2** (database options may vary by project)
- **Spring Security** (for securing the applications)
- **Docker ** (for containerization, if applicable)

---

### Installation and Setup

#### Prerequisites

Ensure you have the following installed on your machine:

- **Java 23** or higher
- **Maven** for build automation
- A compatible **IDE** such as [IntelliJ IDEA](https://www.jetbrains.com/idea/)

#### Steps to Clone and Build the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/kevinjam/spring-boot-interview-projects.git
   cd spring-boot-interview-projects

2. Build all projects using Maven:

   ```bash
   mvn clean install
   ```

   This will compile and package all the subprojects.

---

### Running the Applications

You can run each project independently or run all the projects using the parent `pom.xml`.

#### To run an individual project:

1. Navigate to the project folder:
   ```bash
   cd auth-jwt-service  # or any other project folder
   ```

2. Run the project:
   ```bash
   mvn spring-boot:run
   ```

#### To run all projects together:

You can also run the parent project to start all the subprojects simultaneously.

```bash
  cd spring-boot-interview-projects
  mvn clean install
```

---

### Configuration

Each subproject has its own `application.yml`  file for configuration. Below are the key configurations that may require adjustments:

- **Database Configuration**: Configure your preferred database in the `application.properties` or `application.yml` for each project.
- **JWT Secret**: Set up a secret key for JWT generation and validation in the `application.yml` of the `auth-jwt-service`.
- **Server Ports**: Each project has a default port defined. You can modify these if needed to avoid port conflicts.

---

### Directory Structure

The repository is structured as follows:

```
multi-module-spring-boot-app/
├── auth-jwt-service/       # Authentication service using JWT
├── banking-system/         # Banking system for managing accounts and transactions ( Command-Line App)
├── books-api/              # REST API for managing books
└── todo-app/               # To-Do application for task management with security Jwt
```

- Each subproject contains its own `pom.xml` and Spring Boot application.
- The root directory contains a parent `pom.xml` to build all subprojects together.

---

### Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot) for providing the framework.
- [Java 23](https://openjdk.org/) for being the programming language foundation for these applications.
- [JWT.io](https://jwt.io/) for simplifying secure authentication.