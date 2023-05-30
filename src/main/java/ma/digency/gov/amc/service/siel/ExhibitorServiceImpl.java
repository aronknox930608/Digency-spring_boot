package ma.digency.gov.amc.service.siel;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BookingStandRepository;
import ma.digency.gov.amc.repository.ExhibitorRepository;
import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExhibitorServiceImpl implements ExhibitorService {

    private final ExhibitorRepository exhibitorRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final BookingStandRepository bookingStandRepository;

    @Override
    public Exhibitor findExhibitorByRefExhibitor(String refExhibitor) {
        return exhibitorRepository.findExhibitorByRefExhibitor(refExhibitor)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public Exhibitor createOrUpdateExhibitor(Exhibitor exhibitor) {
        if (null == exhibitor.getRefExhibitor()) {
            var ref = referenceSequenceService.generateRefExhibitor();
            exhibitor.setRefExhibitor(ref);
        }

        return exhibitorRepository.save(exhibitor);

    }

    @Override
    public void deleteExhibitor(String refExhibitor) {
        Exhibitor exhibitor = findExhibitorByRefExhibitor(refExhibitor);
        exhibitorRepository.delete(exhibitor);
    }

    @Override
    public Exhibitor updateStatus(String refExhibitor, StatusEnum statusEnum) {
        Exhibitor find = findExhibitorByRefExhibitor(refExhibitor);
        find.setStatus(statusEnum);
        return exhibitorRepository.save(find);
    }

    @Override
    public Page<Exhibitor> findAllExhibitors(Pageable pageable) {
        return exhibitorRepository.findAll(pageable);
    }

    @Override
    public BookingStand updateStand(String refObject, StatusEnum status) {
        BookingStand stand = bookingStandRepository.findByRefBookingStand(refObject)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BOOKING_STAND_NOT_FOUND));
        stand.setStatus(status);
        bookingStandRepository.save(stand);
        return stand;
    }

    @Override
    public Page<Exhibitor> findExhibitorsPageable(StatusEnum statusEnum, Pageable pageable) {
        if(statusEnum==null)
            return findAllExhibitors(pageable);

        return exhibitorRepository.findExhibitorsByStatusOrderByDateCreationAsc(statusEnum,pageable);
    }

    @Override
    public Page<Exhibitor> findExhibitorsByEdition(String refEdition, Pageable pageable, String country) {
        if(country==null)
            return exhibitorRepository.findExhibitorByEdition(refEdition,pageable);
        else{
            if(!country.equals("MA")){
                return exhibitorRepository.findForeignExhibitorByEdition(refEdition,pageable,"MA");
            }else {
                return exhibitorRepository.findExhibitorByEdition(refEdition,pageable,country);
            }
        }
    }

    @Override
    public Page<Exhibitor> findExhibitorsToValidatePageable(Pageable pageable) {
        return exhibitorRepository.findExhibitorToBeValidate(pageable,StatusEnum.PENDING);
    }


}
