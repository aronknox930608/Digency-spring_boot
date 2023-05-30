package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.repository.entity.distributor.Distributor;
import ma.digency.gov.amc.repository.entity.participant.PublishingOtherProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PublishingOtherProductService {

    PublishingOtherProduct findPublishingOtherProductByRefPublishingOtherProduct(String refPublishingOtherProduct);

    PublishingOtherProduct createOrUpdatePublishingOtherProduct(PublishingOtherProduct publishingOtherProduct);

    void deletePublishingOtherProduct(String refPublishingOtherProduct);

    List<PublishingOtherProduct> findAllPublishingOtherProduct();


}
