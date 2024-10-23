# Social Media Backend

## Project Description

This project is a backend API for a hypothetical social media app built using the Spring Boot framework. It manages user accounts and messages, offering functionality for user registration, login, message creation, retrieval, updates, and deletion. The backend uses Spring Boot’s power to handle persistence, REST endpoints, and dependency injection seamlessly. This project follows a micro-blogging style, allowing users to post and view messages in various ways.

## Technologies Used

* Java - version 17  
* Spring Boot - version 3.1.0  
* Spring Data JPA - version 3.1.0  
* H2 Database - version 2.1.214  
* Maven - version 4.0.0  
* Postman (for API testing) - optional

## Features

* User registration and login system  
* Post, update, and delete messages  
* Retrieve all messages or by specific user  
* RESTful endpoints with proper HTTP response statuses  
* In-memory H2 database for testing purposes  
* Dependency Injection and Spring Annotations throughout  

To-do list:
* Implement message pagination  
* Add authentication tokens for enhanced security  
* Expand message text limit beyond 255 characters

## Getting Started

### Prerequisites

Make sure you have the following installed:
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) version 17 or higher  
- [Maven](https://maven.apache.org/) version 4.0.0 or higher  
- Any IDE (like IntelliJ IDEA, Eclipse, or VS Code with Java support)  
- Postman or a similar API testing tool (optional)  

### Clone the Repository

```bash
git clone <repository-url>
cd <repository-folder>
```

### Setup and Run

1. **Windows:**
   ```bash
   mvnw.cmd clean install
   mvnw.cmd spring-boot:run
   ```

2. **Unix/macOS:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

The app will be available at `http://localhost:8080`.

### Configuration

- The project uses an in-memory H2 database. Access it via:
  ```
  http://localhost:8080/h2-console
  ```
  Use `jdbc:h2:mem:testdb` as the JDBC URL.

## Usage

The API provides the following endpoints:

1. **User Registration:**  
   `POST /register`  
   Example request body:
   ```json
   {
     "username": "john_doe",
     "password": "password123"
   }
   ```

2. **User Login:**  
   `POST /login`  
   Example request body:
   ```json
   {
     "username": "john_doe",
     "password": "password123"
   }
   ```

3. **Create Message:**  
   `POST /messages`  
   Example request body:
   ```json
   {
     "postedBy": 1,
     "messageText": "Hello, world!"
   }
   ```

4. **Get All Messages:**  
   `GET /messages`

5. **Get Message by ID:**  
   `GET /messages/{messageId}`

6. **Update Message:**  
   `PATCH /messages/{messageId}`  
   Example request body:
   ```json
   {
     "messageText": "Updated text"
   }
   ```

7. **Delete Message:**  
   `DELETE /messages/{messageId}`

8. **Get Messages by User:**  
   `GET /accounts/{accountId}/messages`

## Contributors

This project is a solo effort by Peter Marozzi.

## License

This project uses the MIT License. See [LICENSE](https://opensource.org/licenses/MIT) for more details.
