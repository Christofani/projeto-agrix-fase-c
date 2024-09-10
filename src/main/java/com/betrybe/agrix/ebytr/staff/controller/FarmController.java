package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farmId service
   * @param cropService the crop service
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Gets farmId by id.
   *
   * @param id the id
   * @return the farmId by id
   * @throws FarmNotFoundException the farmId not found exception
   */
  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmService.findById(id)
    );
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.findAll();
    return allFarms.stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Create farmId dto.
   *
   * @param farmCreationDto the farmId creation dto
   * @return the farmId dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.create(farmCreationDto.toEntity())
    );
  }

  /**
   * Create crop for farm crop dto.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCropForFarm(@PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto
  ) throws FarmNotFoundException {
    Crop crop = cropCreationDto.toEntity();

    Crop savedCrop = cropService.createCropForFarm(farmId, crop);

    return CropDto.fromEntity(savedCrop);
  }

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getCropsByFarmId(@PathVariable Long farmId)
      throws FarmNotFoundException {
    try {
      farmService.findById(farmId);
    } catch (FarmNotFoundException e) {
      // Lança a exceção se a fazenda não for encontrada
      throw new FarmNotFoundException();
    }

    List<Crop> crops = cropService.getCropsByFarmId(farmId);

    return crops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }
}
