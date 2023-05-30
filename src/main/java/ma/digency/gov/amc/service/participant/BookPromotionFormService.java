package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.repository.entity.participant.BookPromotionForm;
import ma.digency.gov.amc.repository.entity.participant.FieldsOfWriting;

import java.util.List;


public interface BookPromotionFormService {

    BookPromotionForm findBookPromotionFormByRefBookPromotionForm(String refBookPromotionForm);

    BookPromotionForm createOrUpdateBookPromotionForm(BookPromotionForm bookPromotionForm);

    void deleteBookPromotionForm(String refBookPromotionForm);

    List<BookPromotionForm> findAllBookPromotionForm();


}
