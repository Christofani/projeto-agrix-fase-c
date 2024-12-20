package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farmId repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Create farmId.
   *
   * @param farm the farmId
   * @return the farmId
   */
  public Farm create(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Find by id farmId.
   *
   * @param id the id
   * @return the farmId
   * @throws FarmNotFoundException the farmId not found exception
   */
  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Update farm.
   *
   * @param farm the farm to be updated
   * @return the updated farm
   * @throws FarmNotFoundException if the farm is not found
   */
  public Farm update(Farm farm) throws FarmNotFoundException {
    Farm existingFarm = farmRepository.findById(farm.getId())
            .orElseThrow(FarmNotFoundException::new);

    // Atualiza os dados da fazenda
    existingFarm.setName(farm.getName());
    existingFarm.setSize(farm.getSize());
    // Adicione outros campos conforme necessário para atualizar

    return farmRepository.save(existingFarm); // Salva a fazenda atualizada
  }

  /**
   * Delete farm.
   *
   * @param id the id of the farm to be deleted
   * @throws FarmNotFoundException if the farm is not found
   */
  public void delete(Long id) throws FarmNotFoundException {
    Farm existingFarm = farmRepository.findById(id)
            .orElseThrow(FarmNotFoundException::new);

    farmRepository.delete(existingFarm); // Exclui a fazenda
  }
}
