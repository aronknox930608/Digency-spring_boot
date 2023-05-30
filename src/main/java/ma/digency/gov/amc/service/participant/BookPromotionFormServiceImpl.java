package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BookPromotionFormRepository;
import ma.digency.gov.amc.repository.FieldsOfWritingRepository;
import ma.digency.gov.amc.repository.entity.participant.BookPromotionForm;
import ma.digency.gov.amc.repository.entity.participant.FieldsOfWriting;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookPromotionFormServiceImpl implements BookPromotionFormService {

    private final BookPromotionFormRepository bookPromotionFormRepository;
    private final ReferenceSequenceService referenceSequenceService;


    @Override
    public BookPromotionForm findBookPromotionFormByRefBookPromotionForm(String refBookPromotionForm) {
        return bookPromotionFormRepository.findBookPromotionFormByRefBookPromotionForm(refBookPromotionForm)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public BookPromotionForm createOrUpdateBookPromotionForm(BookPromotionForm bookPromotionForm) {
        if (null == bookPromotionForm.getRefBookPromotionForm()) {
            var ref = referenceSequenceService.generateRefBookPromotionForm();
            bookPromotionForm.setRefBookPromotionForm(ref);
        }
        return bookPromotionFormRepository.save(bookPromotionForm);
    }

    @Override
    public void deleteBookPromotionForm(String refBookPromotionForm) {
        BookPromotionForm bookPromotionForm= findBookPromotionFormByRefBookPromotionForm(refBookPromotionForm);
        bookPromotionFormRepository.delete(bookPromotionForm);
    }

    @Override
    public List<BookPromotionForm> findAllBookPromotionForm() {
        return bookPromotionFormRepository.findAll();
    }
}
