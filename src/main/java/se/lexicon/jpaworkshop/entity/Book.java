package se.lexicon.jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String isbn;
    @Setter
    private String title;
    @Setter
    private int maxLoanDays;

    @Setter
    @OneToMany(mappedBy = "book")
    private Set<BookLoan> bookLoans = new HashSet<>();

    // Constructor
    public Book(String title, int maxLoanDays) {
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }
}
