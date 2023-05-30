package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.repository.entity.siel.Publication;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicationService {

    List<Publication> findAllPublications(Exhibitor exhibitor);

    Publication findPublicationByRefPublication(String refExhibitor, String refPublication);

    Publication findByRefPublication(String refPubication);

    void deletePublication(String refExhibitor, String refPublication);

    void deletePublications(List<Publication> publications);

    Publication savePublication(Publication newPablication);

    Publication updateStatus(String refPublication, StatusEnum statusEnum);

    Page<Publication> findPublicationsPageable(String refExhibitor, StatusEnum statusEnum , Pageable pageable);

    Page<Publication> findPublicationsByEdition(String edition, Pageable pageable);

    Page<Publication> findPublicationsByEditionAndExhibitor(String refEdition, Pageable pageable, String refExhibitor);

}
