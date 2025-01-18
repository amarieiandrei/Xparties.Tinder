package com.demo.Xparties.Tinder.Service.Event;

import com.demo.Xparties.Tinder.Dto.EventDto.EventRequestDto;
import com.demo.Xparties.Tinder.Dto.EventDto.EventResponseDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEventService {

    EventResponseDto createEvent(EventRequestDto eventRequestDto);
    Page<EventResponseDto> getAllEvents(Pageable pageable);
    EventResponseDto getEventByExternalId(String externalId);
    EventResponseDto updateEvent(String externalId, EventRequestDto updatedEventRequestDto);
    void deleteEvent(String externalId);
    void deleteAllEvents();
    EventResponseDto enrollPersonIntoEvent(String eventExternalId, String personExternalId);
    List<EventResponseDto> getAllEventsWherePersonEnrolled(String personExternalId);
    List<PersonResponseDto> getAllPersonsEnrolledIntoEvent(String eventExternalId);
    EventResponseDto addPhotoToEvent(String eventExternalId, String photoExternalId);
}
