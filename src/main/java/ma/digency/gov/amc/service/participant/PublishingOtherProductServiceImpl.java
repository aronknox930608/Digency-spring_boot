package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.FacilitiesServicesRepository;
import ma.digency.gov.amc.repository.PublishingOtherProductRepository;
import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import ma.digency.gov.amc.repository.entity.participant.PublishingOtherProduct;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublishingOtherProductServiceImpl implements PublishingOtherProductService {

    private final PublishingOtherProductRepository publishingOtherProductRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public PublishingOtherProduct findPublishingOtherProductByRefPublishingOtherProduct(String refPublishingOtherProduct) {
        return publishingOtherProductRepository.findPublishingOtherProductByRefPublishingOtherProduct(refPublishingOtherProduct)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public PublishingOtherProduct createOrUpdatePublishingOtherProduct(PublishingOtherProduct publishingOtherProduct) {
        if (null == publishingOtherProduct.getRefPublishingOtherProduct()) {
            var ref = referenceSequenceService.generateRefPublishingOtherProduct();
            publishingOtherProduct.setRefPublishingOtherProduct(ref);
        }
        return publishingOtherProductRepository.save(publishingOtherProduct);
    }

    @Override
    public void deletePublishingOtherProduct(String refPublishingOtherProduct) {
        PublishingOtherProduct publishingOtherProduct= findPublishingOtherProductByRefPublishingOtherProduct(refPublishingOtherProduct);
        publishingOtherProductRepository.delete(publishingOtherProduct);
    }

    @Override
    public List<PublishingOtherProduct> findAllPublishingOtherProduct() {
        return publishingOtherProductRepository.findAll();
    }
}
