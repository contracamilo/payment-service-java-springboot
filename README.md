# U-backend

## Description

`u-backend` is a backend application developed with Spring Boot and SQL. This project manages payments and students, allowing CRUD operations on these entities.

## Project Structure
```sh
.
├── java
│   └── com
│       └── payment_system
│           └── u_backend
│               ├── UBackendApplication.java
│               ├── dtos
│               │   └── NewPaymentDTO.java
│               ├── emuns
│               │   ├── PaymentStatus.java
│               │   └── PaymentType.java
│               ├── entities
│               │   ├── Payment.java
│               │   └── Student.java
│               ├── repositories
│               │   ├── PaymentRepository.java
│               │   └── StudentRepository.java
│               └── services
│                   └── PaymentService.java
└── resources
    ├── application.properties
    ├── static
    └── templates
```
## Dependencies

The project uses the following dependencies:

- Spring Boot Starter Actuator
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- H2 Database
- Lombok
- Spring Boot Starter Test

These dependencies are defined in the [pom.xml](pom.xml) file.

## Configuration

The application configuration is located in the [application.properties](src/main/resources/application.properties) file.


## Decorators Explanation

- `@Builder`: This annotation is from Lombok and it provides a builder pattern for the class. It allows you to create instances of the class using a fluent API.

- `@Entity`: This annotation is from JPA (Java Persistence API) and it specifies that the class is an entity and is mapped to a database table.

- `@Data`: This annotation is from Lombok and it generates all the boilerplate code that is normally associated with simple POJOs (Plain Old Java Objects) such as getters, setters, `toString`, `equals`, and `hashCode` methods.

- `@NoArgsConstructor`: This annotation is from Lombok and it generates a no-argument constructor for the class.

- `@AllArgsConstructor`: This annotation is from Lombok and it generates a constructor with one parameter for each field in the class.

These annotations help to reduce boilerplate code and make the class more readable and maintainable.

## Entities

### Payment

The `Payment` entity represents a payment and has the following attributes:

- `id`: Identifier of the payment.
- `date`: Date of the payment.
- `quantity`: Amount of the payment.
- `typePayment`: Type of payment (cash, check, credit card, transfer, deposit).
- `statusPayment`: Status of the payment (created, in process, approved, rejected).
- `file`: File associated with the payment.
- `student`: Student associated with the payment.

### Student

The `Student` entity represents a student and has the following attributes:

- `id`: Identifier of the student.
- `name`: Name of the student.
- `lastName`: Last name of the student.
- `code`: Unique code of the student.
- `email`: Email of the student.
- `programId`: Identifier of the student's program.
- `photo`: Photo of the student.

## Repositories

### PaymentRepository

The `PaymentRepository` allows CRUD operations on the `Payment` entity and has the following methods:

- `List<Payment> findByStudentCode(String studentCode)`
- `List<Payment> findByStatusPayment(PaymentStatus statusPayment)`
- `List<Payment> findByTypePayment(PaymentType typePayment)`

### StudentRepository

The `StudentRepository` allows CRUD operations on the `Student` entity and has the following methods:

- `Student findByCode(String code)`
- `List<Student> findByProgramId(String programId)`

## Services

### PaymentService

The `PaymentService` manages the business logic related to payments.

## Running Tests

The tests are located in the [UBackendApplicationTests.java](src/test/java/com/payment_system/u_backend/UBackendApplicationTests.java) file.

To run the tests, use the following command:

```sh
./mvnw test
```

## Running the Application
To run the application, use the following command:

```sh
./mvnw spring-boot:run
```

## License
This project is licensed under the Apache License 2.0. See the LICENSE file for more details.




