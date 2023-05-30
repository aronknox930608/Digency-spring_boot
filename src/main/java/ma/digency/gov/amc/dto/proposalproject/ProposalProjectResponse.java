package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.dto.shared.NomenclatureResponse;
import ma.digency.gov.amc.dto.shared.SubFamilyResponse;
import ma.digency.gov.amc.repository.entity.proposalproject.ProposalProjectDocument;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProposalProjectResponse {

    private String refProject;

    private LocalDate startDate;

    private LocalDate endDate;

    private StatusEnum status;

    private ProjectSubDomainResponse subDomain;

    private Double amount;

    private String domain;

    private List<ProposalProjectDocument> documents;

}
