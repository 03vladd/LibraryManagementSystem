package com.lms.LMS;

import com.lms.LMS.model.*;
import com.lms.LMS.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
public class DataInitializer implements CommandLineRunner {

    private final LibraryRepository libraryRepository;
    private final MemberRepository memberRepository;
    private final AuthorRepository authorRepository;
    private final BookDetailsRepository bookDetailsRepository;
    private final MagazineDetailsRepository magazineDetailsRepository;
    private final ReadableItemsRepository readableItemRepository;
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;
    private final BookAuthorRepository bookAuthorRepository;

    public DataInitializer(LibraryRepository libraryRepository, MemberRepository memberRepository,
                           AuthorRepository authorRepository, BookDetailsRepository bookDetailsRepository,
                           MagazineDetailsRepository magazineDetailsRepository, ReadableItemsRepository readableItemRepository,
                           LoanRepository loanRepository, ReservationRepository reservationRepository,
                           BookAuthorRepository bookAuthorRepository) {
        this.libraryRepository = libraryRepository;
        this.memberRepository = memberRepository;
        this.authorRepository = authorRepository;
        this.bookDetailsRepository = bookDetailsRepository;
        this.magazineDetailsRepository = magazineDetailsRepository;
        this.readableItemRepository = readableItemRepository;
        this.loanRepository = loanRepository;
        this.reservationRepository = reservationRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if database is empty
        if (libraryRepository.count() > 0) {
            System.out.println("✓ Database already initialized. Skipping data initialization.");
            return;
        }

        System.out.println("→ Initializing database with sample data...");

        // Create 10 Libraries
        List<Library> libraries = new ArrayList<>();
        String[][] libraryData = {
                {"Metropolitan Central Library", "456 Oak Avenue", "metro@library.org", "555-0100"},
                {"Downtown Public Library", "789 Pine Street", "downtown@library.org", "555-0101"},
                {"Westside Branch Library", "321 Elm Road", "westside@library.org", "555-0102"},
                {"University Research Library", "654 Academic Lane", "research@library.org", "555-0103"},
                {"Children's Learning Center", "123 Story Lane", "children@library.org", "555-0104"},
                {"Eastside Community Library", "987 Maple Drive", "eastside@library.org", "555-0105"},
                {"Riverside Public Library", "555 River Road", "riverside@library.org", "555-0106"},
                {"North County Library", "111 North Avenue", "northcounty@library.org", "555-0107"},
                {"South City Library", "222 South Boulevard", "southcity@library.org", "555-0108"},
                {"Central Hub Library", "333 Hub Street", "centralhub@library.org", "555-0109"}
        };

        for (String[] libData : libraryData) {
            Library lib = new Library(libData[0], libData[1]);
            lib.setEmail(libData[2]);
            lib.setPhoneNumber(libData[3]);
            libraries.add(libraryRepository.save(lib));
        }

        // Create 10 Authors
        List<Author> authors = new ArrayList<>();
        Object[][] authorData = {
                {"George Orwell", LocalDate.of(1903, 6, 25), "British"},
                {"Jane Austen", LocalDate.of(1775, 12, 16), "British"},
                {"Mark Twain", LocalDate.of(1835, 11, 30), "American"},
                {"Ernest Hemingway", LocalDate.of(1899, 7, 21), "American"},
                {"F. Scott Fitzgerald", LocalDate.of(1896, 9, 24), "American"},
                {"Harper Lee", LocalDate.of(1926, 4, 28), "American"},
                {"Stephen King", LocalDate.of(1947, 9, 21), "American"},
                {"J.K. Rowling", LocalDate.of(1965, 7, 31), "British"},
                {"Agatha Christie", LocalDate.of(1890, 1, 15), "British"},
                {"Leo Tolstoy", LocalDate.of(1828, 9, 9), "Russian"}
        };

        for (Object[] authData : authorData) {
            Author author = new Author((String) authData[0]);
            author.setBirthDate((LocalDate) authData[1]);
            author.setNationality((String) authData[2]);
            authors.add(authorRepository.save(author));
        }

        // Create BookDetails with ReadableItems as copies
        List<BookDetails> books = new ArrayList<>();
        List<ReadableItems> readableItems = new ArrayList<>();

        String[][] bookTitles = {
                {"1984", "ISBN-001984"},
                {"Pride and Prejudice", "ISBN-PAP1"},
                {"The Adventures of Huckleberry Finn", "ISBN-HUCK1"},
                {"The Old Man and the Sea", "ISBN-OMS1"},
                {"The Great Gatsby", "ISBN-GG1"},
                {"To Kill a Mockingbird", "ISBN-TKAM1"},
                {"The Shining", "ISBN-SHIN1"},
                {"Harry Potter and the Philosopher's Stone", "ISBN-HP1"},
                {"Murder on the Orient Express", "ISBN-MOOE1"},
                {"War and Peace", "ISBN-WAP1"}
        };

        for (int i = 0; i < bookTitles.length; i++) {
            List<ReadableItems> copies = new ArrayList<>();

            // Create 2-3 copies for each book
            int numCopies = (i % 3) + 2;
            for (int j = 0; j < numCopies; j++) {
                ReadableItems copy = new ReadableItems();
                copy.setTitle(bookTitles[i][0]);
                copy.setBarcode(bookTitles[i][1] + "-COPY" + (j + 1));
                copy.setStatus(ReadableItemStatus.Available);
                copy.setLibrary(libraries.get((i + j) % libraries.size()));
                copy = readableItemRepository.save(copy);
                copies.add(copy);
                readableItems.add(copy);
            }

            BookDetails book = new BookDetails(bookTitles[i][0], copies);
            books.add(bookDetailsRepository.save(book));
        }

        // Create BookAuthors associations
        for (int i = 0; i < books.size(); i++) {
            BookAuthor ba = new BookAuthor();
            ba.setBook(books.get(i));
            ba.setAuthor(authors.get(i));
            bookAuthorRepository.save(ba);
        }

        // Create 10 MagazineDetails with copies
        String[][] magazineData = {
                {"The Economist", "The Economist Group"},
                {"National Geographic", "National Geographic Society"},
                {"Time Magazine", "Time USA"},
                {"Nature", "Springer Nature"},
                {"Scientific American", "Springer Nature"},
                {"National Review", "National Review Inc"},
                {"The Atlantic", "Atlantic Media Company"},
                {"The New Yorker", "Conde Nast Publications"},
                {"Wired", "Conde Nast Publications"},
                {"Smithsonian Magazine", "Smithsonian Institution"}
        };

        for (int i = 0; i < magazineData.length; i++) {
            String[] magData = magazineData[i];
            List<ReadableItems> magCopies = new ArrayList<>();

            // Create 1-2 copies for each magazine
            int numMagCopies = (i % 2) + 1;
            for (int j = 0; j < numMagCopies; j++) {
                ReadableItems magCopy = new ReadableItems();
                magCopy.setTitle(magData[0]);
                magCopy.setBarcode("MAG-" + i + "-COPY" + (j + 1));
                magCopy.setStatus(ReadableItemStatus.Available);
                magCopy.setLibrary(libraries.get((i + j) % libraries.size()));
                magCopy = readableItemRepository.save(magCopy);
                magCopies.add(magCopy);
                readableItems.add(magCopy);
            }

            MagazineDetails magazine = new MagazineDetails(magData[0], magCopies, magData[1]);
            magazineDetailsRepository.save(magazine);
        }

        // Create 10 Members
        List<Member> members = new ArrayList<>();
        String[][] memberData = {
                {"Alice Johnson", "alice.johnson@email.com", "742 Evergreen Terrace", "555-1001"},
                {"Robert Smith", "robert.smith@email.com", "1313 Webfoot Walk", "555-1002"},
                {"Maria Garcia", "maria.garcia@email.com", "3001 Capital Crescent", "555-1003"},
                {"James Wilson", "james.wilson@email.com", "3828 Sedgwick Avenue", "555-1004"},
                {"Sarah Chen", "sarah.chen@email.com", "1600 Pennsylvania Avenue", "555-1005"},
                {"Michael Brown", "michael.brown@email.com", "221B Baker Street", "555-1006"},
                {"Emily Davis", "emily.davis@email.com", "42 Wallaby Way", "555-1007"},
                {"David Martinez", "david.martinez@email.com", "1 Infinite Loop", "555-1008"},
                {"Jessica Anderson", "jessica.anderson@email.com", "Two Towers", "555-1009"},
                {"Christopher Lee", "christopher.lee@email.com", "The Shire", "555-1010"}
        };

        for (String[] memData : memberData) {
            Member member = new Member(memData[0], memData[2], libraries.get(members.size() % libraries.size()));
            member.setEmail(memData[1]);
            member.setPhoneNumber(memData[3]);
            members.add(memberRepository.save(member));
        }

        // Create Loans
        List<Loan> loans = new ArrayList<>();
        LocalDate[] loanDates = {
                LocalDate.now().minusDays(5),
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(20),
                LocalDate.now().minusDays(2),
                LocalDate.now().minusDays(15),
                LocalDate.now(),
                LocalDate.now().minusDays(8),
                LocalDate.now().minusDays(3),
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(12)
        };

        for (int i = 0; i < 10; i++) {
            Loan loan = new Loan();
            loan.setDate(loanDates[i]);
            loan.setMember(members.get(i));
            loan.setStatus(LoanStatus.ACTIVE);
            loans.add(loanRepository.save(loan));
        }

        // Create Reservations
        for (int i = 0; i < 10; i++) {
            Reservation reservation = new Reservation();
            reservation.setReservationDate(LocalDate.now());
            reservation.setStatus(ReservationStatus.Active);
            reservation.setLoan(loans.get(i));
            reservation.setReadableItem(readableItems.get(i));
            reservationRepository.save(reservation);
        }

        System.out.println("✓ Database initialization complete!");
        System.out.println("  - Created 10 libraries");
        System.out.println("  - Created 10 authors");
        System.out.println("  - Created 10 books with authors (25-30 copies total)");
        System.out.println("  - Created 10 magazines (10-20 copies total)");
        System.out.println("  - Created 35-50 readable items (books & magazines combined)");
        System.out.println("  - Created 10 members");
        System.out.println("  - Created 10 loans");
        System.out.println("  - Created 10 reservations");
    }
}