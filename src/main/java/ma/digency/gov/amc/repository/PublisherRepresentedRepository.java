package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.PublisherRepresented;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepresentedRepository extends GenericRepository<PublisherRepresented, Long> {

    Optional<PublisherRepresented> findByRefExhibitorAndRefPublisherRepresented(String refExhibitor, String refPublisher);

    Page<PublisherRepresented> findPublisherRepresentedsByRefExhibitorOrderByDateCreationAsc(String refExhibitor, Pageable page);

    Page<PublisherRepresented> findPublisherRepresentedsByRefExhibitor(String refExhibitor, Pageable page);
}
