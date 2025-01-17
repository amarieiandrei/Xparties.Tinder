package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Dto.PersonDto.PersonRequestDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Service.Person.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PersonResponseDto createPerson(@Valid @RequestBody PersonRequestDto personRequestDto) {
        return personService.createPerson(personRequestDto);
    }

    @GetMapping(path = "/persons")
    public Page<PersonResponseDto> getAllPersons(Pageable pageable) {
        return personService.getAllPersons(pageable);
    }

    @GetMapping(path = "/{externalId}")
    public PersonResponseDto getPersonByExternalId(@PathVariable String externalId) {
        return personService.getPersonByExternalId(externalId);
    }

    @PutMapping(path = "/{externalId}")
    public PersonResponseDto updatePerson(
            @PathVariable String externalId,
            @Valid @RequestBody PersonRequestDto updatedPersonRequestDto
    ) {
        return personService.updatePerson(externalId, updatedPersonRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{externalId}")
    public void deletePerson(@PathVariable String externalId) {
        personService.deletePerson(externalId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void deleteAllPersons() {
        personService.deleteAllPersons();
    }
}
