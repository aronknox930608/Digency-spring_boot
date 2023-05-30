package ma.digency.gov.amc.service.siel;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BookingSchoolRepository;
import ma.digency.gov.amc.repository.entity.siel.BookingSchool;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingSchoolServiceImpl implements BookingSchoolService {

    private final BookingSchoolRepository bookingSchoolRepository;

    @Override
    public List<BookingSchool> findAllBookingSchools() {
        return bookingSchoolRepository.findAllBookingSchoolsByOrderByIdDesc();
    }

    @Override
    public BookingSchool findBookingSchoolByRefBookingSchool(String bookingSchool) {
        return bookingSchoolRepository.findByRefBookingSchool(bookingSchool)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BOOKING_SCHOOL_NOT_FOUND));
    }

    @Override
    public void deleteBookingSchool(String bookingSchool) {
        bookingSchoolRepository.delete(findBookingSchoolByRefBookingSchool(bookingSchool));
    }

    @Override
    public BookingSchool saveBookingSchool(BookingSchool newBookingSchool) {
        return bookingSchoolRepository.save(newBookingSchool);
    }

    @Override
    public BookingSchool updateStatus(String refBooking, StatusEnum statusEnum) {
        BookingSchool find = findBookingSchoolByRefBookingSchool(refBooking);
        find.setStatus(statusEnum);
        return bookingSchoolRepository.save(find);
    }

    @Override
    public Page<BookingSchool> findBookingSchoolPageable(StatusEnum statusEnum, Pageable pageable) {
        if(statusEnum==null)
            return bookingSchoolRepository.findAll(pageable);

        return bookingSchoolRepository.findBookingSchoolByStatusOrderByDateCreationAsc(statusEnum,pageable);
    }
}
