package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.Edition;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import java.util.List;
import java.util.Optional;

public interface EditionService {

    List<Edition> findAllEditions();

    Edition saveEdition(Edition edition);

    Edition findEditionByName(String editionName);

    Edition findEditionByRef(String refEdition);

    void deleteEdition(String refEdition);

    Edition findOpenEditions(StatusEnum open);

    Optional<Edition> findOpenEdition(StatusEnum open);
}
