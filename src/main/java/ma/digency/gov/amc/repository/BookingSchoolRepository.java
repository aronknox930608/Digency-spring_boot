package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.siel.BookingSchool;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingSchoolRepository extends GenericRepository<BookingSchool, Long> {

    Optional<BookingSchool> findByRefBookingSchool(String refBookingSchool);


    List<BookingSchool> findAllBookingSchoolsByOrderByIdDesc();

    Page<BookingSchool> findBookingSchoolByStatusOrderByDateCreationAsc(StatusEnum statusEnum, Pageable pageable);
 }
