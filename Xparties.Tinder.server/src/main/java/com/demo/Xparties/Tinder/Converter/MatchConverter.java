package com.demo.Xparties.Tinder.Converter;

import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;
import com.demo.Xparties.Tinder.Exception.EventException.EventNotFound;
import com.demo.Xparties.Tinder.Exception.PersonException.PersonNotFound;
import com.demo.Xparties.Tinder.Model.Entity.Event;
import com.demo.Xparties.Tinder.Model.Entity.Match;
import com.demo.Xparties.Tinder.Model.Entity.Person;
import com.demo.Xparties.Tinder.Repository.EventRepository;
import com.demo.Xparties.Tinder.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchConverter {

    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;
    private final EventRepository eventRepository;

    public Match fromRequestDtoToEntity(MatchRequestDto matchRequestDto) {
        Person person = personRepository.findByExternalId(matchRequestDto.getPersonExternalId())
                .orElseThrow(() -> new PersonNotFound("person with external id " + matchRequestDto.getPersonExternalId() + " could not be found."));

        Person targetPerson = personRepository.findByExternalId(matchRequestDto.getTargetPersonExternalId())
                .orElseThrow(() -> new PersonNotFound("target person with external id " + matchRequestDto.getTargetPersonExternalId() + " could not be found."));

        Event event = eventRepository.findByExternalId(matchRequestDto.getEventExternalId())
                .orElseThrow(() -> new EventNotFound("event with external id " + matchRequestDto.getEventExternalId() + " could not be found."));

        return Match.builder()
                .person(person)
                .targetPerson(targetPerson)
                .event(event)
                .smash(matchRequestDto.getSmash())
                .build();
    }

    public MatchResponseDto fromEntityToResponseDto(Match match) {
        return modelMapper.map(match, MatchResponseDto.class);
    }
}