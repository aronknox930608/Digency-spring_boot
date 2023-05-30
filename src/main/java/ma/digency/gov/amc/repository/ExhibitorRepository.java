package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExhibitorRepository extends GenericRepository<Exhibitor, Long> {

    Optional<Exhibitor> findExhibitorByRefExhibitor(String refExhibitor);
    //update where publication has pending
    Page<Exhibitor> findExhibitorsByStatusOrderByDateCreationAsc(StatusEnum statusEnum,Pageable pageable);

    @Query("SELECT distinct ex FROM Exhibitor ex " +
            "INNER JOIN Publication p ON p.refExhibitor=ex.refExhibitor " +
            "INNER JOIN EditionPublication edp ON edp.editionPublicationId.refPublication=p.refPublication " +
            "AND edp.editionPublicationId.refEdition=:refEdition "+
            "AND ex.country = :country")
    Page<Exhibitor> findExhibitorByEdition(String refEdition, Pageable pageable,String country);

    @Query("SELECT distinct ex FROM Exhibitor ex " +
            "INNER JOIN Publication p ON p.refExhibitor=ex.refExhibitor " +
            "INNER JOIN EditionPublication edp ON edp.editionPublicationId.refPublication=p.refPublication " +
            "AND edp.editionPublicationId.refEdition=:refEdition ")
    Page<Exhibitor> findExhibitorByEdition(String refEdition, Pageable pageable);


    @Query("SELECT distinct ex FROM Exhibitor ex " +
            "INNER JOIN Publication p ON p.refExhibitor=ex.refExhibitor " +
            "INNER JOIN EditionPublication edp ON edp.editionPublicationId.refPublication=p.refPublication " +
            "AND edp.editionPublicationId.refEdition=:refEdition "+
            "AND ex.country <> :country")
    Page<Exhibitor> findForeignExhibitorByEdition(String refEdition, Pageable pageable,String country);

    @Query("SELECT distinct ex FROM Exhibitor ex " +
            "INNER JOIN Publication p ON p.refExhibitor=ex.refExhibitor " +
            "AND p.status=:status")
    Page<Exhibitor> findExhibitorToBeValidate(Pageable pageable, StatusEnum status);




}
