package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProposalProjectRequest {

    private LocalDate startDate;

    private LocalDate endDate;

    private Double amount;

    private String refSubDomain;

    List<Long> refDocumentType;
}
