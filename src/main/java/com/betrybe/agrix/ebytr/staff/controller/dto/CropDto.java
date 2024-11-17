package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId

) {

  /**
   * From entity crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto fromEntity(Crop crop) {
    Long farmDto = crop.getFarm() != null ? crop.getFarm().getId() : null;

    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        farmDto
    );
  }
}
