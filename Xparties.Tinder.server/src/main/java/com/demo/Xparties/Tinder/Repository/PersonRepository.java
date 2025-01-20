package com.demo.Xparties.Tinder.Repository;

import com.demo.Xparties.Tinder.Model.Entity.Person;
import com.demo.Xparties.Tinder.Model.Enums.PersonGender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT COUNT(p) > 0 FROM Person p JOIN p.events e WHERE p.externalId = :personExternalId AND e.externalId = :eventExternalId")
    boolean isPersonEnrolledIntoEvent(String personExternalId, String eventExternalId);

    @Query("SELECT p FROM Person p JOIN p.events e WHERE e.externalId = :eventExternalId AND p.gender = :gender")
    Page<Person> getAllPersonsByGenderAttendingSameEvent(PersonGender gender, String eventExternalId, Pageable pageable);
}
