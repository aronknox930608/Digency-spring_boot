package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistributorRepository extends GenericRepository<Distributor, Long> {

    Optional<Distributor> findDistributorByRefDistributor(String refDistributor);

}
