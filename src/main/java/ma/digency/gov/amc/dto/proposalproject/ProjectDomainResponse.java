package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.proposalproject.ProjectSubDomain;
import ma.digency.gov.amc.utils.enumeration.DomainComponent;

import java.util.List;

@Getter
@Setter
public class ProjectDomainResponse {

    private String refDomain;

    private String shortName;

    private String longName;

    private String shortNameAr;

    private String longNameAr;

    DomainComponent component;

    private List<ProjectSubDomainResponse> subDomains;
}
