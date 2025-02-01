package com.demo.Xparties.Tinder.Converter;

import com.demo.Xparties.Tinder.Dto.PersonDto.PersonRequestDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Model.Entity.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonConverter {

    private final ModelMapper modelMapper;

    public Person fromRequestDtoToEntity(PersonRequestDto personRequestDto) {
        return modelMapper.map(personRequestDto, Person.class);
    }

    public PersonResponseDto fromEntityToResponseDto(Person person) {
        PersonResponseDto personResponseDto = modelMapper.map(person, PersonResponseDto.class);

       // TODO: Something related to relationships

        return personResponseDto;
    }
}