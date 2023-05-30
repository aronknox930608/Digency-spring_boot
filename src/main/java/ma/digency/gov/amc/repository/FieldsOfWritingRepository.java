package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.participant.FacilitiesServices;
import ma.digency.gov.amc.repository.entity.participant.FieldsOfWriting;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldsOfWritingRepository extends GenericRepository< FieldsOfWriting, Long> {

    Optional<FieldsOfWriting> findFieldsOfWritingByRefFieldsOfWriting(String refFieldsOfWriting);

}
