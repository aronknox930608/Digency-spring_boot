package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import ma.digency.gov.amc.repository.entity.participant.PublishingOtherProduct;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublishingOtherProductRepository extends GenericRepository<PublishingOtherProduct, Long> {

    Optional<PublishingOtherProduct> findPublishingOtherProductByRefPublishingOtherProduct(String refPublishingOtherProduct);

}
