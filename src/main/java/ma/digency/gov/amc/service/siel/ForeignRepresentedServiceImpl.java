package ma.digency.gov.amc.service.siel;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.ForeignRepresentedRepository;
import ma.digency.gov.amc.repository.PublisherRepresentedRepository;
import ma.digency.gov.amc.repository.entity.siel.ForeignRepresented;
import ma.digency.gov.amc.repository.entity.siel.PublisherRepresented;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForeignRepresentedServiceImpl implements ForeignRepresentedService {

    private final ForeignRepresentedRepository foreignRepresentedRepository;

    @Override
    public void deleteForeignRepresented(String refExhibitor, String refForeignRepresented) {
        foreignRepresentedRepository.delete(findForeignRepresented(refExhibitor, refForeignRepresented));
    }

    @Override
    public ForeignRepresented findForeignRepresented(String refExhibitor, String refForeignRepresented) {
        return foreignRepresentedRepository.findByRefExhibitorAndRefForeignRepresented(refExhibitor, refForeignRepresented)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PUBLISHER_REPRESENTED_NOT_FOUND));

    }

    @Override
    public ForeignRepresented update(ForeignRepresented pub) {
        return foreignRepresentedRepository.save(pub);
    }

    @Override
    public List<ForeignRepresented> findForeignRepresentedByRefExhibitor(String refExhibitor) {
        return foreignRepresentedRepository.findByRefExhibitor(refExhibitor);
    }

    @Override
    public ForeignRepresented saveForeignRepresented(ForeignRepresented fr) {
        return foreignRepresentedRepository.save(fr);
    }
}
