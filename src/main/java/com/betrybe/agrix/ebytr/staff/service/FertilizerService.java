package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Fertilizer service.
   *
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Create fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }

  /**
   * Find by id fertilizer.
   *
   * @param id the id
   * @return the fertilizer
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Fertilizer findById(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id)
            .orElseThrow(FertilizerNotFoundException::new);
  }

  /**
   * Update fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Fertilizer update(Fertilizer fertilizer) throws FertilizerNotFoundException {
    Fertilizer fertilizerExisted = fertilizerRepository.findById(fertilizer.getId())
            .orElseThrow(FertilizerNotFoundException::new);

    fertilizerExisted.setName(fertilizer.getName());
    fertilizerExisted.setBrand(fertilizer.getBrand());
    fertilizerExisted.setComposition(fertilizer.getComposition());

    return fertilizerRepository.save(fertilizerExisted);
  }


  /**
   * Delete fertilizer by id.
   *
   * @param id the id
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public void deleteFertilizerById(Long id) throws FertilizerNotFoundException {
    Fertilizer existedFertilizer = fertilizerRepository.findById(id)
            .orElseThrow(FertilizerNotFoundException::new);

    fertilizerRepository.delete(existedFertilizer);
  }

}
