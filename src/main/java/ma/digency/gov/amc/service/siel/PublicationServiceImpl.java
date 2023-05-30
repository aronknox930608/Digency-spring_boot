package ma.digency.gov.amc.service.siel;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.PublicationRepository;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.repository.entity.siel.Publication;
import ma.digency.gov.amc.utils.SearchUtils;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    @Override
    public List<Publication> findAllPublications(Exhibitor exhibitor) {
        return publicationRepository.findPublicationByRefExhibitor(exhibitor);
    }

    @Override
    public Publication findPublicationByRefPublication(String refExhibitor, String refPublication) {
        return publicationRepository.findPublicationByRefPublicationAndRefExhibitor(refPublication, refExhibitor)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PUBLICATION_NOT_FOUND));
    }

    @Override
    public Publication findByRefPublication(String refPublication) {
        return publicationRepository.findByRefPublication(refPublication)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PUBLICATION_NOT_FOUND));
    }

    @Override
    public void deletePublication(String refExhibitor, String refPublication) {
        var pub = findPublicationByRefPublication(refExhibitor, refPublication);
        pub.setStatus(StatusEnum.DELETED);
        publicationRepository.save(pub);
    }

    @Override
    public void deletePublications(List<Publication> publications) {
        publicationRepository.deleteAll(publications);
    }

    @Override
    public Publication savePublication(Publication newPablication) {
        return publicationRepository.save(newPablication);
    }

    @Override
    public Publication updateStatus(String refPublication, StatusEnum statusEnum) {
        Publication find = publicationRepository.findByRefPublication(refPublication)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PUBLICATION_NOT_FOUND));
        find.setStatus(statusEnum);
        return publicationRepository.save(find);
    }

    @Override
    public Page<Publication> findPublicationsPageable(String refExhibitor, StatusEnum statusEnum, Pageable pageable) {
        if(statusEnum == null){
            return publicationRepository.findPublicationsByRefExhibitorOrderByDateCreationAsc(refExhibitor,pageable);
        }
        return publicationRepository.findPublicationsByRefExhibitorAndStatusOrderByDateCreationAsc(refExhibitor,statusEnum,pageable);
    }

    @Override
    public Page<Publication> findPublicationsByEdition(String refEdition, Pageable pageable) {
        if(pageable==null){
            pageable= SearchUtils.createPageable(0,10);
        }
        return publicationRepository.findPublicationsByEditions(refEdition, pageable);
    }

    @Override
    public Page<Publication> findPublicationsByEditionAndExhibitor(String refEdition, Pageable pageable, String refExhibitor) {
        return publicationRepository.findPublicationsByEditionAndByExhibitor(refEdition,pageable,refExhibitor);
    }

}
