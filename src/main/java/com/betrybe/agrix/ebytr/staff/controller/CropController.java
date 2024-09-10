package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service.ebytr.staff.
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(
        cropService.findById(id)
    );
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.findAll();
    return allCrops.stream()
        .map(CropDto::fromEntity)
        .collect(Collectors.toList());
  }

  /**
   * Search crops by date range list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */

  @GetMapping("search")
  public List<CropDto> searchCropsByDateRange(
      @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

    return cropService.getCropsBySearchDate(start, end).stream()
        .map(CropDto::fromEntity)
        .collect(Collectors.toList());
  }
}
