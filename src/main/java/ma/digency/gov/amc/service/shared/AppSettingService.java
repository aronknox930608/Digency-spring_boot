package ma.digency.gov.amc.service.shared;

import ma.digency.gov.amc.repository.entity.shared.Nomenclature;
import ma.digency.gov.amc.repository.entity.shared.Setting;

import java.util.List;

public interface AppSettingService {

    List<Setting> getAllSetting();

    void updateSetting(Setting setting);

    Setting getSettingByCode(String code);

}
