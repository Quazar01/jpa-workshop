package se.lexicon.jpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    Set<Book> writtenBooks;

    // Constructor
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Helper Methods

    // Add a book to the author.
    public void addBook(Book book){
        if(book == null) throw new IllegalArgumentException("Book was null");
        if(writtenBooks.contains(book)) throw new IllegalArgumentException("Book already exists.");
        writtenBooks.add(book);
        book.getAuthors().add(this);
    }

    // Remove a book from the author.
    public void removeBook(Book book){
        if(book == null) throw new IllegalArgumentException("Book was null");
        if(!writtenBooks.contains(book)) throw new IllegalArgumentException("Book does not exist.");
        writtenBooks.remove(book);
        book.getAuthors().remove(this);
    }


}
