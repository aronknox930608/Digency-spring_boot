package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.publiclibrary.Equipment;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends GenericRepository<Equipment, Long> {

    Optional<Equipment> findEquipmentByRefEquipment(String refEquipment);
}
