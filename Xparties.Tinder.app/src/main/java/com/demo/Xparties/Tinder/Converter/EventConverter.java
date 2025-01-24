package com.demo.Xparties.Tinder.Converter;

import com.demo.Xparties.Tinder.Dto.EventDto.EventRequestDto;
import com.demo.Xparties.Tinder.Dto.EventDto.EventResponseDto;
import com.demo.Xparties.Tinder.Model.Entity.Event;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventConverter {

    private final ModelMapper modelMapper;

    public Event fromRequestDtoToEntity(EventRequestDto eventRequestDto) {
        return modelMapper.map(eventRequestDto, Event.class);
    }

    public EventResponseDto fromEntityToResponseDto(Event event) {
        EventResponseDto eventResponseDto = modelMapper.map(event, EventResponseDto.class);

        // TODO: understanding
//        eventResponseDto.setPersonSet(event.getPersons()
//                .stream().map(person -> modelMapper.map(person, PersonResponseDto.class)).collect(Collectors.toSet())
//        );

        return eventResponseDto;
    }
}
