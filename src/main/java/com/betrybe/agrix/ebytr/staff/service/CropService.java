package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository the crop repository
   * @param farmService    the farm service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  /**
   * Find by id crop.
   *
   * @param id the id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> getCropsByFarmId(Long farmId) throws FarmNotFoundException {
    Farm farm = farmService.findById(farmId);

    return findAll().stream()
        .filter(crop -> crop.getFarm().equals(farm))
        .collect(Collectors.toList());
  }

  /**
   * Gets crops by search date.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by search date
   */
  public List<Crop> getCropsBySearchDate(LocalDate start, LocalDate end) {
    return cropRepository.findAllByHarvestDateBetween(start, end);
  }

  /**
   * Create crop for farm crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop createCropForFarm(Long farmId, Crop crop) throws FarmNotFoundException {
    Farm farm = farmService.findById(farmId);
    crop.setFarm(farm);

    return cropRepository.save(crop);
  }
}
