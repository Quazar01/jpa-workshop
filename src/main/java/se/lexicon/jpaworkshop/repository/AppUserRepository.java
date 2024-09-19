package se.lexicon.jpaworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.jpaworkshop.entity.AppUser;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
    Optional<List<AppUser>> findBetweenTwoDates(LocalDate startDate, LocalDate endDate);
    Optional<AppUser> findByDetailsId(int detailsId);
    Optional<AppUser> findByEmailIgonreCase(String email);

    //Optional
    Optional<AppUser> findByUsernameAndPassword(String username, String password);

}
