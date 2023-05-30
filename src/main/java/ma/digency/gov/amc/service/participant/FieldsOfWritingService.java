package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.repository.entity.participant.FieldsOfWriting;
import ma.digency.gov.amc.repository.entity.participant.WritingLanguage;

import java.util.List;


public interface FieldsOfWritingService {

    FieldsOfWriting findFieldsOfWritingByRefFieldsOfWriting(String refFieldsOfWriting);

    FieldsOfWriting createOrUpdateFieldsOfWriting(FieldsOfWriting fieldsOfWriting);

    void deleteFieldsOfWriting(String refFieldsOfWriting);

    List<FieldsOfWriting> findAllFieldsOfWriting();


}
