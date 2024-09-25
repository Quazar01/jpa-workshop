package se.lexicon.jpaworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.jpaworkshop.entity.Details;

import java.util.Optional;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Optional<Details> findByEmail(String email);
    Optional<Details> findByNameContaining(String name);
    Optional<Details> findByNameIgnoreCase(String name);
//
//    // Optional
//    Optional<Details> findByBirthDate(LocalDate birthDate);
}
