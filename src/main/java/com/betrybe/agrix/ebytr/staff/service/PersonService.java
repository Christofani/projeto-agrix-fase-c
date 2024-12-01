package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;


  /**
   * Instantiates a new Person service.
   *
   * @param personRepository the person repository
   */
  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }


  /**
   * Create person.
   *
   * @param person the person
   * @return the person
   */
  public Person create(Person person) {
    String hashedPassword = new BCryptPasswordEncoder()
            .encode(person.getPassword());

    person.setPassword(hashedPassword);
    return personRepository.save(person);
  }


  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Person> findAll() {
    return personRepository.findAll();
  }


  /**
   * Gets person by id.
   *
   * @param id the id
   * @return the person by id
   * @throws PersonNotFoundException the person not found exception
   */
  public Person getPersonById(long id) throws PersonNotFoundException {
    return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
  }
  /**
   * Update person.
   *
   * @param person the person with updated details
   * @param id the id of the person to be updated
   * @return the updated person
   * @throws PersonNotFoundException the person not found exception
   */

  public Person update(Person person, long id) throws PersonNotFoundException {
    Optional<Person> personOptional = personRepository.findById(id);

    if (personOptional.isPresent()) {
      Person personToUpdate = personOptional.get();

      // Updating the fields
      personToUpdate.setUsername(person.getUsername());
      personToUpdate.setRole(person.getRole());

      // If a new password is provided, rehash and update it
      if (person.getPassword() != null && !person.getPassword().isEmpty()) {
        personToUpdate.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));
      }

      return personRepository.save(personToUpdate);
    } else {
      throw new PersonNotFoundException();
    }
  }

  /**
   * Delete person by id person.
   *
   * @param id the id
   * @return the person
   * @throws PersonNotFoundException the person not found exception
   */
  public Person deletePersonById(Long id) throws PersonNotFoundException {
    Optional<Person> personOptional = personRepository.findById(id);
    if (personOptional.isPresent()) {
      Person person = personOptional.get();
      personRepository.delete(person);
      return person;
    } else {
      throw new PersonNotFoundException();
    }
  }


  /**
   * Gets person by username.
   *
   * @param username the username
   * @return the person by username
   */
  public Person getPersonByUsername(String username) {
    Optional<Person> person = personRepository.findByUsername(username);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }




  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

}
