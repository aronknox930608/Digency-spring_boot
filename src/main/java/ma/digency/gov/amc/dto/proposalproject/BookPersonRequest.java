package ma.digency.gov.amc.dto.proposalproject;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.Address;

@Getter
@Setter
public class BookPersonRequest {

    private String holder;

    private String title;

    private Integer duration;

    private Double amount;

    private Address address;
}
