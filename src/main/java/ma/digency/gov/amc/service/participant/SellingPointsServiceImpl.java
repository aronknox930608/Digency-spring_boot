package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.SellingPointsRepository;
import ma.digency.gov.amc.repository.WritingLanguageRepository;
import ma.digency.gov.amc.repository.entity.participant.SellingPoints;
import ma.digency.gov.amc.repository.entity.participant.WritingLanguage;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellingPointsServiceImpl implements SellingPointsService {

    private final SellingPointsRepository sellingPointsRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public SellingPoints findSellingPointsByRefSellingPoints(String refSellingPoints) {
        return sellingPointsRepository.findSellingPointsByRefSellingPoints(refSellingPoints)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public SellingPoints createOrUpdateSellingPoints(SellingPoints sellingPoints) {
        if (null == sellingPoints.getRefSellingPoints()) {
            var ref = referenceSequenceService.generateRefSellingPoints();
            sellingPoints.setRefSellingPoints(ref);
        }
        return sellingPointsRepository.save(sellingPoints);
    }

    @Override
    public void deleteSellingPoints(String refSellingPoints) {
        SellingPoints sellingPoints= findSellingPointsByRefSellingPoints(refSellingPoints);
        sellingPointsRepository.delete(sellingPoints);
    }

    @Override
    public List<SellingPoints> findAllSellingPoints() {
        return sellingPointsRepository.findAll();
    }
}
