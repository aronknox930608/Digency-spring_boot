package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.GeneralInformationRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.GeneralInformation;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralInformationServiceImp implements GeneralInformationService{

    private final GeneralInformationRepository GeneralInformationRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public GeneralInformation createNewGeneralInformation(GeneralInformation generalInformation) {
        generalInformation.setRefGeneralInformation(referenceSequenceService.generateRefGeneralInformation());
       return GeneralInformationRepository.save(generalInformation);
    }

    @Override
    public GeneralInformation updateGeneralInformation(GeneralInformation generalInformation) {
        return GeneralInformationRepository.save(generalInformation);
    }



}
