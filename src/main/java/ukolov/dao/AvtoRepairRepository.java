package ukolov.dao;

import ukolov.entity.AvtoRepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvtoRepairRepository extends JpaRepository<AvtoRepairEntity, Long> {

    AvtoRepairEntity findByName(String name);
}
