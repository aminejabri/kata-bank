# kata-bank

Kata-bank project: simple bank account management with operations.

## Description

This project implements a bank account with operations such as deposits, withdrawals, and balance calculation.

## Main Features

- Adding bank operations (deposits, withdrawals)
- Calculating monthly operations
- Unit and integration tests
- Using embedded MongoDB for tests

## Technologies

- Java 21+
- Spring Boot
- Lombok
- Mapstruct
- MongoDB (Flapdoodle Embedded)
- JUnit 5
- Maven

## Instructions

## Instructions

1. Clone the repository
2. Build the project:  
   `mvn clean package`
3. Run the packaged application:  
   `java -jar target/kata-bank-<version>.jar`
4. Run tests (optional):  
   `mvn test`

## API Endpoints

| Method | Endpoint                                              | Description                                                | Request Body                           | Response                    |
|--------|-------------------------------------------------------|------------------------------------------------------------|----------------------------------------|-----------------------------|
| GET    | `/api/accounts/{id}`                                  | Retrieve account details by account ID                     | —                                      | Account object              |
| POST   | `/api/accounts/{id}/operations`                       | Add a new operation (DEPOSIT or WITHDRAWAL) to the account | `{ "type": "DEPOSIT", "amount": 100 }` | Created operation object    |
| GET    | `/api/accounts/{id}/`                                 | Retrieve all operations for the account                    | —                                      | List of operation objects   |
| GET    | `/api/accounts/{id}/monthly-statements?month=YYYY-MM` | Retrieve operations filtered by month                      | —                                      | Filtered list of operations |

## Author

Amine Jabri