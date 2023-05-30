package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.BookingSchool;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingSchoolService {

    List<BookingSchool> findAllBookingSchools();

    BookingSchool findBookingSchoolByRefBookingSchool(String bookingSchool);

    void deleteBookingSchool(String bookingSchool);

    BookingSchool saveBookingSchool(BookingSchool newBookingSchool);

    BookingSchool updateStatus(String refBooking, StatusEnum statusEnum);

    Page<BookingSchool> findBookingSchoolPageable(StatusEnum statusEnum, Pageable pageable);

}
