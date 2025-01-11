package com.demo.Xparties.Tinder.Service;

import com.demo.Xparties.Tinder.Converter.PersonConverter;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonRequestDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Exception.PersonException.*;
import com.demo.Xparties.Tinder.Model.Person;
import com.demo.Xparties.Tinder.Repository.PersonRepository;
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
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public PersonResponseDto createPerson(PersonRequestDto personRequestDto) {
        try {

            Person person = personConverter.fromRequestDtoToEntity(personRequestDto);
            Optional<Person> personOptional = personRepository.findByPersonEmail(person.getEmail());
            if (personOptional.isPresent()) {
                throw new PersonAlreadyExists("person with same email already exists.");
            }
            person.setExternalId(UUID.randomUUID().toString());
            return personConverter.fromEntityToResponseDto(personRepository.save(person));

        } catch (Exception e) {
            throw new PersonNotCreated("person could not be created.");
        }
    }

    public Page<PersonResponseDto> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable)
                .map(personConverter::fromEntityToResponseDto);
    }

    public PersonResponseDto getPersonByExternalId(String externalId) {
        return personConverter.fromEntityToResponseDto(
                personRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new PersonNotFound("person with external id " + externalId + " could not be found."))
        );
    }

    public PersonResponseDto updatePerson(String externalId, PersonRequestDto updatedPersonRequestDto) {
        try {

            if (personRepository.existsByExternalId(externalId)) {
                    Person person = personRepository.findByExternalId(externalId).get();
                    Person updatedPerson = personConverter.fromRequestDtoToEntity(updatedPersonRequestDto);

                    person.setEmail(updatedPerson.getEmail());
                    person.setFirstName(updatedPerson.getFirstName());
                    person.setLastName(updatedPerson.getLastName());
                    person.setAge(updatedPerson.getAge());
                    person.setGender(updatedPerson.getGender());

                    return personConverter.fromEntityToResponseDto(personRepository.save(person));
            } else {
                throw new PersonNotFound("person with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new PersonNotUpdated("person with external id " + externalId + " could not be updated.");
        }
    }

    @Transactional
    public void deletePerson(String externalId) {
        try {

            if (personRepository.existsByExternalId(externalId)) {
                personRepository.deleteByExternalId(externalId);
            } else {
                throw new PersonNotFound("person with external id " + externalId + " could not be found.");
            }

        } catch (Exception e) {
            throw new PersonNotDeleted("person with external id " + externalId + " could not be deleted.");
        }
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
}
