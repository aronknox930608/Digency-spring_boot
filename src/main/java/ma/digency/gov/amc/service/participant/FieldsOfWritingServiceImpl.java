package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.FieldsOfWritingRepository;
import ma.digency.gov.amc.repository.WritingLanguageRepository;
import ma.digency.gov.amc.repository.entity.participant.FieldsOfWriting;
import ma.digency.gov.amc.repository.entity.participant.WritingLanguage;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldsOfWritingServiceImpl implements FieldsOfWritingService {

    private final FieldsOfWritingRepository fieldsOfWritingRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public FieldsOfWriting findFieldsOfWritingByRefFieldsOfWriting(String refFieldsOfWriting) {
        return fieldsOfWritingRepository.findFieldsOfWritingByRefFieldsOfWriting(refFieldsOfWriting)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }


    @Override
    public FieldsOfWriting createOrUpdateFieldsOfWriting(FieldsOfWriting fieldsOfWriting) {
        if (null == fieldsOfWriting.getRefFieldsOfWriting()) {
            var ref = referenceSequenceService.generateRefFieldsOfWriting();
            fieldsOfWriting.setRefFieldsOfWriting(ref);
        }
        return fieldsOfWritingRepository.save(fieldsOfWriting);
    }

    @Override
    public void deleteFieldsOfWriting(String refFieldsOfWriting) {
        FieldsOfWriting fieldsOfWriting= findFieldsOfWritingByRefFieldsOfWriting(refFieldsOfWriting);
        fieldsOfWritingRepository.delete(fieldsOfWriting);
    }

    @Override
    public List<FieldsOfWriting> findAllFieldsOfWriting() {
        return fieldsOfWritingRepository.findAll();
    }
}
