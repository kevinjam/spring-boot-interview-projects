
```markdown
# JWT Authentication with Spring Boot

This project demonstrates how to implement JWT (JSON Web Token) authentication in a Spring Boot application. It includes endpoints for logging in with a username and password, as well as refreshing an expired access token using a refresh token.

## Features
- **Login Endpoint**: Authenticates the user and generates an access token and a refresh token.
- **Refresh Token Endpoint**: Refreshes the access token using a valid refresh token.
- **JWT Authentication**: Secures API endpoints using JWT with configurable expiration times.
  
## Technologies Used
- Spring Boot 3.4.1
- Spring Security
- JJWT (JSON Web Token)
- Java 23

## Project Structure
- **`AuthController`**: Handles HTTP requests and responses for authentication.
- **`AuthService`**: Contains the business logic for handling user authentication and token generation.
- **`JwtUtil`**: A utility class for generating and validating JWT tokens.

## Endpoints

### 1. `POST /api/auth/login`

Authenticates the user by checking their username and password. If successful, it generates an access token and a refresh token.

#### Request:
```json
{
  "username": "kevin",
  "password": "hello@1234"
}
```

#### Response:
- **Success** (200 OK):
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZXZpbiIsImlhdCI6MTczNzQ5NzIyMiwiZXhwIjoxNzM3NDk3NTIyfQ.0zAmTsmjF8DoM3FsJoo4iG4PXPdsbDbycT94fMPPYIE",
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZXZpbiIsImlhdCI6MTczNzQ5NzIyMiwiZXhwIjoxNzM3NzU2NDIyfQ.QlDybtX2-nptgX2mVJ1lzRDJqtgExR3WBcU6DrYVoPg",
  "message": "Successful"
}
```
- **Failure** (401 Unauthorized):
```json
{
  "accessToken": null,
  "refreshToken": null,
  "message": "Invalid credentials"
}
```

### 2. `POST /api/auth/refresh`

Refreshes the access token using a valid refresh token. The refresh token should not be expired.

#### Request:
```json
{
  "refreshToken": "existing_refresh_token"
}
```

#### Response:
- **Success** (200 OK):
```json
{
  "accessToken": "new_generated_access_token",
  "message": null
}
```
- **Failure** (401 Unauthorized):
```json
{
  "accessToken": null,
  "refreshToken": null,
  "message": "Refresh token expired"
}
```

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/kevinjam/jwt-authentication-spring-boot.git
   ```

2. Navigate to the project directory:
   ```bash
   cd jwt-authentication-spring-boot
   ```

3. Build and run the project:
   ```bash
   ./mvnw spring-boot:run
   ```

4. The application will be available at `http://localhost:8080`.

## Dependencies
- **Spring Boot Starter Web**: For building RESTful web services.
- **Spring Security**: For securing the API.
- **JJWT**: For creating and parsing JWT tokens.

## Configuration
- **JWT Expiration**: Access tokens expire in 5 minutes and refresh tokens in 3 days.
- **Leeway**: A 5-minute leeway is used to account for time differences between the client and server.