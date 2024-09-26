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
    private String isbn;
    @Setter
    private String title;
    @Setter
    private int maxLoanDays;

    @Setter
    // FetchType.EAGER is used to load all the authors of the book when the book is loaded.
    // CascadeType.ALL is used to propagate all operations (including delete) from a parent to a child entity.
    @ManyToMany(mappedBy = "writtenBooks",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Author> authors = new HashSet<>();

    @Setter
    @OneToMany(mappedBy = "book")
    private Set<BookLoan> bookLoans = new HashSet<>();

    // Constructor
    public Book(String title, int maxLoanDays) {
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.isbn = generateIsbn();
    }


    // Helper Methods
    //Generate ISBN using UUID.
    private String generateIsbn() {
        return java.util.UUID.randomUUID().toString();
    }

    // Add an author to the book.
    public void addAuthor(Author author){
        if(author == null) throw new IllegalArgumentException("Author was null");
        if(authors.contains(author)) throw new IllegalArgumentException("Author already exists.");
        authors.add(author);
        author.getWrittenBooks().add(this);
    }
    // Add a book loan to the book.
    public void addBookLoan(BookLoan bookLoan){
        if(bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        if(bookLoans.contains(bookLoan)) throw new IllegalArgumentException("BookLoan already exists.");
        bookLoans.add(bookLoan);
    }

    // Remove an author from the book.
    public void removeAuthor(Author author){
        if(author == null) throw new IllegalArgumentException("Author was null");
        if(!authors.contains(author)) throw new IllegalArgumentException("Author does not exist.");
        authors.remove(author);
        author.getWrittenBooks().remove(this);
    }

    // Remove a book loan from the book.
    public void removeBookLoan(BookLoan bookLoan){
        if(bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        if(!bookLoans.contains(bookLoan)) throw new IllegalArgumentException("BookLoan does not exist.");
        bookLoans.remove(bookLoan);
    }

}
