package ma.digency.gov.amc.service.siel;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BookingStandRepository;
import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingStandServiceImpl implements BookingStandService {

    private final BookingStandRepository bookingStandRepository;

    @Override
    public void deleteBookingStand(String refBookingStand) {
        bookingStandRepository.delete(findBookingStand(refBookingStand));
    }

    @Override
    public BookingStand findBookingStand(String refBookingStand) {
        return bookingStandRepository.findByRefBookingStand(refBookingStand)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BOOKING_STAND_NOT_FOUND));

    }

    @Override
    public Page<BookingStand> findBookingPageable(StatusEnum statusEnum, Pageable pageable) {
        if(statusEnum==null)
            return bookingStandRepository.findAll(pageable);
        return bookingStandRepository.findBookingStandsByStatusOrderByDateCreationAsc(statusEnum,pageable);
    }

    @Override
    public BookingStand findBookingByEditionAndExhibitor(String refEditions, String refExhibitor) {
        return bookingStandRepository.findBookingStandByRefEditionAndRefExhibitor(refEditions,refExhibitor);
    }
}
