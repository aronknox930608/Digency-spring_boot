package ma.digency.gov.amc.repository;

import ma.digency.gov.amc.repository.entity.attributionsprix.FontType;

public interface FontTypeRepository extends GenericRepository<FontType, Long> {

   FontType findFontTypeByRefFontType(String refFontType);
}
