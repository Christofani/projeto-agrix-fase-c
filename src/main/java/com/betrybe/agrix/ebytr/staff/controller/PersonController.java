package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.controller.dto.PersonCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person person dto.
   *
   * @param personDto the person dto
   * @return the person dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@RequestBody PersonCreationDto personDto) {
    Person savedPerson = personService.create(
        personDto.toEntity()
    );

    return PersonDto.fromEntity(savedPerson);
  }


  /**
   * Gets person by id.
   *
   * @param id the id
   * @return the person by id
   * @throws PersonNotFoundException the person not found exception
   */
  @GetMapping("/{id}")
  public PersonDto findById(@PathVariable("id") long id) throws PersonNotFoundException {
    Person person = personService.getPersonById(id);
    return PersonDto.fromEntity(person);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @GetMapping
  public List<PersonDto> findAll() {
    List<Person> persons = personService.findAll();

    return persons.stream().map(PersonDto::fromEntity)
            .toList();
  }

  /**
   * Delete person person dto.
   *
   * @param id the id
   * @return the person dto
   * @throws PersonNotFoundException the person not found exception
   */
  @DeleteMapping("/{id}")
  public PersonDto deletePerson(@PathVariable Long id) throws PersonNotFoundException {
    return PersonDto.fromEntity(
            personService.deletePersonById(id)
    );
  }
}
