package se.lexicon.jpaworkshop;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.jpaworkshop.entity.*;
import se.lexicon.jpaworkshop.repository.*;

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
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create a new AppUser
        AppUser appUser = new AppUser("Test", "password");
        // Create a new Details
        Details details = new Details("TestUserName","test@test.se");
        // Save the details
        detailsRepository.save(details);
        // Set the details to the user
        appUser.setDetails(details);
        // Save the user
        appUserRepository.save(appUser);

        // Create a new Author
        Author author = new Author("Test", "Testsson");
        // Create a new Book
        Book book = new Book("Test", 30);
        // Save the book
        bookRepository.save(book);
        // Save the author
        authorRepository.save(author);
        // Add the book to the author
        author.addBook(book);
        // Save the author
        authorRepository.save(author);

        // Create a new BookLoan
        BookLoan bookLoan = new BookLoan(book, appUser);
        // Save the book loan
        bookLoanRepository.save(bookLoan);
        // Add the book loan to the user
        appUser.addLoan(bookLoan);


//        // Find user by username.
//        appUserRepository.findByUsername("Test").ifPresent(System.out::println);
//        // Find user by email.
//        appUserRepository.findByDetails_Id(details.getId()).ifPresent(System.out::println);
//
//
//
//        // Find book by title constains.
//        bookRepository.findByTitleContaining("Test").forEach(System.out::println);
//
//
//
//        // Find book loans by book's id
//        bookLoanRepository.findByBook_Id(book.getId()).forEach(System.out::println);
//        // Find book loans by borrower's id
//        bookLoanRepository.findByBorrower_Id(appUser.getId()).forEach(System.out::println);
//        // Find book loans that have not been returned.
//        bookLoanRepository.findByReturnedFalse().forEach(System.out::println);
//        // Find book loans that are overdue.
//        bookLoanRepository.findByDueDateBeforeAndReturnedFalse(bookLoan.getDueDate()).forEach(System.out::println);
//        // Find book loans between specified dates.
//        bookLoanRepository.findByLoanDateBetween(startDate, endDate).forEach(System.out::println);
//        // Mark a book loan as returned by its loan id.
//        bookLoanRepository.updateBookLoanReturnedToTrue(bookLoan.getId());
//


    }
}
