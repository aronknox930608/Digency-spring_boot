package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.PublisherRepresented;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublisherRepresentedService {

    void deletePublisherRepresented(String refExhibitor, String refPublisherRepresented);

    PublisherRepresented findPublisherRepresented(String refExhibitor, String refPublisherRepresented);

    PublisherRepresented update(PublisherRepresented pub);

    Page<PublisherRepresented> findPublishersRepresentedByRefExhibitor(String refExhibitor, Pageable pageable);
}
