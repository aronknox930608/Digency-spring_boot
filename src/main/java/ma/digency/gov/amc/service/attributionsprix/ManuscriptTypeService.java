package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;

import java.util.List;

public interface ManuscriptTypeService {

    ManuscriptType findManuscriptTypeByRefManuscriptType(String refManuscriptType);

    List<ManuscriptType> findAllManuscriptType();


}
