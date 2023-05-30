package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.repository.entity.participant.BookPromotionForm;
import ma.digency.gov.amc.repository.entity.participant.SellingPoints;

import java.util.List;


public interface SellingPointsService {

    SellingPoints findSellingPointsByRefSellingPoints(String refSellingPoints);

    SellingPoints createOrUpdateSellingPoints(SellingPoints sellingPoints);

    void deleteSellingPoints(String refSellingPoints);

    List<SellingPoints> findAllSellingPoints();


}
