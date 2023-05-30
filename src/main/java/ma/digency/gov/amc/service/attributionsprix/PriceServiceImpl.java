package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.PriceRepository;
import ma.digency.gov.amc.repository.entity.Price;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService{

    private final ReferenceSequenceService referenceSequenceService;

    private final PriceRepository priceRepository;

    @Override
    public Price createOrUpdatePrice(Price price) {
        if(null==price.getRefPrice()){
            var ref=referenceSequenceService.generateRefPrice();
            price.setRefPrice(ref);
        }
        return priceRepository.save(price) ;
    }

    @Override
    public Price getPriceByRef(String ref) {
        return priceRepository.findPriceByRefPrice(ref);
    }

    @Override
    public Void deletePrice(Price price) {
        priceRepository.delete(price);
        return null;
    }
}
