package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.PublishingOtherProductRepository;
import ma.digency.gov.amc.repository.WritingLanguageRepository;
import ma.digency.gov.amc.repository.entity.participant.PublishingOtherProduct;
import ma.digency.gov.amc.repository.entity.participant.WritingLanguage;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingLanguageServiceImpl implements WritingLanguageService {

    private final WritingLanguageRepository writingLanguageRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public WritingLanguage findWritingLanguageByRefWritingLanguage(String refWritingLanguage) {
        return writingLanguageRepository.findWritingLanguageByRefWritingLanguage(refWritingLanguage)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }


    @Override
    public WritingLanguage createOrUpdateWritingLanguage(WritingLanguage writingLanguage) {
        if (null == writingLanguage.getRefWritingLanguage()) {
            var ref = referenceSequenceService.generateRefWritingLanguage();
            writingLanguage.setRefWritingLanguage(ref);
        }
        return writingLanguageRepository.save(writingLanguage);
    }

    @Override
    public void deleteWritingLanguage(String refWritingLanguage) {
        WritingLanguage writingLanguage= findWritingLanguageByRefWritingLanguage(refWritingLanguage);
        writingLanguageRepository.delete(writingLanguage);
    }

    @Override
    public List<WritingLanguage> findAllWritingLanguage() {
        return writingLanguageRepository.findAll();
    }
}
