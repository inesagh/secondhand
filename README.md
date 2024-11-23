# Second-Hand Clothes project with Spring Framework

This project demonstrates how to set up a Spring Boot application with Spring JPA, Spring Security, Flyway for database migration, JUnit 5 for testing, Mockito for mocking, and Docker Compose to manage the application and MySQL database.

## Tech Stack

- **Spring Boot** - Backend framework.
- **Spring JPA** - ORM for database interaction.
- **Spring Security** - For securing the application.
- **Flyway** - Database migration tool.
- **MySQL** - Database.
- **JUnit 5, Mockito** - For testing.
- **Docker** - For containerization and running the app in isolated environments.
- **Docker Compose** - To manage multi-container services.

## Prerequisites

Make sure you have the following tools installed:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java 17 or higher](https://www.oracle.com/java/technologies/javase-downloads.html) (for local development)
- Maven Wrapper (already included in the project)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/inesagh/secondhand.git
cd secondhand
```

### 2. Build the Application
Use the Maven Wrapper to build the project:
```bash
./mvnw clean package
```
This will generate a JAR file in the target/ directory, which will be used to build the Docker image.

### 3. Start the Application with Docker Compose
```bash
docker-compose up --build
```
This command will:
- Build the Docker image for the Spring Boot application.
- Start the MySQL container.
- Start the Spring Boot application container, which connects to MySQL and applies the Flyway migrations.

### 4. Access the Application
- Spring Boot App: http://localhost:8080
- Database Credentials:
   - Database: secondhand
   - User: user
   - Password: password

### 5. Stopping the Application
```bash
docker-compose down
```





