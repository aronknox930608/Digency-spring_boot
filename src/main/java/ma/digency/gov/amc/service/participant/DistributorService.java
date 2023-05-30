package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.dto.searchParticipant.DistributorSearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.PrinterSearchCriteria;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.repository.entity.search.DistributorPage;
import ma.digency.gov.amc.repository.entity.search.PrinterPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DistributorService {

    Distributor findDistributorByRefDistributor(String refDistributor);

    Distributor createOrUpdateDistributor(Distributor distributor);

    void deleteDistributor(String refDistributor);

    List<Distributor> findAllDistributors();

    Page<Distributor> findDistributorsPageable(Pageable pageable);

    Page<Distributor> getDistributors(DistributorPage distributorPage, DistributorSearchCriteria distributorSearchCriteria);
}
