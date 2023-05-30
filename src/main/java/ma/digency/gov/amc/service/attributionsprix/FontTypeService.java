package ma.digency.gov.amc.service.attributionsprix;

import ma.digency.gov.amc.repository.entity.attributionsprix.FontType;

public interface FontTypeService {

    FontType findFontTypeByRefFontType(String refFontType);
}
