package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Model.Event;
import com.demo.Xparties.Tinder.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(path = "/{externalId}")
    public Event getEventByExternalId(@PathVariable String externalId) {
        return eventService.getEventByExternalId(externalId);
    }

    @PutMapping(path = "/{externalId}")
    public void updateEvent(
            @PathVariable String externalId,
            @Valid @RequestBody Event updatedEvent
    ) {
        eventService.updateEvent(externalId, updatedEvent);
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
}
