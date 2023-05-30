package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.RolesRequest;
import ma.digency.gov.amc.dto.SettingRequest;
import ma.digency.gov.amc.dto.SettingResponse;
import ma.digency.gov.amc.dto.TemplateNotificationDto;
import ma.digency.gov.amc.dto.shared.NomenclatureResponse;

import java.util.List;

public interface SettingProcess {

    TemplateNotificationDto findTemplateNotification(String refTemplate);

    void updateTemplateNotification(String refTemplate, TemplateNotificationDto dto);

    TemplateNotificationDto findTemplateNotificationByNatureAndType(String type, String nature);

    void addRolesToAccount(String refAccount, RolesRequest rolesRequest);

    List<SettingResponse> getAllParameters();

    void updateParameter(String paramName, SettingRequest request);

    void sendMailAndNotification(String refEntity, String refObject);

    List<NomenclatureResponse> getAllNomenclature();

    NomenclatureResponse getNomenclatureByType(String typeNomenclature);
}
