package ma.digency.gov.amc.service.siel;

import ma.digency.gov.amc.repository.entity.siel.ForeignRepresented;
import ma.digency.gov.amc.repository.entity.siel.PublisherRepresented;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ForeignRepresentedService {

    void deleteForeignRepresented(String refExhibitor, String refForeignRepresented);

    ForeignRepresented findForeignRepresented(String refExhibitor, String refForeignRepresented);

    ForeignRepresented update(ForeignRepresented pub);

    List<ForeignRepresented> findForeignRepresentedByRefExhibitor(String refExhibitor);

    ForeignRepresented saveForeignRepresented(ForeignRepresented fr);
}
