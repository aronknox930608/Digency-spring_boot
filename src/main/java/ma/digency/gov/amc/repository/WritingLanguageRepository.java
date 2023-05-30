package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import ma.digency.gov.amc.repository.entity.participant.WritingLanguage;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WritingLanguageRepository extends GenericRepository<WritingLanguage, Long> {

    Optional<WritingLanguage> findWritingLanguageByRefWritingLanguage(String refWritingLanguage);

}
