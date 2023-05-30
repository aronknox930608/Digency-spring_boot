package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingStandRepository extends GenericRepository<BookingStand, Long> {

    Optional<BookingStand> findByRefBookingStand(String refBookinStand);

    Page<BookingStand> findBookingStandsByStatusOrderByDateCreationAsc(StatusEnum statusEnum, Pageable pageable);

    BookingStand findBookingStandByRefEditionAndRefExhibitor(String refEdition, String refExhibitor);
}
