package com.demo.Xparties.Tinder.Service;

import com.demo.Xparties.Tinder.Exception.EventException.EventNotCreated;
import com.demo.Xparties.Tinder.Exception.EventException.EventNotDeleted;
import com.demo.Xparties.Tinder.Exception.EventException.EventNotFound;
import com.demo.Xparties.Tinder.Exception.EventException.EventNotUpdated;
import com.demo.Xparties.Tinder.Model.Event;
import com.demo.Xparties.Tinder.Repository.EventRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(@Valid Event event) {
        try {

            Optional<Event> eventOptional = eventRepository.findByEventName(event.getName());
            if (eventOptional.isPresent()) {
                throw new IllegalStateException("event with the same name already exists.");
            }
            event.setExternalId(UUID.randomUUID().toString());
            return eventRepository.save(event);

        } catch (Exception e) {
            throw new EventNotCreated("event could not be created.");
        }
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventByExternalId(String externalId) {
        return eventRepository.findByExternalId(externalId)
                .orElseThrow(() -> new EventNotFound("Event with external id " + externalId + " could not be found."));
    }

    public void updateEvent(String externalId, @Valid Event updatedEvent) {
        try {

            if (eventRepository.existsByExternalId(externalId)) {
                Optional<Event> eventOptional = eventRepository.findByExternalId(externalId);

                if (eventOptional.isPresent()) {
                    Event event = eventOptional.get();

                    if (updatedEvent.getName() != null) {
                        event.setName(updatedEvent.getName());
                    }
                    if (updatedEvent.getDate() != null) {
                        event.setDate(updatedEvent.getDate());
                    }
                    if (updatedEvent.getPhoto() != null) {
                        event.setPhoto(updatedEvent.getPhoto());
                    }
                    eventRepository.save(event);
                }
            } else {
                throw new EventNotFound("event with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new EventNotUpdated("event with external id " + externalId + " could not be updated.");
        }
    }

    @Transactional
    public void deleteEvent(String externalId) {
        try {

            if (eventRepository.existsByExternalId(externalId)) {
                eventRepository.deleteByExternalId(externalId);
            } else {
                throw new EventNotFound("event with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new EventNotDeleted("event with external id " + externalId + " could not be deleted.");
        }
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }
}
