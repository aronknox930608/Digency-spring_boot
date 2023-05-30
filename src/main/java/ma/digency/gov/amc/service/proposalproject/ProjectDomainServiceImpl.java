package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.ProjectDomainRepository;
import ma.digency.gov.amc.repository.ProjectSubDomainRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.ProjectDomain;
import ma.digency.gov.amc.repository.entity.proposalproject.ProjectSubDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectDomainServiceImpl implements ProjectDomainService{

    private final ProjectDomainRepository projectDomainRepository;

    private final ProjectSubDomainRepository projectSubDomainRepository;

    @Override
    public ProjectDomain addOrUpdateProjectDomain(ProjectDomain projectDomain) {
        return projectDomainRepository.save(projectDomain);
    }

    @Override
    public void deleteProjectDomain(String refProjectDomain) {
        projectDomainRepository.delete(findProjectDomain(refProjectDomain));
    }

    @Override
    public ProjectDomain findProjectDomain(String refProjectDomain) {
        return projectDomainRepository.findByRefDomain(refProjectDomain).orElseThrow(
            ()->  new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PROJECT_DOMAIN_NOT_FOUND));
    }

    @Override
    public List<ProjectDomain> findAll() {
        return projectDomainRepository.findAll();
    }

    @Override
    public Page<ProjectDomain> findAllPage(Pageable pageable) {
        return this.projectDomainRepository.findAll(pageable);
    }

    @Override
    public ProjectSubDomain findProjectSubDomain(String refSubDomain) {
        return projectSubDomainRepository.findByRefSubDomain(refSubDomain).orElseThrow(()->
                new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PROJECT_SUB_DOMAIN_NOT_FOUND));
    }
}
