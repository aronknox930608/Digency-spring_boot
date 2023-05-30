package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.DomainRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.Domain;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements  DomainService{

    private final DomainRepository domainRepository;

    @Override
    public Domain findDomainByRef(String refDomain) {
        return domainRepository.getDomainByRefDomain(refDomain);
    }

    @Override
    public Domain findDomainByName(String domainName) {
        return domainRepository.getDomainByName(domainName);
    }
}
