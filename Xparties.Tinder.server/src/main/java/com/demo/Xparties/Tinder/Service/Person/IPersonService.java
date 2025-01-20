package com.demo.Xparties.Tinder.Service.Person;

import com.demo.Xparties.Tinder.Dto.PersonDto.PersonRequestDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonService {

    PersonResponseDto createPerson(PersonRequestDto personRequestDto);
    Page<PersonResponseDto> getAllPersons(Pageable pageable);
    PersonResponseDto getPersonByExternalId(String externalId);
    PersonResponseDto updatePerson(String externalId, PersonRequestDto updatedPersonRequestDto);
    void deletePerson(String externalId);
    void deleteAllPersons();
    PersonResponseDto addPhotoToPerson(String personExternalId, String photoExternalId);
    Page<PersonResponseDto> getAllOppositeGenderPersonsAttendingSameEvent(String personExternalId, String eventExternalId, Pageable pageable);
}
