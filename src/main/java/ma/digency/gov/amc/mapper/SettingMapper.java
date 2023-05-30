package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import ma.digency.gov.amc.dto.SettingRequest;
import ma.digency.gov.amc.dto.SettingResponse;
import ma.digency.gov.amc.dto.TemplateNotificationDto;
import ma.digency.gov.amc.dto.shared.NomenclatureResponse;
import ma.digency.gov.amc.repository.entity.shared.Nomenclature;
import ma.digency.gov.amc.repository.entity.shared.Setting;
import ma.digency.gov.amc.repository.entity.shared.TemplateNotification;

import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface SettingMapper {


    TemplateNotification templateNotificationDtoToEntity(TemplateNotificationDto notificationDto);

    TemplateNotificationDto templateNotificationEntityToDto(TemplateNotification notification);

    @Maps(withIgnoreFields = {"refTemplateNotification"})
    TemplateNotification templateNotificationUpdate(TemplateNotificationDto dto, TemplateNotification entity);

    Setting updateSettingFromSettingRequest(SettingRequest request, Setting setting);

    SettingResponse settingToSettingResponse(Setting setting);

    NomenclatureResponse nomenclatureToNomenclatureResponse(Nomenclature nomenclature);

    List<NomenclatureResponse> nomenclaturesToNomenclaturesResponse(List<Nomenclature> nomenclature);
}
