package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExhibitorService {

    Exhibitor findExhibitorByRefExhibitor(String refExhibitor);

    Exhibitor createOrUpdateExhibitor(Exhibitor exhibitor);

    void deleteExhibitor(String refExhibitor);

    Exhibitor updateStatus(String refExhibitor, StatusEnum statusEnum);


    Page<Exhibitor> findAllExhibitors(Pageable pageable);

    BookingStand updateStand(String refObject, StatusEnum status);

    Page<Exhibitor> findExhibitorsPageable(StatusEnum statusEnum , Pageable pageable);

    Page<Exhibitor> findExhibitorsByEdition(String refEdition, Pageable pageable, String country);

    Page<Exhibitor> findExhibitorsToValidatePageable(Pageable pageable);
}
