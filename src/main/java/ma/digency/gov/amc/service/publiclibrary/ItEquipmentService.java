package ma.digency.gov.amc.service.publiclibrary;


import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.Equipment;
import ma.digency.gov.amc.repository.entity.publiclibrary.ItEquipment;

import java.util.List;


public interface ItEquipmentService {

    ItEquipment findItEquipmentByRefItEquipment(String refItEquipment);

    ItEquipment createOrUpdateItEquipment(ItEquipment itEquipment);

    void deleteItEquipment(String refItEquipment);

    List<ItEquipment> findAllItEquipment();

}
