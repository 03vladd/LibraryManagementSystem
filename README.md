# Library Management System

## Project Overview
This is a Library Management System built with Spring Boot for managing books, magazines, members, loans, and reservations.

*Course:* Object-Oriented Programming in Java 
*Project:* Phase 1 - Basic Structure (In-Memory Storage)  
*Team Members:* Vlad & Rares

---

## Technologies Used
- *Java 25*
- *Spring Boot 3.x*
- *Maven*
- *In-Memory Storage* (HashMap collections)

---

## Project Structure

src/main/java/com/lms/LMS/
├── model/          # Entity classes (UML diagram implementation)
├── repo/           # Repository layer (in-memory data storage)
├── service/        # Business logic layer
└── controller/     # REST endpoints


---

## Architecture Decisions

### 1. Layered Architecture (MVC)
We implemented a clear separation of concerns:
- *Model Layer*: Contains all entity classes matching the UML diagram
- *Repository Layer*: Handles data persistence using in-memory HashMap storage
- *Service Layer*: Contains business logic and orchestrates repository calls
- *Controller Layer*: Handles HTTP requests (currently basic test endpoints)

### 2. Single Responsibility Principle
Each class has one clear responsibility:
- Repositories only manage data storage/retrieval
- Services only contain business logic
- Controllers only handle HTTP requests/responses

### 3. Repository Design
- Each entity has its own dedicated repository
- Used HashMap<String, Entity> for in-memory storage
- Returns Optional<T> for single object queries to handle null cases safely
- Custom finder methods (e.g., findByLibraryId, findByStatus)

### 4. Service Design
- Each service manages one main entity
- Services depend on repositories via constructor injection
- Business logic methods for common operations (e.g., adding items to loans)

### 5. Why In-Memory Storage?
For Phase 1, we chose HashMap-based storage because:
- No database setup required
- Fast prototyping and testing
- Easy to understand and implement
- Will be replaced with JPA/database in future phases

---

## Entity Relationships

- *Library* → ReadableItem (1-to-many)
- *Member* → Loan (1-to-many)
- *Loan* → ReadableItem (1-to-many)
- *Loan* → Reservation (1-to-many)
- *Reservation* → ReadableItem (1-to-1)
- *Publication* (abstract) → BookDetails, MagazineDetails (inheritance)
- *BookDetails* ↔ Author (many-to-many via BookAuthor junction table)

---

## Added Properties (Requirement 5)

We added 1-2 new properties to the following classes:

1. *Author*:
   - LocalDate birthDate - to track when the author was born
   - String nationality - to identify the author's country of origin

2. *Library*:
   - String phoneNumber - contact number for the library
   - String email - email address for the library

3. *Member*:
   - String phoneNumber - contact number for the member
   - String email - email address for the member

---

## How to Run

1. Clone the repository:
bash
   git clone https://github.com/03vladd/LibraryManagementSystem.git


2. Navigate to project directory:
bash
   cd LibraryManagementSystem


3. Run the application:
bash
   ./mvnw spring-boot:run

   Or in IntelliJ: Run the main application class

4. Test endpoints:
   - http://localhost:8080/
   - http://localhost:8080/library
   - http://localhost:8080/member
   - http://localhost:8080/loan
   - http://localhost:8080/reservation
   - http://localhost:8080/readableitem
   - http://localhost:8080/bookdetails
   - http://localhost:8080/magazinedetails
   - http://localhost:8080/author
   - http://localhost:8080/bookauthor

---

## SOLID Principles Applied

- *Single Responsibility*: Each class has one clear purpose
- *Open/Closed*: Code is open for extension, closed for modification
- *Liskov Substitution*: Subclasses (BookDetails, MagazineDetails) can replace Publication
- *Interface Segregation*: Clean, focused interfaces
- *Dependency Inversion*: Services depend on abstractions (repositories)

---

## Additional Principles

- *KISS (Keep It Simple & Straightforward)*: Simple, clear code
- *DRY (Don't Repeat Yourself)*: No code duplication
- *Clean Code*: 
  - Classes: PascalCase
  - Variables/Methods: camelCase
  - Date types: LocalDate/LocalTime/LocalDateTime

---

## Future Enhancements (Phase 2+)
- Database integration (JPA/Hibernate with MySQL/PostgreSQL)
- Full CRUD REST API endpoints with JSON responses
- Input validation and error handling
- Unit and integration tests
- Authentication and authorization
- Frontend interface (React/Angular/Thymeleaf)
- Advanced search and filtering
- Overdue loan notifications

---

## Team Contributions

*Vlad*:
- Model class implementation
- Repository layer
- Service layer
- Controller structure
- Initial project setup

*Rares*:
- Added extra properties to model classes (Author, Library, Member)
- README documentation
- Testing and verification

---

## GitHub Repository
https://github.com/03vladd/LibraryManagementSystem

---

## Submission Information
- *Project*: Projektaufgabe 1
- *Due Date*: Week 5
- *Team*: Vlad & Rares
