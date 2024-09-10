package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(
    Long id,
    String name,
    Double size
) {

  /**
   * From entity farmId dto.
   *
   * @param farm the farmId
   * @return the farmId dto
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }
}
