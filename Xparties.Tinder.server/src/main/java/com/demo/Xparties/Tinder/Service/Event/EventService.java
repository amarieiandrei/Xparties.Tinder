package com.demo.Xparties.Tinder.Service.Event;

import com.demo.Xparties.Tinder.Converter.EventConverter;
import com.demo.Xparties.Tinder.Converter.PersonConverter;
import com.demo.Xparties.Tinder.Dto.EventDto.EventRequestDto;
import com.demo.Xparties.Tinder.Dto.EventDto.EventResponseDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Exception.EventException.*;
import com.demo.Xparties.Tinder.Exception.PersonException.PersonNotFound;
import com.demo.Xparties.Tinder.Exception.PhotoException.PhotoAlreadyAssignedToAPerson;
import com.demo.Xparties.Tinder.Exception.PhotoException.PhotoNotFound;
import com.demo.Xparties.Tinder.Model.Event;
import com.demo.Xparties.Tinder.Model.Person;
import com.demo.Xparties.Tinder.Model.Photo;
import com.demo.Xparties.Tinder.Repository.EventRepository;
import com.demo.Xparties.Tinder.Repository.PersonRepository;
import com.demo.Xparties.Tinder.Repository.PhotoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService implements IEventService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final PhotoRepository photoRepository;

    @Override
    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
        try {

            Event event = eventConverter.fromRequestDtoToEntity(eventRequestDto);
            Optional<Event> eventOptional = eventRepository.findByEventName(event.getName());
            if (eventOptional.isPresent()) {
                throw new EventAlreadyExists("event with the same name already exists.");
            }
            event.setExternalId(UUID.randomUUID().toString());
            return eventConverter.fromEntityToResponseDto(eventRepository.save(event));

        } catch (Exception e) {
            throw new EventNotCreated("event could not be created.");
        }
    }

    @Override
    public Page<EventResponseDto> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(eventConverter::fromEntityToResponseDto);
    }

    @Override
    public EventResponseDto getEventByExternalId(String externalId) {
        return eventConverter.fromEntityToResponseDto(
                eventRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new EventNotFound("event with external id " + externalId + " could not be found."))
        );
    }

    @Override
    public EventResponseDto updateEvent(String externalId, EventRequestDto updatedEventRequestDto) {
        try {

            if (eventRepository.existsByExternalId(externalId)) {
                Event event = eventRepository.findByExternalId(externalId).get();
                Event updatedEvent = eventConverter.fromRequestDtoToEntity(updatedEventRequestDto);

                event.setName(updatedEvent.getName());
                event.setDate(updatedEvent.getDate());

                return eventConverter.fromEntityToResponseDto(eventRepository.save(event));
            } else {
                throw new EventNotFound("event with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new EventNotUpdated("event with external id " + externalId + " could not be updated.");
        }
    }

    @Override
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

    @Override
    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    @Override
    public EventResponseDto enrollPersonIntoEvent(String eventExternalId, String personExternalId) {
        Event event = eventRepository.findByExternalId(eventExternalId)
                .orElseThrow(() -> new EventNotFound("event with external id " + eventExternalId + " could not be found."));

        Person person = personRepository.findByExternalId(personExternalId)
                .orElseThrow(() -> new PersonNotFound("person with external id " + personExternalId + " could not be found."));

        if (event.getPersons().contains(person)) {
            throw new EventAlreadyContainsPerson("person with external id " + personExternalId + " could not enroll multiple times in the same event.");
        }

        event.getPersons().add(person);
        person.getEvents().add(event);
        eventRepository.save(event);
        personRepository.save(person);

        return eventConverter.fromEntityToResponseDto(event);
    }

    @Override
    public List<EventResponseDto> getAllEventsWherePersonEnrolled(String personExternalId) {
        Person person = personRepository.findByExternalId(personExternalId)
                .orElseThrow(() -> new PersonNotFound("person with external id " + personExternalId + " could not be found."));

        return person.getEvents().stream().map(eventConverter::fromEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<PersonResponseDto> getAllPersonsEnrolledIntoEvent(String eventExternalId) {
        Event event = eventRepository.findByExternalId(eventExternalId)
                .orElseThrow(() -> new EventNotFound("event with external id " + eventExternalId + " could not be found."));

        return event.getPersons().stream().map(personConverter::fromEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public EventResponseDto addPhotoToEvent(String eventExternalId, String photoExternalId) {
        Event event = eventRepository.findByExternalId(eventExternalId)
                .orElseThrow(() -> new EventNotFound("event with external id " + eventExternalId + " could not be found."));

        Photo photo = photoRepository.findByExternalId(photoExternalId)
                .orElseThrow(() -> new PhotoNotFound("photo with external id " + photoExternalId + " could not be found."));

        if (photo.getPerson() == null) {
            event.setPhoto(photo);
            photo.setEvent(event);
            eventRepository.save(event);
            photoRepository.save(photo);
        } else {
            // TODO: Refactor the error
            throw new PhotoAlreadyAssignedToAPerson("photo with external id " + photoExternalId + " already assigned to a person and could not have multiple relationships since cascade all will create confusion.");
        }

        return eventConverter.fromEntityToResponseDto(event);
    }
}
