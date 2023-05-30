package ma.digency.gov.amc.service.siel;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.EditionRepository;
import ma.digency.gov.amc.repository.entity.siel.Edition;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditionServiceImpl implements EditionService {

    private final EditionRepository editionRepository;

    @Override
    public List<Edition> findAllEditions() {
        return editionRepository.findAll();
    }

    @Override
    public Edition saveEdition(Edition edition) {
        return editionRepository.save(edition);
    }

    @Override
    public Edition findEditionByName(String editionName) {
        return editionRepository.findByName(editionName)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EDITION_FOUND));

    }

    @Override
    public void deleteEdition(String refEdition) {
        Edition e = findEditionByRef(refEdition);
        e.setStatus(StatusEnum.DELETED);
        saveEdition(e);
    }

    @Override
    public Edition findOpenEditions(StatusEnum open) {
        return editionRepository.findByStatus(open)
                .orElseThrow(()-> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EDITION_FOUND));
    }

    @Override
    public Edition findEditionByRef(String refEdition) {
        return editionRepository.findByRefEdition(refEdition)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EDITION_FOUND));

    }

    @Override
    public Optional<Edition> findOpenEdition(StatusEnum open) {
        return editionRepository.findByStatus(open);
    }
}

