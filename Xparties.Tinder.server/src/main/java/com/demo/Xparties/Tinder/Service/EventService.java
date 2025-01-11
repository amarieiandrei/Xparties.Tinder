package com.demo.Xparties.Tinder.Service;

import com.demo.Xparties.Tinder.Converter.EventConverter;
import com.demo.Xparties.Tinder.Dto.EventDto.EventRequestDto;
import com.demo.Xparties.Tinder.Dto.EventDto.EventResponseDto;
import com.demo.Xparties.Tinder.Exception.EventException.*;
import com.demo.Xparties.Tinder.Model.Event;
import com.demo.Xparties.Tinder.Repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;

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

    public Page<EventResponseDto> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(eventConverter::fromEntityToResponseDto);
    }

    public EventResponseDto getEventByExternalId(String externalId) {
        return eventConverter.fromEntityToResponseDto(
                eventRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new EventNotFound("event with external id " + externalId + " could not be found."))
        );
    }

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
