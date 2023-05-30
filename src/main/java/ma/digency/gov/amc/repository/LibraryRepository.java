package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends GenericRepository<Library, Long> {

    Optional<Library> findLibraryByRefLibrary(String refLibrary);

}
