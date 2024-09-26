package se.lexicon.jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@ToString (exclude = {"borrower", "book"})
@EqualsAndHashCode (exclude = {"borrower", "book"})

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private LocalDate loanDate;
    @Setter
    private LocalDate dueDate;
    @Setter
    private boolean returned;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "borrower_id")
    private AppUser borrower;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    // Constructor
    public BookLoan(Book book, AppUser borrower){
        this.book = book;
        this.borrower = borrower;
        this.loanDate = LocalDate.now();
        // Assign the due date to the loan date plus the max loan days of the book.
        this.dueDate = loanDate.plusDays(book.getMaxLoanDays());
        this.returned = false;
    }

    // Helper Methods
    // Calculate the field dueDate by using "maxLoanDays" from the book.
    public void calculateDueDate(){
        this.dueDate = this.loanDate.plusDays(book.getMaxLoanDays());
    }

}
