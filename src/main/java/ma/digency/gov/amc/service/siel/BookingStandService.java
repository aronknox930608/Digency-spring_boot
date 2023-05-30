package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface BookingStandService {


    void deleteBookingStand(String refBookingStand);

    BookingStand findBookingStand(String refBookingStand);

    Page<BookingStand> findBookingPageable(StatusEnum statusEnum, Pageable pageable);

    BookingStand findBookingByEditionAndExhibitor(String refEditions, String refExhibitor);
}
