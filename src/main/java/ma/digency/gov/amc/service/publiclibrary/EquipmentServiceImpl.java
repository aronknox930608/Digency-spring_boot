package ma.digency.gov.amc.service.publiclibrary;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BriefcaseBooksRepository;
import ma.digency.gov.amc.repository.EquipmentRepository;
import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.Equipment;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public Equipment findEquipmentByRefEquipment(String refEquipment) {
        return equipmentRepository.findEquipmentByRefEquipment(refEquipment)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public Equipment createOrUpdateEquipment(Equipment equipment) {
        if (null == equipment.getRefEquipment()) {
              var ref = referenceSequenceService.generateRefEquipment();
              equipment.setRefEquipment(ref);
        }
        return equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(String refEquipment) {
        Equipment equipment = findEquipmentByRefEquipment(refEquipment);
        equipmentRepository.delete(equipment);
    }

    @Override
    public List<Equipment> findAllEquipment() {
        return equipmentRepository.findAll();
    }
}
