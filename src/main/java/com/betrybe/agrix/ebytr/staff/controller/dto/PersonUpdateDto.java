package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;

/**
 * The type Person update dto.
 */
public record PersonUpdateDto(
        String username,
        String role,
        String password
) {

  /**
   * To entity person.
   *
   * @param id the id of the person to be updated
   * @return the person entity
   */
  public Person toEntity(Long id) {
    return new Person(id, username, password, role);
  }

  /**
   * From entity person update dto.
   *
   * @param person the person
   * @return the person update dto
   */
  public static PersonUpdateDto fromEntity(Person person) {
    return new PersonUpdateDto(
            person.getUsername(),
            person.getRole(),
            person.getPassword() // Assuming password is also required for updates
    );
  }
}
