# Library Management System

## Project Overview
This is a Library Management System built with Spring Boot for managing books, magazines, members, loans, and reservations.

*Course:* Object-Oriented Programming in Java  
*Project:* Phase 2 - Web Interface with Thymeleaf  
*Team Members:* Vlad & Rares

---

## Technologies Used
- *Java 25*
- *Spring Boot 3.5.7*
- *Maven*
- *Thymeleaf* (Template Engine for web pages)
- *Lombok* (Code generation for models)
- *In-Memory Storage* (HashMap collections)

---

## Project Structure

src/main/java/com/lms/LMS/
├── model/          # Entity classes (with Lombok annotations)
├── repo/           # Repository layer (in-memory data storage)
├── service/        # Business logic layer
└── controller/     # REST endpoints and web controllers

src/main/resources/
└── templates/      # Thymeleaf HTML templates
    ├── library/
    │   ├── index.html
    │   └── form.html
    ├── member/
    │   ├── index.html
    │   └── form.html
    └── author/
        ├── index.html
        └── form.html


---

## Architecture Decisions

### 1. Layered Architecture (MVC)
We implemented a clear separation of concerns:
- *Model Layer*: Contains all entity classes matching the UML diagram
- *Repository Layer*: Handles data persistence using in-memory HashMap storage
- *Service Layer*: Contains business logic and orchestrates repository calls
- *Controller Layer*: Handles HTTP requests and returns Thymeleaf views

### 2. Single Responsibility Principle
Each class has one clear responsibility:
- Repositories only manage data storage/retrieval
- Services only contain business logic
- Controllers handle HTTP requests/responses and prepare data for views

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
For Phases 1 & 2, we chose HashMap-based storage because:
- No database setup required
- Fast prototyping and testing
- Easy to understand and implement
- Will be replaced with JPA/database in future phases

### 6. Lombok Integration (Project 2)
We refactored our model classes to use Lombok annotations:
- *@Data*: Generates getters, setters, toString(), equals(), hashCode()
- *@NoArgsConstructor*: Generates no-argument constructor
- Reduced boilerplate code by ~60% per class
- Improved code readability and maintainability

### 7. Thymeleaf Templates (Project 2)
- Organized templates by entity (library/, member/, author/)
- Each entity has two views: index.html (list) and form.html (create)
- Used Thymeleaf expressions for dynamic content
- Implemented basic styling with embedded CSS

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

## Added Properties (Requirement 5 - Project 1)

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

## Web Interface (Project 2)

### Implemented Pages

#### Libraries
- *GET /libraries* - List all libraries with details (ID, name, address, phone, email)
- *GET /libraries/new* - Form to create a new library
- *POST /libraries* - Submit form to create library
- *POST /libraries/{id}/delete* - Delete a library

#### Members
- *GET /members* - List all members with details (ID, name, library ID, address, phone, email)
- *GET /members/new* - Form to create a new member
- *POST /members* - Submit form to create member
- *POST /members/{id}/delete* - Delete a member

#### Authors
- *GET /authors* - List all authors with details (ID, name, birth date, nationality)
- *GET /authors/new* - Form to create a new author
- *POST /authors* - Submit form to create author
- *POST /authors/{id}/delete* - Delete an author

### Features
- ✅ Responsive HTML tables with color-coded headers
- ✅ Create forms with validation (required fields)
- ✅ Delete functionality with confirmation dialogs
- ✅ Redirect after successful operations
- ✅ Empty state messages ("No items found")
- ✅ Cancel buttons to return to list views

---

## How to Run

1. *Clone the repository:*
bash
   git clone https://github.com/03vladd/LibraryManagementSystem.git


2. *Navigate to project directory:*
bash
   cd LibraryManagementSystem/LMS


3. *Run the application:*
bash
   ./mvnw spring-boot:run


   Or in IntelliJ: Run the LmsApplication class

4. *Access the web interface:*
   - Main page: http://localhost:8080/
   - Libraries: http://localhost:8080/libraries
   - Members: http://localhost:8080/members
   - Authors: http://localhost:8080/authors

5. *Test the CRUD operations:*
   - Click "Add New [Entity]" to create entries
   - View all entries in the table
   - Click "Delete" to remove entries (with confirmation)

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
- *DRY (Don't Repeat Yourself)*: No code duplication, enhanced by Lombok
- *Clean Code*: 
  - Classes: PascalCase
  - Variables/Methods: camelCase
  - Date types: LocalDate/LocalTime/LocalDateTime
- *MVC Pattern*: Clear separation between Model, View (Thymeleaf), and Controller

---

## Future Enhancements (Phase 3+)
- Database integration (JPA/Hibernate with MySQL/PostgreSQL)
- Edit/Update functionality for entities
- Input validation and error handling
- Unit and integration tests
- Authentication and authorization
- Advanced search and filtering
- Pagination for large datasets
- RESTful API with JSON responses
- Frontend framework (React/Angular) or enhanced Thymeleaf
- Overdue loan notifications

---

## Team Contributions

### Vlad:
- Model class implementation
- Repository layer
- Service layer
- Controller structure (Project 1 & 2)
- Thymeleaf templates
- Lombok refactoring
- Initial project setup

### Rares:
- Added extra properties to model classes (Author, Library, Member)
- README documentation (Project 1 & 2)
- Testing and verification
- Code review

---

## Project Phases

### ✅ Phase 1 - Basic Structure (Completed)
- Model classes with OOP principles
- In-memory repositories
- Service layer with business logic
- Basic controller structure
- Added new properties to 3 model classes

### ✅ Phase 2 - Web Interface with Thymeleaf (Completed)
- Thymeleaf dependency integration
- Lombok for code simplification
- Web controllers for Libraries, Members, Authors
- HTML templates (list + create forms)
- CRUD operations (Create, Read, Delete)
- Styled web pages with CSS

### 🔜 Phase 3 - Database Integration (Coming Soon)
- JPA/Hibernate setup
- MySQL or PostgreSQL database
- Database migrations
- Full CRUD with Update operations

---

## GitHub Repository
https://github.com/03vladd/LibraryManagementSystem

---

## Submission Information
- *Project 1*: Projektaufgabe 1 - Week 5 ✅
- *Project 2*: Projektaufgabe 2 - Week 6 ✅
- *Team*: Vlad & Rares
