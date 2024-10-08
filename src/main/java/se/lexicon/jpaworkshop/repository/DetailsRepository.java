package se.lexicon.jpaworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.jpaworkshop.entity.Details;

import java.util.Optional;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Optional<Details> findByEmail(String email);
    Optional<Details> findByNameContains(String name);
    Optional<Details> findByNameIgnoreCase(String name);
}
