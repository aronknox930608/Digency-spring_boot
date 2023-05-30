package ma.digency.gov.amc.service.siel;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.PublisherRepresentedRepository;
import ma.digency.gov.amc.repository.entity.siel.PublisherRepresented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherRepresentedServiceImpl implements PublisherRepresentedService {

    private final PublisherRepresentedRepository publisherRepresentedRepository;

    @Override
    public void deletePublisherRepresented(String refExhibitor, String refPublisherRepresented) {
        publisherRepresentedRepository.delete(findPublisherRepresented(refExhibitor, refPublisherRepresented));
    }

    @Override
    public PublisherRepresented findPublisherRepresented(String refExhibitor, String refPublisherRepresented) {
        return publisherRepresentedRepository.findByRefExhibitorAndRefPublisherRepresented(refExhibitor, refPublisherRepresented)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PUBLISHER_REPRESENTED_NOT_FOUND));

    }

    @Override
    public PublisherRepresented update(PublisherRepresented pub) {
        return publisherRepresentedRepository.save(pub);
    }

    @Override
    public Page<PublisherRepresented> findPublishersRepresentedByRefExhibitor(String refExhibitor, Pageable pageable) {
        return publisherRepresentedRepository.findPublisherRepresentedsByRefExhibitorOrderByDateCreationAsc(refExhibitor,pageable);
    }
}
