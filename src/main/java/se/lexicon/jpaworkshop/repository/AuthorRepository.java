package se.lexicon.jpaworkshop.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.jpaworkshop.entity.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    // Find authors by first name
    List<Author> findByFirstName(String firstName);

    // Find authors by last name
    List<Author> findByLastName(String lastName);

    // Find authors by their first name or last name containing a keyword
    List<Author> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    // Find authors by a book's ID
    List<Author> findByWrittenBooks_Id(int bookId);

    // Update an author's name by their ID
    @Modifying
    @Query("update Author a set a.firstName = :firstName, a.lastName = :lastName where a.id = :id")
    void updateAuthorName(int id, String firstName, String lastName);

    // Delete an author by their ID
    @Modifying
    @Query("delete from Author a where a.id = :id")
    void deleteById(int id);
}
