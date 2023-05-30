package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.publiclibrary.ItEquipment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItEquipmentRepository extends GenericRepository<ItEquipment, Long> {

    Optional<ItEquipment> findItEquipmentByRefItEquipment(String refItEquipment);
}
