package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.participant.BookPromotionForm;
import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookPromotionFormRepository extends GenericRepository<BookPromotionForm, Long> {

    Optional<BookPromotionForm> findBookPromotionFormByRefBookPromotionForm(String refBookPromotionForm);

}
