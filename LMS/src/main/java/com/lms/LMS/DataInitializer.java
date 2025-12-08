package com.lms.LMS;

import com.lms.LMS.model.*;
import com.lms.LMS.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
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
        if (libraryRepository.count() > 0) {
            return; // Data already initialized
        }

        // Create Libraries
        List<Library> libraries = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Library lib = new Library("Library " + i, "Address " + i);
            lib.setEmail("library" + i + "@example.com");
            lib.setPhoneNumber("123456789" + i);
            libraries.add(libraryRepository.save(lib));
        }

        // Create Authors
        List<Author> authors = new ArrayList<>();
        String[] authorNames = {"George Orwell", "Jane Austen", "Mark Twain", "Ernest Hemingway",
                "F. Scott Fitzgerald", "Harper Lee", "Stephen King", "J.K. Rowling",
                "Agatha Christie", "Leo Tolstoy"};
        for (int i = 0; i < 10; i++) {
            Author author = new Author(authorNames[i]);
            author.setBirthDate(LocalDate.of(1950 + i, 1, 1));
            author.setNationality("Country " + (i + 1));
            authors.add(authorRepository.save(author));
        }

        // Create ReadableItems
        List<ReadableItems> readableItems = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ReadableItems item = new ReadableItems();
            item.setTitle("Item " + i);
            item.setBarcode("BARCODE" + i);
            item.setStatus(ReadableItemStatus.Available);
            item.setLibrary(libraries.get((i - 1) % libraries.size()));
            readableItems.add(readableItemRepository.save(item));
        }

        // Create BookDetails
        List<BookDetails> books = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            BookDetails book = new BookDetails("Book " + i, new ArrayList<>());
            books.add(bookDetailsRepository.save(book));
        }

        // Create BookAuthors
        for (int i = 0; i < 10; i++) {
            BookAuthor ba = new BookAuthor();
            ba.setBook(books.get(i));
            ba.setAuthor(authors.get(i));
            bookAuthorRepository.save(ba);
        }

        // Create MagazineDetails
        for (int i = 1; i <= 10; i++) {
            MagazineDetails magazine = new MagazineDetails("Magazine " + i, new ArrayList<>(), "Publisher " + i);
            magazineDetailsRepository.save(magazine);
        }

        // Create Members
        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Member member = new Member("Member " + i, "Member Address " + i, libraries.get((i - 1) % libraries.size()));
            member.setEmail("member" + i + "@example.com");
            member.setPhoneNumber("987654321" + i);
            members.add(memberRepository.save(member));
        }

        // Create Loans
        List<Loan> loans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Loan loan = new Loan();
            loan.setDate(LocalDate.now());
            loan.setMember(members.get(i));
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
    }
}