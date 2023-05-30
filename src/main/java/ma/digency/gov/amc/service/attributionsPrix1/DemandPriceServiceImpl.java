package ma.digency.gov.amc.service.attributionsPrix1;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.DemandPriceRepository;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.DemandPrice;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandPriceServiceImpl implements DemandPriceService{

    private final ReferenceSequenceService referenceSequenceService;

    private final DemandPriceRepository demandPriceRepository;


    @Override
    public DemandPrice createOrUpdateDemandPrice(DemandPrice demandPrice) {
        if(null==demandPrice.getRefDemand()){
            var ref=referenceSequenceService.generateRefDemandPrice();
            demandPrice.setRefDemand(ref);
        }
        return demandPriceRepository.save(demandPrice);
    }

    @Override
    public DemandPrice findDemandPriceByRef(String refDemand) {
        return demandPriceRepository.findDemandPriceByRefDemand(refDemand);
    }

    @Override
    public Void deleteDemandPrice(DemandPrice demandPrice) {
        demandPriceRepository.delete(demandPrice);
        return null;
    }

    @Override
    public Page<DemandPrice> findDemandPageableAwardHassan2(Pageable pageable) {
        return demandPriceRepository.findDemandAwardHassan2(pageable);
    }

    @Override
    public Page<DemandPrice> findDemandPageableAwardHonorary(Pageable pageable) {
        return demandPriceRepository.findDemandAwardHonorary(pageable);
    }

    @Override
    public Page<DemandPrice> findDemandPageableAwardTheater(Pageable pageable) {
        return demandPriceRepository.findDemandAwardTheater(pageable);
    }

    @Override
    public Page<DemandPrice> findDemandPageableAwardBook(Pageable pageable) {
        return demandPriceRepository.findDemandAwardBook(pageable);
    }

    @Override
    public List<DemandPrice> findAllDemandAwardHassa2() {
        return demandPriceRepository.findAllDemandAwardHassa2();
    }
}
