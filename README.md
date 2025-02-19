# Spring Boot User Management API

This project is a Spring Boot-based RESTful API for managing users and their addresses. It provides endpoints for creating users, retrieving user details, and checking if a user exists. The project uses a relational database to store user and address information and includes features like password encryption, validation, and exception handling.

## Key Features

1. **User Management**:
   - Create a new user with details like first name, last name, email, password, and addresses.
   - Retrieve user details by ID.
   - Check if a user exists by ID.

2. **Address Management**:
   - Each user can have multiple addresses.
   - Addresses are stored in a separate table and linked to the user via a foreign key.

3. **Password Encryption**:
   - Passwords are encrypted using `PasswordEncoder` from Spring Security before being stored in the database.

4. **Validation**:
   - Ensures that a user cannot be created without at least one address.
   - Prevents duplicate users by checking if an email already exists.

5. **Exception Handling**:
   - Custom exceptions are thrown for scenarios like user already exists, user not found, and empty addresses.

---

## Project Structure

### Key Classes

1. **`UserController`**:
   - REST controller that exposes endpoints for user-related operations.
   - Endpoints:
     - `POST /api/users`: Create a new user.
     - `GET /api/users/{id}`: Retrieve user details by ID.
     - `GET /api/users/exists/{id}`: Check if a user exists by ID.

2. **`UserService` and `UserServiceImpl`**:
   - Service layer that handles business logic for user operations.
   - Methods:
     - `createUser`: Creates a new user after validating email uniqueness and address presence.
     - `getUser`: Retrieves user details by ID.
     - `checkUserExists`: Checks if a user exists by ID.

3. **`User` and `Address` Entities**:
   - JPA entities representing the `users` and `addresses` tables in the database.
   - `User` has a one-to-many relationship with `Address`.

4. **`UserRequestDTO` and `UserResponseDTO`**:
   - Data Transfer Objects (DTOs) used for request and response payloads.
   - `UserRequestDTO` is used for creating a user.
   - `UserResponseDTO` is used for returning user details.

5. **`PasswordEncryptionService`**:
   - Service responsible for encrypting and verifying passwords using `PasswordEncoder`.

6. **`BaseEntity`**:
   - A base class for all entities that includes common fields like `id`, `createdAt`, and `updatedAt`.

7. **Custom Exceptions**:
   - `UserAlreadyExistsException`: Thrown when a user with the same email already exists.
   - `UserDoesNotExistsException`: Thrown when a user is not found.
   - `AddressMustNotBeEmptyException`: Thrown when a user is created without any addresses.

---

## API Endpoints

### Create a User
- **Endpoint**: `POST /api/users`
- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "addresses": [
      {
        "street": "123 Main St",
        "city": "New York",
        "state": "NY",
        "country": "USA",
        "zipCode": "10001"
      }
    ]
  }
