package ma.digency.gov.amc.service.shared;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.AppSettingRepository;
import ma.digency.gov.amc.repository.entity.shared.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppSettingServiceImpl implements AppSettingService {


    private final AppSettingRepository appSettingRepository;


    @Override
    public List<Setting> getAllSetting() {
        return appSettingRepository.findAll();
    }

    @Override
    public void updateSetting(Setting setting) {
        appSettingRepository.save(setting);
    }

    @Override
    public Setting getSettingByCode(String code) {
        return appSettingRepository.findByKeySetting(code)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_INVALID_PARAMETER));

    }
}
