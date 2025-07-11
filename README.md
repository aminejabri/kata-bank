# kata-bank

Kata-bank project: simple bank account management with operations.

This project allows you to populate the database, then retrieve the account IDs (from the populate
endpoint response or the account list endpoint), add operations (via the POST operation endpoint),
and finally retrieve the monthly statements of a given account.

## Description

This project implements a bank account with operations such as deposits, withdrawals, and balance
calculation.

## Design Decisions

For simplicity, I embedded operations directly in the
`Account` document to focus on core logic during the kata.

In a real-world scenario, I would separate
`Account` and
`Operation` into distinct entities:

- In a microservices architecture, each would be handled by its own service, with consistency
  managed through reliable communication patterns such as the outbox pattern or event-driven
  mechanisms. Additionally, security measures and retry mechanisms would be implemented to ensure
  robustness and data integrity.

- In a monolith, I’d use a relational database with proper normalization and ACID transactions to
  guarantee data consistency and integrity.

This design choice here was intentional to keep the kata lightweight and readable.

## Main Features

- Adding bank operations (deposits, withdrawals)
- Calculating monthly operations
- Includes one unit test and one integration test as examples
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
5. Run IT (optional):  
   `mvn verify`
6. Use the provided Postman collection to explore the API:  
   The file
   `kata-bank.postman_collection.json` is available in the
   `postman/` folder.

## API Endpoints

| Method | Endpoint                                | Description                                                | Request Body                           | Response                    |
|--------|-----------------------------------------|------------------------------------------------------------|----------------------------------------|-----------------------------|
| GET    | `/api/accounts`                         | Retrieve accounts                                          | —                                      | List of Accounts            |
| GET    | `/api/accounts/{id}`                    | Retrieve account details by account ID                     | —                                      | Account object              |
| POST   | `/api/accounts/{id}/operations`         | Add a new operation (DEPOSIT or WITHDRAWAL) to the account | `{ "type": "DEPOSIT", "amount": 100 }` | —                           |
| GET    | `/api/accounts/{id}/monthly-statements` | Retrieve operations from last month                        | —                                      | Filtered list of operations |

## Test Data Population Endpoint

*POST*
`/api/test/accounts/populate`

This endpoint populates the database with random bank accounts for testing purposes only.  
It resets existing data and should be disabled in production environments.

## Author

Amine Jabri