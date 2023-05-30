package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import ma.digency.gov.amc.repository.entity.publiclibrary.Spaces;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpacesRepository extends GenericRepository<Spaces, Long> {

    Optional<Spaces> findSpacesByRefSpaces(String refSpaces);
}
