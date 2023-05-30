package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.searchParticipant.DistributorSearchCriteria;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.DistributorRepository;
import ma.digency.gov.amc.repository.PrinterRepository;
import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.distributor.DistributorCriteriaRepository;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.repository.entity.search.DistributorPage;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistributorServiceImpl implements DistributorService {
    private final DistributorRepository distributorRepository;
    private final ReferenceSequenceService referenceSequenceService;
    private final DistributorCriteriaRepository distributorCriteriaRepository;


    @Override
    public Distributor findDistributorByRefDistributor(String refDistributor) {
        return distributorRepository.findDistributorByRefDistributor(refDistributor)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }


    @Override
    public Distributor createOrUpdateDistributor(Distributor distributor) {
        if (null == distributor.getRefDistributor()) {
            var ref = referenceSequenceService.generateRefDistributor();
            distributor.setRefDistributor(ref);
        }
        return distributorRepository.save(distributor);
    }
    @Override
    public void deleteDistributor(String refDistributor) {
        Distributor distributor = findDistributorByRefDistributor(refDistributor);
        distributorRepository.delete(distributor);
    }

    @Override
    public List<Distributor> findAllDistributors() {
        return distributorRepository.findAll();
    }

    @Override
    public Page<Distributor> findDistributorsPageable(Pageable pageable) {
        return distributorRepository.findAll(pageable);
    }

    @Override
    public Page<Distributor> getDistributors(DistributorPage distributorPage, DistributorSearchCriteria distributorSearchCriteria) {
        return distributorCriteriaRepository.findAllWithFilters(distributorPage,distributorSearchCriteria);
    }
}
