package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Dto.EventDto.EventRequestDto;
import com.demo.Xparties.Tinder.Dto.EventDto.EventResponseDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Model.Enums.EventCategory;
import com.demo.Xparties.Tinder.Service.Event.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/event")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {

    private final EventService eventService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EventResponseDto createEvent(@Valid @RequestBody EventRequestDto eventRequestDto) {
        return eventService.createEvent(eventRequestDto);
    }

    @GetMapping(path = "/events")
    public Page<EventResponseDto> getAllEvents(Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }

    @GetMapping(path = "/{externalId}")
    public EventResponseDto getEventByExternalId(@PathVariable String externalId) {
        return eventService.getEventByExternalId(externalId);
    }

    @PutMapping(path = "/{externalId}")
    public EventResponseDto updateEvent(
            @PathVariable String externalId,
            @Valid @RequestBody EventRequestDto updatedEventRequestDto
    ) {
        return eventService.updateEvent(externalId, updatedEventRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{externalId}")
    public void deleteEvent(@PathVariable String externalId) {
        eventService.deleteEvent(externalId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteAllEvents() {
        eventService.deleteAllEvents();
    }

    @PatchMapping(path = "/{eventExternalId}")
    public EventResponseDto enrollPersonIntoEvent(@PathVariable String eventExternalId, @RequestParam String personExternalId) {
        return eventService.enrollPersonIntoEvent(eventExternalId, personExternalId);
    }

    // TODO: Can we have pagepageable of events?
    @GetMapping(path = "/personExternalId/{personExternalId}")
    public List<EventResponseDto> getAllEventsWherePersonEnrolled(@PathVariable String personExternalId) {
        return eventService.getAllEventsWherePersonEnrolled(personExternalId);
    }

    // TODO: Can we have pageable of persons?
    @GetMapping(path = "/eventExternalId/{eventExternalId}")
    List<PersonResponseDto> getAllPersonsEnrolledIntoEvent(@PathVariable String eventExternalId) {
        return eventService.getAllPersonsEnrolledIntoEvent(eventExternalId);
    }

    @PatchMapping(path = "/{eventExternalId}/photo")
    public EventResponseDto addPhotoToEvent(@PathVariable String eventExternalId, @RequestParam String photoExternalId) {
        return eventService.addPhotoToEvent(eventExternalId, photoExternalId);
    }

    @GetMapping(path = "/events/{category}")
    public Page<EventResponseDto> getAllEventsByCategory(@PathVariable EventCategory category, Pageable pageable) {
        return eventService.getAllEventsByCategory(category, pageable);
    }
}
