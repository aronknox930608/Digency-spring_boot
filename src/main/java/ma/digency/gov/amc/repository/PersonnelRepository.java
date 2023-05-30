package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;
import ma.digency.gov.amc.repository.entity.shared.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnelRepository extends GenericRepository<Personnel, Long> {

    Optional<Personnel> findPersonnelByRefPersonnel(String refPersonnel);
}
