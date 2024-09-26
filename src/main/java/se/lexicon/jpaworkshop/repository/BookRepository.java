package se.lexicon.jpaworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.jpaworkshop.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findByIsbnIgnoreCase(String isbn);
    List<Book> findByTitleContaining(String title);
    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);
}
