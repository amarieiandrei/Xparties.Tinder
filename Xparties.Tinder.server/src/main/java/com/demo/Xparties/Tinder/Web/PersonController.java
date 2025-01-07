package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Model.Person;
import com.demo.Xparties.Tinder.Service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Person createPerson(@Valid @RequestBody Person person) {
        return personService.createPerson(person);
    }

    @GetMapping(path = "/persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping(path = "/{externalId}")
    public Person getPersonByExternalId(@PathVariable String externalId) {
        return personService.getPersonByExternalId(externalId);
    }

    @PutMapping(path = "/{externalId}")
    public void updatePerson(
            @PathVariable String externalId,
            @Valid @RequestBody Person updatedPerson
    ) {
        personService.updatePerson(externalId, updatedPerson);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping( path = "/{externalId}")
    public void deletePerson(@PathVariable String externalId) {
        personService.deletePerson(externalId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void deleteAllPersons() {
        personService.deleteAllPersons();
    }
}
