package com.demo.Xparties.Tinder.Service;

import com.demo.Xparties.Tinder.Exception.PersonException.*;
import com.demo.Xparties.Tinder.Model.Person;
import com.demo.Xparties.Tinder.Repository.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(@Valid Person person) {
        try {

            Optional<Person> personOptional = personRepository.findByPersonEmail(person.getEmail());
            if (personOptional.isPresent()) {
                throw new PersonAlreadyExists("person with same email already exists.");
            }
            person.setExternalId(UUID.randomUUID().toString());
            return personRepository.save(person);

        } catch (Exception e) {
            throw new PersonNotCreated("person could not be created.");
        }
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonByExternalId(String externalId) {
        return personRepository.findByExternalId(externalId)
                .orElseThrow(() -> new PersonNotFound("person with external id " + externalId + " could not be found."));
    }

    public void updatePerson(String externalId, @Valid Person updatedPerson) {
        try {

            if (personRepository.existsByExternalId(externalId)) {
                Optional<Person> personOptional = personRepository.findByExternalId(externalId);

                if (personOptional.isPresent()) {
                    Person person = personOptional.get();

                    if (updatedPerson.getEmail() != null) {
                        person.setEmail(updatedPerson.getEmail());
                    }
                    if (updatedPerson.getFirstName() != null) {
                        person.setFirstName(updatedPerson.getFirstName());
                    }
                    if (updatedPerson.getLastName() != null) {
                        person.setLastName(updatedPerson.getLastName());
                    }
                    if (updatedPerson.getAge() != null) {
                        person.setAge(updatedPerson.getAge());
                    }
                    if (updatedPerson.getGender() != null) {
                        person.setGender(updatedPerson.getGender());
                    }
                    personRepository.save(person);
                }
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
