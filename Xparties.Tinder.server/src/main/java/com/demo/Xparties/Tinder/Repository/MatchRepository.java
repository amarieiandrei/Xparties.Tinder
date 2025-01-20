package com.demo.Xparties.Tinder.Repository;

import com.demo.Xparties.Tinder.Model.Entity.Event;
import com.demo.Xparties.Tinder.Model.Entity.Match;
import com.demo.Xparties.Tinder.Model.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<Match> findByPersonAndTargetPersonAndEvent(Person person, Person targetPerson, Event event);
}