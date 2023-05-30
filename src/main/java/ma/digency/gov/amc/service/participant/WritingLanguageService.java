package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.repository.entity.participant.PublishingOtherProduct;
import ma.digency.gov.amc.repository.entity.participant.WritingLanguage;

import java.util.List;


public interface WritingLanguageService {

    WritingLanguage findWritingLanguageByRefWritingLanguage(String refWritingLanguage);

    WritingLanguage createOrUpdateWritingLanguage(WritingLanguage writingLanguage);

    void deleteWritingLanguage(String refWritingLanguage);

    List<WritingLanguage> findAllWritingLanguage();


}
