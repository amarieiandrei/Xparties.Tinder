package com.demo.Xparties.Tinder.Repository;

import com.demo.Xparties.Tinder.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    boolean existsByExternalId(String externalId);
    void deleteByExternalId(String externalId);

    Optional<Event> findByExternalId(String externalId);

    @Query("SELECT e FROM Event e WHERE e.name = ?1")
    Optional<Event> findByEventName(String name);
}
