package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer fertilizer dto.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the fertilizer dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(
      @RequestBody FertilizerCreationDto fertilizerCreationDto) {
    return FertilizerDto.fromEntity(
        fertilizerService.create(fertilizerCreationDto.toEntity())
    );
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  public List<FertilizerDto> getAllFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.findAll();

    return allFertilizers.stream().map(FertilizerDto::fromEntity)
        .toList();
  }


  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<Fertilizer> getFertilizerById(@PathVariable Long id)
      throws FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(fertilizer);
  }
}