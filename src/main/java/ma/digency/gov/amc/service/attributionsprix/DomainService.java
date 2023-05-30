package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.Domain;


public interface DomainService {

    Domain findDomainByRef(String refDomain);

    Domain findDomainByName(String domainName);
}
