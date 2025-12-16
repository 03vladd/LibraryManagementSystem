# Library Management System - Project v4

## Project Overview
This is a comprehensive Library Management System built with Spring Boot featuring MySQL database persistence with JPA/Hibernate, complete CRUD operations through a professional web interface, automatic loan status management, and proper OOP architecture following MVC patterns.

*Course:* Object-Oriented Programming in Java  
*Project:* Phase 4 - MySQL Database & Advanced Features  
*GitHub:* https://github.com/03vladd/LibraryManagementSystem

---

## Key Features

### ✅ Completed in Project 4
- *MySQL Database Persistence* - All data persisted with JPA/Hibernate
- *Spring Data JPA* - Repository layer with automatic query generation
- *Complete CRUD Web Interface* - Create, Read, Update, Delete for all entities
- *Thymeleaf Templates* - Professional UI with gradient styling and navigation
- *Entity Relationships* - OneToMany, ManyToOne, ManyToMany associations with proper mapping
- *Lombok Integration* - Reduced boilerplate with @Data, @ToString.Exclude
- *Sort & Filter Functionality* - Dynamic sorting and filtering on all entity lists
- *Automatic Loan Status Management* - ACTIVE → OVERDUE after 14 days
- *Loan Return Functionality* - Manual return with status transition to RETURNED
- *Readable Item Copies* - Books and magazines display proper copy counts
- *Docker Support* - MySQL containerization with docker-compose
- *Professional Navigation* - "← Home" navbar on all pages
- *Data Initialization* - Realistic sample data with 10 entities of each type

### 📊 Supported Entities (9 total)
1. *Library* - 10 library branches with contact info
2. *Member* - 10 members with email/phone and library association
3. *Author* - 10 authors with birth dates and nationalities
4. *BookDetails* - 10 books with 2-3 copies each
5. *MagazineDetails* - 10 magazines with 1-2 copies each
6. *ReadableItems* - 25-50 physical copies linked to publications
7. *Loan* - Loan management with automatic overdue detection
8. *Reservation* - Member reservations for items
9. *BookAuthor* - Junction table for book-author relationships

---

## Technologies Used

- *Java 25*
- *Spring Boot 3.5.7*
- *Maven* - Dependency management
- *MySQL 8.0* - Relational database
- *JPA/Hibernate* - Object-Relational Mapping
- *Spring Data JPA* - Repository abstraction
- *Thymeleaf* - Server-side templating
- *Lombok* - Code generation
- *Docker* - Container orchestration
- *docker-compose* - Multi-container setup

---

## Project Structure

```
src/main/java/com/lms/LMS/
├── model/              # JPA Entity classes with Lombok
│   ├── Library.java
│   ├── Member.java
│   ├── Author.java
│   ├── BookDetails.java
│   ├── MagazineDetails.java
│   ├── ReadableItems.java
│   ├── Loan.java
│   ├── Reservation.java
│   ├── BookAuthor.java
│   ├── Publication.java      # Abstract parent class
│   ├── ReadableItemStatus.java
│   ├── ReservationStatus.java
│   └── LoanStatus.java
│
├── repo/               # Spring Data JPA Repositories
│   ├── LibraryRepository.java
│   ├── MemberRepository.java
│   ├── AuthorRepository.java
│   ├── BookDetailsRepository.java
│   ├── MagazineDetailsRepository.java
│   ├── ReadableItemRepository.java
│   ├── LoanRepository.java
│   ├── ReservationRepository.java
│   └── BookAuthorRepository.java
│
├── service/            # Business logic layer
│   ├── LibraryService.java
│   ├── MemberService.java
│   ├── AuthorService.java
│   ├── BookDetailsService.java
│   ├── MagazineDetailsService.java
│   ├── ReadableItemService.java
│   ├── LoanService.java
│   ├── LoanStatusService.java    # Automatic status management
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
└── LmsApplication.java # Spring Boot entry point with @EnableScheduling

src/main/resources/
├── templates/          # Thymeleaf HTML templates
│   ├── index.html      # Home page with entity links
│   ├── member/
│   │   ├── index.html  # List with sort/filter
│   │   └── form.html   # Create/edit form
│   ├── loan/
│   │   ├── index.html  # List with sort/filter
│   │   ├── form.html   # Create/edit with item selection
│   │   └── details.html # Loan details with return button
│   ├── bookdetails/
│   ├── author/
│   ├── library/
│   ├── magazinedetails/
│   ├── readableitems/
│   ├── reservation/
│   └── bookauthor/
│
└── application.properties  # Database & JPA configuration

docker-compose.yml         # MySQL 8.0 container setup
```

---

## Architecture & Design Patterns

### 1. Layered Architecture (MVC)
*Clean separation of concerns:*
- *Model Layer* - JPA Entity classes with @Entity, @Embeddable annotations
- *Repository Layer* - Spring Data JPA repositories with automatic query generation
- *Service Layer* - Business logic orchestration and data transformation
- *Controller Layer* - HTTP request/response handling
- *View Layer* - Thymeleaf templates with responsive UI

### 2. Spring Data JPA Repository Pattern

```
JpaRepository<T, ID>
    ↓
Custom Repository Interface (extends JpaRepository)
    ↓
Spring provides proxy implementation automatically
```

*Benefits:*
- Zero boilerplate CRUD implementation
- Automatic SQL query generation
- Sort and Pagination support
- Custom finder methods with query methods
- Type-safe queries

### 3. Entity Relationships

**One-to-Many:**
- Library → ReadableItems
- Member → Loans
- Loan → Reservations
- Author → Books (via BookAuthor)

**Many-to-One:**
- ReadableItems → Library
- Loan → Member
- Reservation → Loan

**Many-to-Many:**
- Book ↔ Author (via BookAuthor junction table)

All relationships use proper JPA annotations: @OneToMany, @ManyToOne, @ManyToMany

### 4. CRUD Operations
Each entity supports:
- *Create* - POST /route → service.save(entity)
- *Read* - GET /route or GET /route/{id}
- *Update* - POST /route/{id} → service.save(entity)
- *Delete* - POST /route/{id}/delete → service.delete(id)

### 5. Sort & Filter Functionality

```
Frontend (Thymeleaf)
    ↓ (Query params: ?sortBy=name&sortOrder=asc&filter=value)
Controller
    ↓ (@RequestParam binding)
Service
    ↓ (Sort.by(direction, field))
Repository
    ↓ (Spring Data generates SQL ORDER BY)
Database (MySQL)
    ↓ (Returns sorted results)
View (Display with sort indicators)
```

### 6. Automatic Loan Status Management

**Scheduled Task (runs hourly):**
```
LoanStatusService.updateOverdueLoans()
    → Finds all ACTIVE loans
    → Checks if date > 14 days old
    → Sets status to OVERDUE
    → Saves to database
```

**Manual Return:**
- User clicks "Return Loan" button
- Status changes to RETURNED immediately
- Called before every loan list retrieval

### 7. Circular Reference Prevention

Used `@ToString.Exclude` on bidirectional relationships to prevent StackOverflowError:
```java
@Entity
public class Member {
    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Loan> loans;
}

@Entity
public class Loan {
    @ManyToOne
    private Member member;  // No @ToString.Exclude needed here
}
```

### 8. SOLID Principles Applied
- *Single Responsibility* - Each class has one clear purpose
- *Open/Closed* - New entities don't modify existing code
- *Liskov Substitution* - BookDetails/MagazineDetails replace Publication
- *Interface Segregation* - JpaRepository has focused methods
- *Dependency Inversion* - Controllers depend on Services, Services depend on Repositories

---

## Key Implementation Details

### Database Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_management_system
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

**ddl-auto=update:**
- Creates tables on first run
- Adds new columns if entities change
- Preserves existing data between restarts
- ✅ **Use this for production**

### Entity Inheritance

```
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publication {
    private String id;
    private String title;
    @OneToMany
    private List<ReadableItems> copies;
}

@Entity
public class BookDetails extends Publication {
    @OneToMany
    private List<BookAuthor> bookAuthors;
}

@Entity
public class MagazineDetails extends Publication {
    private String publisher;
}
```

### Lombok Integration

```java
@Entity
@Data                      // Getters, setters, equals, hashCode, toString
@NoArgsConstructor         // Default constructor
@AllArgsConstructor        // Constructor with all fields
@ToString.Exclude          // Prevent circular references
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Member member;
    
    @OneToMany
    @ToString.Exclude      // Break bidirectional loop
    private List<ReadableItems> items;
}
```

### Service Layer Pattern

```java
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    
    // Constructor injection
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    
    public List<Loan> getFilteredAndSortedLoans(String memberName, String status, 
                                                 String sortBy, String sortOrder) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) 
            ? Sort.Direction.DESC 
            : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        return loanRepository.findAll(sort);  // Spring Data handles SQL
    }
}
```

### LoanStatusService (Automatic Updates)

```java
@Service
public class LoanStatusService {
    private static final long LOAN_DURATION_DAYS = 14;
    
    @Scheduled(cron = "0 0 * * * *")  // Runs every hour
    public void updateOverdueLoans() {
        List<Loan> activeLoans = loanRepository.findByStatus(LoanStatus.ACTIVE);
        LocalDate today = LocalDate.now();
        
        for (Loan loan : activeLoans) {
            long daysElapsed = ChronoUnit.DAYS.between(loan.getDate(), today);
            if (daysElapsed > LOAN_DURATION_DAYS) {
                loan.setStatus(LoanStatus.OVERDUE);
                loanRepository.save(loan);
            }
        }
    }
}
```

---

## Docker Setup

### docker-compose.yml
```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    container_name: lms-mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: library_management_system
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
```

### Running with Docker

```bash
# Start MySQL container
docker-compose up -d

# Stop container
docker-compose down

# View logs
docker-compose logs mysql
```

---

## How to Run

### Prerequisites
- JDK 25 or higher
- Maven 3.6+
- Docker & Docker Compose (for MySQL)
- Git

### Local Setup & Execution

1. *Clone the repository:*
   ```bash
   git clone https://github.com/03vladd/LibraryManagementSystem.git
   cd LibraryManagementSystem/LMS
   ```

2. *Start MySQL with Docker:*
   ```bash
   docker-compose up -d
   ```

3. *Install dependencies:*
   ```bash
   mvn clean install
   ```

4. *Run the application:*
   ```bash
   mvn spring-boot:run
   ```
   Or in IntelliJ: Right-click LmsApplication.java → Run

5. *Access the application:*
   - Home: http://localhost:8080/
   - Libraries: http://localhost:8080/libraries
   - Members: http://localhost:8080/members
   - Authors: http://localhost:8080/authors
   - Books: http://localhost:8080/bookdetails
   - Magazines: http://localhost:8080/magazinedetails
   - Loans: http://localhost:8080/loans
   - Reservations: http://localhost:8080/reservations
   - ReadableItems: http://localhost:8080/readableitems
   - Book-Authors: http://localhost:8080/bookauthors

6. *Data Initialization:*
   - On first run, DataInitializer automatically populates database with 10 of each entity
   - Realistic data: real author names, library branches, member profiles
   - Sample loans with varied dates (some OVERDUE)
   - Disable DataInitializer after first run by commenting @Component

---

## Web Interface Features

### For Each Entity
✅ *List View* - Display all records in table with sort/filter
✅ *Sort Functionality* - Click column headers to sort ascending/descending
✅ *Filter Functionality* - Text search and dropdown filters
✅ *Create Form* - Add new entity with validation
✅ *Edit Form* - Modify existing entity
✅ *Delete Action* - Remove entity with confirmation
✅ *Details Page* - View detailed information (Loans only)
✅ *Responsive Design* - Gradient purple styling, professional UI
✅ *Navigation* - "← Home" button on all pages

### Standard CRUD Flow

```
List Page (GET /route)
    ↓
[Create] Button → Create Form (GET /route/new)
    ↓
Submit Form → Create (POST /route)
    ↓
[Edit] Button → Edit Form (GET /route/{id}/edit)
    ↓
Submit Form → Update (POST /route/{id})
    ↓
[Delete] Button → Delete (POST /route/{id}/delete)
    ↓
Back to List
```

### Loan-Specific Features
- *Loan Details* - GET /loans/{id}/details (shows due date, remaining days)
- *Return Loan* - POST /loans/{id}/return (sets status to RETURNED)
- *Automatic Overdue* - ACTIVE loans > 14 days auto-convert to OVERDUE
- *Color-coded Status* - Green (Active), Blue (Returned), Red (Overdue)

---

## Data Persistence

### MySQL Database
- Host: localhost:3306
- Database: library_management_system
- User: root
- Password: root
- Schema auto-created by Hibernate on startup

### Data Initialization

DataInitializer runs once (if @Component is enabled):
```java
@Component
public class DataInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        if (libraryRepository.count() > 0) {
            return;  // Only run if database is empty
        }
        
        // Create 10 libraries, 10 authors, 10 books, 10 members, etc.
        // With realistic data and proper relationships
    }
}
```

**To disable after first run:**
```java
// @Component  // Commented out - database already initialized
public class DataInitializer implements CommandLineRunner {
```

Then restart the app - data persists!

---

## Extended Properties (Requirement 5)

### Author Class
- `birthDate` (LocalDate) - Birth date for historical tracking
- `nationality` (String) - Author's country of origin

### Library Class
- `phoneNumber` (String) - Contact phone number
- `email` (String) - Email address

### Member Class
- `phoneNumber` (String) - Contact phone number
- `email` (String) - Email address

---

## Development Workflow

### Adding New Features

1. *Create/Modify Entity* - Add @Entity with JPA annotations
2. *Create Repository* - Extend JpaRepository
3. *Create Service* - Implement business logic
4. *Create Controller* - Add request mapping methods
5. *Create Templates* - Design Thymeleaf views
6. *Test* - Verify CRUD operations work
7. *Deploy* - Run with docker-compose

### Best Practices
- Always use @Data, @NoArgsConstructor on entities
- Use @ToString.Exclude for bidirectional relationships
- Constructor injection for services (no @Autowired)
- Use Optional<T> for safe null handling
- Follow REST conventions
- Consistent URL patterns: /resource, /resource/{id}/edit, /resource/{id}/delete
- Use @Scheduled for recurring tasks

---

## Advanced Features

### Sort & Filter
- Backend sorting via Spring Data Sort
- Dynamic query building
- URL parameters preserve state (bookmarkable)
- Works with any sortable field

### Automatic Loan Management
- Hourly scheduled task checks for overdue loans
- Manual return button for immediate status change
- Days remaining display with color coding
- ACTIVE → OVERDUE automatic transition

### Relationship Management
- OneToMany relationships with cascade operations
- ManyToMany junction tables (BookAuthor)
- Proper foreign key constraints
- Referential integrity maintained

### Data Validation
- JPA validation annotations
- Error handling in forms
- User-friendly error messages
- Form value persistence on error

---

## Testing the Application

### Manual Testing Checklist
- [ ] Create entity via web form
- [ ] Verify data persisted in MySQL
- [ ] Read/list all entities
- [ ] Edit entity and verify changes
- [ ] Delete entity and verify removal
- [ ] Test sorting on each column
- [ ] Test filtering with various inputs
- [ ] Create loan and verify copy count
- [ ] Check if loan becomes OVERDUE after 14+ days
- [ ] Test manual loan return
- [ ] Verify relationships (Book-Author, Member-Loan)

### Data Integrity
All operations automatically:
- Generate IDs via database sequence
- Persist to MySQL database
- Enforce foreign key constraints
- Validate using JPA annotations
- Maintain referential integrity

---

## Common Issues & Solutions

### Issue: Database connection refused
**Solution:** Ensure MySQL is running: `docker-compose up -d`

### Issue: "Table doesn't exist"
**Solution:** Check ddl-auto setting. Set to `update` or `create` in application.properties

### Issue: StackOverflowError in toString()
**Solution:** Add `@ToString.Exclude` to bidirectional relationship fields

### Issue: Null pointer in list display
**Solution:** Initialize lists with `new ArrayList<>()` in Lombok or entity constructors

### Issue: DataInitializer runs every restart
**Solution:** Comment out `@Component` annotation after first run

### Issue: Thymeleaf "Cannot bind form errors"
**Solution:** Move error display block **inside** the `<form th:object="...">` tag

### Issue: Loan status not updating
**Solution:** Ensure @EnableScheduling is in LmsApplication.java

---

## Performance Considerations

### Eager vs Lazy Loading
- Use `@OneToMany(fetch = FetchType.LAZY)` for large collections
- Use `@ManyToOne(fetch = FetchType.EAGER)` for required relationships
- Prevents N+1 query problems

### Indexing
MySQL automatically indexes:
- Primary keys (@Id)
- Foreign keys (@ManyToOne, @OneToMany)
- Consider adding indexes for frequently filtered fields

### Batch Operations
For bulk updates, use batch processing in service layer

---

## Future Enhancements (Phase 5+)

### Security & Authentication
- User authentication (login/logout)
- Role-based access (Librarian, Member)
- CSRF protection
- Input validation & sanitization

### Advanced Features
- Full-text search across multiple fields
- Pagination for large result sets
- Advanced reporting and analytics
- Member fine calculation
- Overdue notification emails
- Reservation queue management

### Testing
- Unit tests (JUnit 5)
- Integration tests with TestContainers
- End-to-end tests (Selenium)
- Performance testing

### DevOps
- GitHub Actions for CI/CD
- Deployment to cloud (AWS, Azure)
- Kubernetes containerization
- Automated backups

---

## SOLID & Clean Code Principles

✅ *Single Responsibility* - Each class has one clear purpose  
✅ *Open/Closed* - New entities don't modify existing code  
✅ *Liskov Substitution* - Subclasses substitute parent classes  
✅ *Interface Segregation* - JpaRepository is focused  
✅ *Dependency Inversion* - Services depend on interfaces  
✅ *DRY* - No code duplication  
✅ *KISS* - Simple, clear architecture  
✅ *Clean Code* - Consistent naming, documentation  

---

## Technologies & Versions

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 25 | Programming language |
| Spring Boot | 3.5.7 | Web framework |
| Spring Data JPA | 3.5.7 | Database abstraction |
| Hibernate | 6.4.x | ORM framework |
| MySQL | 8.0 | Database |
| Thymeleaf | 3.1.3 | Template engine |
| Lombok | 1.18.x | Code generation |
| Docker | Latest | Containerization |
| Maven | 3.6+ | Build tool |

---

## Project Submission

- *Submission Method:* GitHub
- *Due Date:* Week 12
- *Evaluation Criteria:*
  - Code quality and OOP principles
  - Complete CRUD functionality with MySQL
  - Automatic loan status management
  - Web UI fully functional with sort/filter
  - Sort and filtering working correctly
  - Professional UI/UX design
  - Documentation completeness
  - Proper error handling

---

## Additional Resources

### Spring Boot & Spring Data
- https://spring.io/projects/spring-boot
- https://spring.io/projects/spring-data-jpa
- https://docs.spring.io/spring-boot/

### Thymeleaf
- https://www.thymeleaf.org/

### Hibernate & JPA
- https://hibernate.org/
- https://jakarta.ee/specifications/persistence/

### MySQL
- https://dev.mysql.com/doc/

### Docker
- https://docs.docker.com/
- https://docs.docker.com/compose/

### Lombok
- https://projectlombok.org/

---

## Support & Questions

For questions or issues:
1. Check this README first
2. Review the code structure and comments
3. Check recent commits in Git history
4. Review application logs
5. Verify Docker container is running: `docker-compose ps`

---

*Last Updated:* December 16, 2025  
*Version:* 4.0 (MySQL Database & Advanced Features)
