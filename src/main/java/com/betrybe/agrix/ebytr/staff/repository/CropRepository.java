package com.betrybe.agrix.ebytr.staff.repository;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  /**
   * Find all by harvest date between list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  List<Crop> findAllByHarvestDateBetween(LocalDate start, LocalDate end);
}
