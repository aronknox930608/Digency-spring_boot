package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.ForeignRepresented;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ForeignRepresentedRepository extends GenericRepository<ForeignRepresented, Long>{


    Optional<ForeignRepresented> findByRefExhibitorAndRefForeignRepresented(String refExhibitor, String refPublisher);

    List<ForeignRepresented> findByRefExhibitor(String refExhibitor);

    Page<ForeignRepresented> findForeignRepresentedByRefExhibitorOrderByDateCreationAsc(String refExhibitor, Pageable page);

}
