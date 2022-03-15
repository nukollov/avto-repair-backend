package ukolov.dao;

import ukolov.entity.TypeServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeServiceRepository extends JpaRepository<TypeServiceEntity, Long> {

    TypeServiceEntity findByName(String name);
}