package se.lexicon.jpaworkshop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.jpaworkshop.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;


public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {
    // Find book loan by borrower's id
    List<BookLoan> findByBorrower_Id(int borrowerId);
    // Find book loans by book's id
    List<BookLoan> findByBook_Id(int bookId);
    // Find book loans that have not been returned.
    List<BookLoan> findByReturnedFalse();
    // Find book loans that are overdue.
    List<BookLoan> findByDueDateBeforeAndReturnedFalse(LocalDate dueDate);
    // Find book loans between specified dates.
    List<BookLoan> findByLoanDateBetween(LocalDate startDate, LocalDate endDate);
    // Mark a book loan as returned by its loan id.
    @Query("update BookLoan bl set bl.returned = true where bl.id = :loanId")
    void updateBookLoanReturnedToTrue(@Param("loanId") int id);

}
