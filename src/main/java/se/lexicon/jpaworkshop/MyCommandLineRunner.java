package se.lexicon.jpaworkshop;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.jpaworkshop.entity.AppUser;
import se.lexicon.jpaworkshop.entity.Book;
import se.lexicon.jpaworkshop.entity.BookLoan;
import se.lexicon.jpaworkshop.entity.Details;
import se.lexicon.jpaworkshop.repository.AppUserRepository;
import se.lexicon.jpaworkshop.repository.BookLoanRepository;
import se.lexicon.jpaworkshop.repository.BookRepository;
import se.lexicon.jpaworkshop.repository.DetailsRepository;

import java.time.LocalDate;

@Component
@Transactional
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    DetailsRepository detailsRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookLoanRepository bookLoanRepository;

    @Override
    public void run(String... args) throws Exception {

        AppUser appUser = new AppUser("Test", "password");
        Details details = new Details("TestUserName","test@test.se");
        detailsRepository.save(details);
        appUser.setDetails(details);
        appUserRepository.save(appUser);

        // Find user by username.
        appUserRepository.findByUsername("Test").ifPresent(System.out::println);
        // Find user by email.
        appUserRepository.findByDetails_Id(details.getId()).ifPresent(System.out::println);


        // Create a new Book
        Book book = new Book("Test Book", 30);
        bookRepository.save(book);

        // Find book by title constains.
        bookRepository.findByTitleContaining("Test").forEach(System.out::println);

        // Create a new BookLoan
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(10);

        BookLoan bookLoan = new BookLoan(book, appUser);
        bookLoanRepository.save(bookLoan);

        // Find book loans by book's id
        bookLoanRepository.findByBook_Id(book.getId()).forEach(System.out::println);
        // Find book loans by borrower's id
        bookLoanRepository.findByBorrower_Id(appUser.getId()).forEach(System.out::println);
        // Find book loans that have not been returned.
        bookLoanRepository.findByReturnedFalse().forEach(System.out::println);
        // Find book loans that are overdue.
        bookLoanRepository.findByDueDateBeforeAndReturnedFalse(bookLoan.getDueDate()).forEach(System.out::println);
        // Find book loans between specified dates.
        bookLoanRepository.findByLoanDateBetween(startDate, endDate).forEach(System.out::println);
        // Mark a book loan as returned by its loan id.
        bookLoanRepository.updateBookLoanReturnedToTrue(bookLoan.getId());

    }
}
