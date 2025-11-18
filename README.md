# Library Management System - Project 3

## Project Overview
This is a comprehensive Library Management System built with Spring Boot featuring persistent JSON file storage, complete CRUD operations through a web interface, and proper OOP architecture following MVC patterns.

*Course:* Object-Oriented Programming in Java  
*Project:* Phase 3 - JSON Persistence & Full Web UI  
*Team Members:* Vlad & Rares  
*GitHub:* https://github.com/03vladd/Java.git

---

## Key Features

### ✅ Completed in Project 3
- *JSON File Persistence* - All data saved to JSON files automatically
- *Generic Repository Pattern* - InFileRepo handles all entity types
- *Complete CRUD Web Interface* - Create, Read, Update, Delete for all entities
- *Thymeleaf Templates* - Professional UI with Bootstrap-style styling
- *Entity Relationships* - Book-Author junction table support
- *Lombok Integration* - Reduced boilerplate with annotations
- *Jackson Serialization* - Automatic JSON conversion

### 📊 Supported Entities (9 total)
1. *Library* - Library branches and locations
2. *Member* - Library members and their information
3. *Author* - Book authors with birth dates and nationality
4. *BookDetails* - Books with author relationships
5. *MagazineDetails* - Magazines with publishers
6. *ReadableItems* - Physical copies of books/magazines
7. *Loan* - Book loans to members
8. *Reservation* - Member reservations
9. *BookAuthor* - Junction table for book-author relationships

---

## Technologies Used

- *Java 25*
- *Spring Boot 3.5.7*
- *Maven* - Dependency management
- *Thymeleaf* - Server-side templating
- *Jackson* - JSON processing
- *Lombok* - Code generation
- *JSON Files* - Data persistence (no database)

---

## Project Structure


src/main/java/com/lms/LMS/
├── model/              # Entity classes with Lombok
│   ├── Library.java
│   ├── Member.java
│   ├── Author.java
│   ├── BookDetails.java
│   ├── MagazineDetails.java
│   ├── ReadableItems.java
│   ├── Loan.java
│   ├── Reservation.java
│   └── BookAuthor.java
│
├── repo/               # Repository layer (JSON persistence)
│   ├── AbstractRepo.java          # Interface defining CRUD contract
│   ├── InFileRepo.java            # Generic implementation with JSON I/O
│   ├── LibraryRepo.java
│   ├── MemberRepo.java
│   ├── AuthorRepo.java
│   ├── BookDetailsRepo.java
│   ├── MagazineDetailsRepo.java
│   ├── ReadableItemRepo.java
│   ├── LoanRepo.java
│   ├── ReservationRepo.java
│   └── BookAuthorRepo.java
│
├── service/            # Business logic layer
│   ├── LibraryService.java
│   ├── MemberService.java
│   ├── AuthorService.java
│   ├── BookDetailsService.java
│   ├── MagazineDetailsService.java
│   ├── ReadableItemService.java
│   ├── LoanService.java
│   ├── ReservationService.java
│   └── BookAuthorService.java
│
├── controller/         # Web request handling
│   ├── LibraryController.java
│   ├── MemberController.java
│   ├── AuthorController.java
│   ├── BookDetailsController.java
│   ├── MagazineDetailsController.java
│   ├── ReadableItemsController.java
│   ├── LoanController.java
│   ├── ReservationController.java
│   └── BookAuthorController.java
│
└── LmsApplication.java # Spring Boot entry point

src/main/resources/
├── templates/          # Thymeleaf HTML templates
│   ├── author/
│   ├── bookauthor/
│   ├── bookdetails/
│   ├── library/
│   ├── loan/
│   ├── magazinedetails/
│   ├── member/
│   ├── readableitems/
│   └── reservation/
│
├── data/              # JSON data files
│   ├── libraries.json
│   ├── members.json
│   ├── authors.json
│   ├── books.json
│   ├── magazines.json
│   ├── readable_items.json
│   ├── loans.json
│   ├── reservations.json
│   └── book_authors.json
│
└── application.properties


---

## Architecture & Design Patterns

### 1. Layered Architecture (MVC)
*Clean separation of concerns:*
- *Model Layer* - Entity classes representing domain objects
- *Repository Layer* - Data persistence with generic pattern
- *Service Layer* - Business logic orchestration
- *Controller Layer* - HTTP request/response handling
- *View Layer* - Thymeleaf templates for user interface

### 2. Generic Repository Pattern

AbstractRepo<T> (Interface)
    ↓
InFileRepo<T> (Generic Implementation)
    ↓
Specific Repos (LibraryRepo, MemberRepo, etc.)


*Benefits:*
- Single implementation for all entity types
- Automatic ID field resolution via reflection
- Consistent CRUD operations across all entities
- Easy to switch persistence layer (e.g., database)

### 3. CRUD Operations
Each entity supports:
- *Create* - POST /route → service.save(entity)
- *Read* - GET /route → service.getAll() or service.getById(id)
- *Update* - POST /route/{id} → service.update(id, entity)
- *Delete* - POST /route/{id}/delete → service.delete(id)

### 4. JSON Persistence
- *InFileRepo* reads/writes JSON files using Jackson
- *Jackson Module Registration* - Automatic LocalDate/LocalDateTime handling
- *File-based Storage* - No database required
- *Automatic Serialization* - All entity fields persisted

### 5. SOLID Principles Applied
- *Single Responsibility* - Each class has one purpose
- *Open/Closed* - Code open for extension, closed for modification
- *Liskov Substitution* - BookDetails/MagazineDetails replace Publication
- *Interface Segregation* - Clean, focused interfaces
- *Dependency Inversion* - Services depend on abstractions (AbstractRepo)

---

## Key Implementation Details

### Generic InFileRepo
java
public class InFileRepo<T> implements AbstractRepo<T> {
    // Handles any entity type using reflection
    // Automatically manages ID fields
    // Provides JSON serialization/deserialization
    // Supports CRUD + custom finder methods
}


### Entity Inheritance

Publication (abstract)
├── BookDetails
└── MagazineDetails

Subclasses inherit common fields (id, title, copies) while adding specific properties.

### Lombok Integration
java
@Data              // Auto-generates getters/setters
@NoArgsConstructor // Empty constructor
@AllArgsConstructor // Constructor with all fields
public class Library { ... }


### Service Layer Pattern
Each service:
1. Injects repository via constructor
2. Provides business logic methods
3. Handles entity transformation
4. Returns Optional for safe null handling

---

## How to Run

### Prerequisites
- JDK 25 or higher
- Maven 3.6+
- Git

### Setup & Execution

1. *Clone the repository:*
   bash
   git clone https://github.com/03vladd/Java.git
   cd LibraryManagementSystem/LMS
   

2. *Install dependencies:*
   bash
   mvn clean install
   

3. *Run the application:*
   bash
   mvn spring-boot:run
   
   Or in IntelliJ: Right-click LmsApplication.java → Run

4. *Access the application:*
   - Home: http://localhost:8080/
   - Libraries: http://localhost:8080/libraries
   - Members: http://localhost:8080/members
   - Authors: http://localhost:8080/authors
   - Books: http://localhost:8080/books
   - Magazines: http://localhost:8080/magazines
   - Loans: http://localhost:8080/loans
   - Reservations: http://localhost:8080/reservations
   - Readable Items: http://localhost:8080/ReadableItems
   - Book-Authors: http://localhost:8080/bookauthors

---

## Web Interface Features

### For Each Entity
✅ *List View* - Display all records in a table
✅ *Create Form* - Add new entity
✅ *Edit Form* - Modify existing entity
✅ *Delete Action* - Remove entity with confirmation
✅ *Responsive Design* - Color-coded tables by entity type

### Standard CRUD Flow

List Page (GET /)
    ↓
Add New Button → Create Form (GET /new)
    ↓
Submit Form → Create (POST /)
    ↓
Edit Button → Edit Form (GET /{id}/edit)
    ↓
Submit Form → Update (POST /{id})
    ↓
Delete Button → Delete (POST /{id}/delete)


---

## Data Persistence

### JSON File Storage
- Location: src/main/resources/data/
- Files: libraries.json, members.json, authors.json, etc.
- Format: Array of objects with pretty-printing
- Automatic Backup: Each save overwrites file

### Sample Data Structure
json
[
  {
    "id": "lib-001",
    "name": "Springfield Central Library",
    "address": "100 Library Lane",
    "phoneNumber": "+1-555-1001",
    "email": "springfield@library.local",
    "readableItems": []
  }
]


### Configuration
properties
# src/main/resources/application.properties
spring.application.name=LMS
app.data.directory=src/main/resources/data


---

## Extended Properties (Requirement 5)

### Author Class
- birthDate (LocalDate) - Birth date for age tracking
- nationality (String) - Author's country of origin

### Library Class
- phoneNumber (String) - Contact number
- email (String) - Email address

### Member Class
- phoneNumber (String) - Contact number
- email (String) - Email address

---

## Development Workflow

### Adding New Features

1. *Create/Modify Model* - Add entity with Lombok annotations
2. *Create Repository* - Extend InFileRepo for the entity
3. *Create Service* - Implement business logic
4. *Create Controller* - Add CRUD endpoints
5. *Create Templates* - Design web interface
6. *Add JSON File* - Create sample data file
7. *Test* - Verify CRUD operations work

### Best Practices
- Always use @Data, @NoArgsConstructor, @AllArgsConstructor on models
- Constructor injection for services (no @Autowired)
- Use Optional<T> for safe null handling
- Follow REST conventions: GET (read), POST (create/update), POST (delete)
- Consistent URL patterns: /resource, /resource/{id}/edit, /resource/{id}/delete

---

## Testing the Application

### Manual Testing Checklist
- [ ] Create entity via web form
- [ ] Verify data saved in JSON file
- [ ] Read/list all entities
- [ ] Edit entity and verify changes persisted
- [ ] Delete entity and verify removed from JSON
- [ ] Test with invalid data
- [ ] Verify relationships (Book-Author)

### Data Integrity
All operations automatically:
- Generate UUIDs for new entities
- Persist to JSON files
- Load from JSON on startup
- Maintain referential integrity

---

## Future Enhancements (Phase 4+)

### Database Integration
- Replace JSON with JPA/Hibernate
- Add MySQL/PostgreSQL support
- Implement database migrations

### Advanced Features
- Search and filtering
- Pagination
- Sorting
- Advanced reports
- Member fine calculation
- Overdue notifications

### Security
- Authentication & authorization
- User roles (librarian, member)
- Data validation & sanitization
- CSRF protection

### Testing
- Unit tests (JUnit 5)
- Integration tests
- End-to-end tests
- Performance testing

---

## Common Issues & Solutions

### Issue: JSON file not found
*Solution:* Ensure data/ directory exists in src/main/resources/

### Issue: "Entity must have an 'id' field"
*Solution:* Verify all entities have a String id field with getters/setters

### Issue: NoSuchFieldException on inherited fields
*Solution:* InFileRepo handles parent class fields via reflection

### Issue: Null pointer in lists
*Solution:* Initialize lists with new ArrayList<>() or set default in Lombok

### Issue: Date deserialization error
*Solution:* objectMapper.findAndRegisterModules() registers Jackson date modules

---

## SOLID & Clean Code Principles

✅ *Single Responsibility* - Each class handles one concern
✅ *Open/Closed* - New entities don't modify existing code
✅ *Liskov Substitution* - Subclasses substitute parent classes
✅ *Interface Segregation* - AbstractRepo has focused methods
✅ *Dependency Inversion* - Services depend on interfaces
✅ *DRY* - Generic InFileRepo eliminates duplication
✅ *KISS* - Simple, clear architecture
✅ *Clean Code* - Consistent naming, documentation

---

## Team Contributions

*Vlad:*
- Core architecture design
- Repository layer implementation (AbstractRepo, InFileRepo)
- Service layer implementation
- Controller structure and CRUD endpoints
- JSON persistence setup
- Integration testing

*Rares:*
- Extended model properties
- Web UI/Thymeleaf templates
- Front-end styling and layout
- Data validation and error handling
- Documentation and README

---

## How to Contribute

1. Create a feature branch: git checkout -b feature/your-feature
2. Make your changes
3. Commit: git commit -m "Add feature description"
4. Push: git push origin feature/your-feature
5. Create a Pull Request

---

## Project Submission

- *Submission Method:* GitHub
- *Repository:* https://github.com/03vladd/Java.git
- *Due Date:* Week 8 (Phase 3)
- *Evaluation Criteria:*
  - Code quality and OOP principles
  - Complete CRUD functionality
  - JSON persistence working correctly
  - Web UI fully functional
  - Documentation completeness
  - Team collaboration evidence

---

## Additional Resources

### Spring Boot Documentation
- https://spring.io/projects/spring-boot
- https://docs.spring.io/spring-boot/

### Thymeleaf Documentation
- https://www.thymeleaf.org/

### Jackson Documentation
- https://github.com/FasterXML/jackson

### Lombok Documentation
- https://projectlombok.org/

---

## License

This project is part of an academic course assignment and is provided as-is for educational purposes.

---

## Support & Questions

For questions or issues:
1. Check this README first
2. Review the code structure
3. Check recent commits in Git history
4. Contact team members

---

*Last Updated:* November 18, 2025  
*Version:* 3.0 (JSON Persistence & Full Web UI)
