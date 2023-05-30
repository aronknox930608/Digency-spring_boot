package ma.digency.gov.amc.repository.entity.search;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class DistributorPage {

    private int pageNumber=0;

    private int pageSize=10;

    private Sort.Direction sortDirection=Sort.Direction.ASC;

    private String sortBy="businessName";

}
