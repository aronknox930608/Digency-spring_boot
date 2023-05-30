package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.Edition;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditionRepository  extends GenericRepository<Edition, Long>{

    Optional<Edition> findByRefEdition(String refEdition);

    Optional<Edition> findByStatus(StatusEnum status);

    Optional<Edition> findByName(String name);

}
