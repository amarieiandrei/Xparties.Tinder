package com.demo.Xparties.Tinder.Service.Match;

import com.demo.Xparties.Tinder.Converter.MatchConverter;
import com.demo.Xparties.Tinder.Converter.PersonConverter;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Exception.EventException.EventNotFound;
import com.demo.Xparties.Tinder.Exception.MatchException.MatchAlreadyExists;
import com.demo.Xparties.Tinder.Exception.MatchException.MatchCouldNotContainsSamePersons;
import com.demo.Xparties.Tinder.Model.Entity.Event;
import com.demo.Xparties.Tinder.Model.Entity.Match;
import com.demo.Xparties.Tinder.Model.Entity.Person;
import com.demo.Xparties.Tinder.Repository.EventRepository;
import com.demo.Xparties.Tinder.Repository.MatchRepository;
import com.demo.Xparties.Tinder.Service.Notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchService implements IMatchService {

    private final MatchRepository matchRepository;
    private final MatchConverter matchConverter;
    private final NotificationService notificationService;
    private final EventRepository eventRepository;
    private final PersonConverter personConverter;

    @Override
    public MatchResponseDto createMatch(MatchRequestDto matchRequestDto) {
        Match match = matchConverter.fromRequestDtoToEntity(matchRequestDto);

        Optional<Match> existingMatch = matchRepository.findByPersonAndTargetPersonAndEvent(match.getPerson(), match.getTargetPerson(), match.getEvent());

        if (existingMatch.isPresent()) {
            throw new MatchAlreadyExists("person already smash/pass target person enrolled in this event");
        }

        if ( match.getPerson().getId() == match.getTargetPerson().getId() ) {
            throw new MatchCouldNotContainsSamePersons("person is the same like target person");
        }

        boolean personDecision = match.getSmash();
        boolean targetPersonDecision = checkMutualSmash(match.getPerson(), match.getTargetPerson(), match.getEvent());

        if (personDecision && targetPersonDecision) {
            // Logic to notify or connect both people can go here
            notificationService.notifyMatch(match.getPerson(), match.getTargetPerson());

            match.setIsMatch(true);
            setMutualSmash(match.getPerson(), match.getTargetPerson(), match.getEvent());
        } else {
            match.setIsMatch(false);
        }

        return matchConverter.fromEntityToResponseDto(matchRepository.save(match));
    }

    private boolean checkMutualSmash(Person person, Person targetPerson, Event event) {
        Optional<Match> targetDecision = matchRepository
                .findByPersonAndTargetPersonAndEvent(targetPerson, person, event);

        return targetDecision.isPresent() && targetDecision.get().getSmash();
    }

    private void setMutualSmash(Person person, Person targetPerson, Event event) {
        Match match = matchRepository.findByPersonAndTargetPersonAndEvent(targetPerson, person, event).get();
        match.setIsMatch(true);
        matchRepository.save(match);
    }

    @Override
    public Page<PersonResponseDto> getAllMatchedPersonsByEvent(String eventExternalId, Pageable pageable) {
        Event event = eventRepository.findByExternalId(eventExternalId)
                .orElseThrow(() -> new EventNotFound("event with external id " + eventExternalId + " could not be found."));
        return matchRepository.findMatchedPersonsByEvent(event.getId(), pageable)
                .map(personConverter::fromEntityToResponseDto);
    }
}