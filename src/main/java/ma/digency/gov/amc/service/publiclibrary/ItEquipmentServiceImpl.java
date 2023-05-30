package ma.digency.gov.amc.service.publiclibrary;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BriefcaseBooksRepository;
import ma.digency.gov.amc.repository.ItEquipmentRepository;
import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.ItEquipment;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItEquipmentServiceImpl implements ItEquipmentService {

    private final ItEquipmentRepository itEquipmentRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public ItEquipment findItEquipmentByRefItEquipment(String refItEquipment) {
        return itEquipmentRepository.findItEquipmentByRefItEquipment(refItEquipment)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public ItEquipment createOrUpdateItEquipment(ItEquipment itEquipment) {
        if (null == itEquipment.getRefItEquipment()) {
               var ref = referenceSequenceService.generateRefItEquipment();
               itEquipment.setRefItEquipment(ref);
        }
        return itEquipmentRepository.save(itEquipment);
    }

    @Override
    public void deleteItEquipment(String refItEquipment) {
        ItEquipment itEquipment = findItEquipmentByRefItEquipment(refItEquipment);
        itEquipmentRepository.delete(itEquipment);
    }

    @Override
    public List<ItEquipment> findAllItEquipment() {
        return itEquipmentRepository.findAll();
    }
}
