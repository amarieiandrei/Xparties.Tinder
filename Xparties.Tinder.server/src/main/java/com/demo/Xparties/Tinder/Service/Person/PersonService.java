package com.demo.Xparties.Tinder.Service.Person;

import com.demo.Xparties.Tinder.Converter.PersonConverter;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonRequestDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Exception.PersonException.*;
import com.demo.Xparties.Tinder.Exception.PhotoException.PhotoAlreadyAssignedToAPerson;
import com.demo.Xparties.Tinder.Exception.PhotoException.PhotoAlreadyAssignedToAnEvent;
import com.demo.Xparties.Tinder.Exception.PhotoException.PhotoNotFound;
import com.demo.Xparties.Tinder.Model.Person;
import com.demo.Xparties.Tinder.Model.Photo;
import com.demo.Xparties.Tinder.Repository.PersonRepository;
import com.demo.Xparties.Tinder.Repository.PhotoRepository;
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
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final PhotoRepository photoRepository;

    @Override
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

    @Override
    public Page<PersonResponseDto> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable)
                .map(personConverter::fromEntityToResponseDto);
    }

    @Override
    public PersonResponseDto getPersonByExternalId(String externalId) {
        return personConverter.fromEntityToResponseDto(
                personRepository.findByExternalId(externalId)
                        .orElseThrow(() -> new PersonNotFound("person with external id " + externalId + " could not be found."))
        );
    }

    @Override
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

    @Override
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

    @Override
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    @Override
    public PersonResponseDto addPhotoToPerson(String personExternalId, String photoExternalId) {
        Person person = personRepository.findByExternalId(personExternalId)
                .orElseThrow(() -> new PersonNotFound("person with external id " + personExternalId + " could not be found."));

        Photo photo = photoRepository.findByExternalId(photoExternalId)
                .orElseThrow(() -> new PhotoNotFound("photo with external id " + photoExternalId + " could not be found."));

        if (photo.getEvent() == null) {
            person.getPhotos().add(photo);
            photo.setPerson(person);
            personRepository.save(person);
            photoRepository.save(photo);
        } else {
            // TODO: Refactor the error
            throw new PhotoAlreadyAssignedToAnEvent("photo with external id " + photoExternalId + " already assigned to an event and could not have multiple relationships since cascade all will create confusion.");
        }

        return personConverter.fromEntityToResponseDto(person);
    }
}
