package ma.digency.gov.amc.service.publiclibrary;


import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.Equipment;

import java.util.List;


public interface EquipmentService {

    Equipment findEquipmentByRefEquipment(String refEquipment);

    Equipment createOrUpdateEquipment(Equipment equipment);

    void deleteEquipment(String refEquipment);

    List<Equipment> findAllEquipment();


}
