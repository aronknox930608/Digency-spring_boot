package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import ma.digency.gov.amc.repository.entity.participant.SellingPoints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellingPointsRepository extends GenericRepository<SellingPoints, Long> {

    Optional<SellingPoints> findSellingPointsByRefSellingPoints(String refSellingPoints);

}
