package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.repository.entity.siel.Publication;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends GenericRepository<Publication, Long> {

    Optional<Publication> findPublicationByRefPublicationAndRefExhibitor(String refPublication, String refExhibitor);

    List<Publication> findPublicationByRefExhibitor(Exhibitor exhibitor);


    Optional<Publication> findByRefPublication(String refPublication);

    Page<Publication> findPublicationsByRefExhibitorAndStatusOrderByDateCreationAsc(String refExhibitors, StatusEnum status,Pageable pageable);

    Page<Publication> findPublicationsByRefExhibitorOrderByDateCreationAsc(String refExhibitors,Pageable pageable);

    ////
    @Query( "SELECT p FROM Publication p " +
            "INNER JOIN EditionPublication ep ON p.refPublication=ep.editionPublicationId.refPublication " +
            "AND ep.editionPublicationId.refEdition=:refEdition")
    Page<Publication> findPublicationsByEditions(String refEdition,Pageable page);


    @Query( "SELECT p FROM Publication p " +
            "INNER JOIN EditionPublication ep ON p.refPublication=ep.editionPublicationId.refPublication " +
            "AND ep.editionPublicationId.refEdition=:refEdition AND p.refExhibitor=:refExhibitor")
    Page<Publication> findPublicationsByEditionAndByExhibitor(String refEdition,Pageable page,String refExhibitor);


}
