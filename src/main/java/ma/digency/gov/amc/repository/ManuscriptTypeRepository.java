package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.ManuscriptType;

public interface ManuscriptTypeRepository extends GenericRepository<ManuscriptType, Long>{

  ManuscriptType findManuscriptTypeByRefManuscriptType(String RefManuscriptType);


}
