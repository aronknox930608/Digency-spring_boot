package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.publiclibrary.PublicLibrary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicLibraryRepository extends GenericRepository<PublicLibrary, Long> {

    Optional<PublicLibrary> findPublicLibraryByRefPublicLibrary(String refPublicLibrary);

}
