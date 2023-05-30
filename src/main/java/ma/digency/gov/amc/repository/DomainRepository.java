package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.attributionsprix.Domain;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends GenericRepository<Domain,Long>{

    Domain getDomainByRefDomain(String refDomain);

    Domain getDomainByName(String domainName);

}

