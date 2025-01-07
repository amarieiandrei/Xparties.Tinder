package com.demo.Xparties.Tinder.Repository;

import com.demo.Xparties.Tinder.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    boolean existsByExternalId(String externalId);
    void deleteByExternalId(String externalId);

    Optional<Person> findByExternalId(String externalId);

    @Query("SELECT p FROM Person p WHERE p.email = ?1")
    Optional<Person> findByPersonEmail(String email);

}
