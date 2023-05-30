package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.RolesRequest;
import ma.digency.gov.amc.dto.SettingRequest;
import ma.digency.gov.amc.dto.SettingResponse;
import ma.digency.gov.amc.dto.TemplateNotificationDto;
import ma.digency.gov.amc.dto.shared.NomenclatureResponse;
import ma.digency.gov.amc.mapper.SettingMapper;
import ma.digency.gov.amc.repository.entity.shared.*;
import ma.digency.gov.amc.repository.entity.siel.BookingSchool;
import ma.digency.gov.amc.repository.entity.siel.BookingStand;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.service.shared.AppSettingService;
import ma.digency.gov.amc.service.shared.NomenclatureService;
import ma.digency.gov.amc.service.shared.NotificationTemplateService;
import ma.digency.gov.amc.service.shared.RoleService;
import ma.digency.gov.amc.service.siel.BookingSchoolService;
import ma.digency.gov.amc.service.siel.BookingStandService;
import ma.digency.gov.amc.service.siel.ExhibitorService;
import ma.digency.gov.amc.utils.enumeration.EntityNameEnum;
import ma.digency.gov.amc.utils.enumeration.NotificationNature;
import ma.digency.gov.amc.utils.enumeration.NotificationType;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettingProcessImpl implements SettingProcess {

    private final NotificationTemplateService notificationTemplateService;

    private final NotificationProcess notificationProcess;

    private final AccountService accountService;

    private final RoleService roleService;

    private final ExhibitorService exhibitorService;

    private final BookingStandService bookingStandService;

    private final BookingSchoolService bookingSchoolService;

    private final SettingMapper settingMapper;

    private final AppSettingService appSettingService;

    private final NomenclatureService nomenclatureService;

    @Override
    public TemplateNotificationDto findTemplateNotification(String refTemplate) {
        return settingMapper.templateNotificationEntityToDto(
                notificationTemplateService.findNotificationTemplate(refTemplate));
    }

    @Override
    public TemplateNotificationDto findTemplateNotificationByNatureAndType(String type, String nature) {
        TemplateNotification templateNotification = notificationTemplateService.findTemplateNotificationByNatureAndType(
                NotificationType.valueOf(type), NotificationNature.valueOf(nature));
        return settingMapper.templateNotificationEntityToDto(templateNotification);
    }

    @Override
    public void addRolesToAccount(String refAccount, RolesRequest rolesRequest) {

        Account account = accountService.findAccountByRefAccount(refAccount);
        rolesRequest.getRoles().forEach(roleDto -> {
            Role role = roleService.findRoleByCodeRole(roleDto);
            /*AccountRole addRole = new AccountRole();
            addRole.setRefRole(role);
            addRole.setRefAccount(account);
            account.getRoles().add(addRole);*/
        });
        accountService.updateAccount(account);

    }

    @Override
    public List<SettingResponse> getAllParameters() {
        return appSettingService.getAllSetting().stream()
                .map(settingMapper::settingToSettingResponse).collect(Collectors.toList());
    }

    @Override
    public void updateParameter(String paramName, SettingRequest request) {
        Setting find = appSettingService.getSettingByCode(paramName);
        settingMapper.updateSettingFromSettingRequest(request, find);
        appSettingService.updateSetting(find);
    }

    @Override
    public void sendMailAndNotification(String refEntity, String refObject) {
        var entityName = EntityNameEnum.from(refEntity);
        switch (entityName) {

            case BOOKING_STAND:
                var stand = bookingStandService.findBookingStand(refObject);
                notificationProcess.sendNotificationAndMail(prepareParameters(stand), NotificationNature.VALID_STAND);
                break;

            case BOOKING_SCHOOL:
                var school = bookingSchoolService.findBookingSchoolByRefBookingSchool(refObject);
                notificationProcess.sendNotificationAndMail(prepareParameters(school), NotificationNature.VALID_BOOKING);
                break;

        }
    }

    @Override
    public List<NomenclatureResponse> getAllNomenclature() {
        var nomenclature = nomenclatureService.getAllNomenclature();
        return settingMapper.nomenclaturesToNomenclaturesResponse(nomenclature);
    }

    @Override
    public NomenclatureResponse getNomenclatureByType(String typeNomenclature) {
        var nomenclature = nomenclatureService.getAllNomenclatureByFamily(typeNomenclature);
        return settingMapper.nomenclatureToNomenclatureResponse(nomenclature);
    }

    @Override
    public void updateTemplateNotification(String refTemplate, TemplateNotificationDto dto) {
        TemplateNotification templateNotification = notificationTemplateService.findNotificationTemplate(refTemplate);
        templateNotification = settingMapper.templateNotificationUpdate(dto, templateNotification);
        notificationTemplateService.updateNotificationTemplate(templateNotification);
    }

    protected HashMap<String, String> prepareParameters(BookingSchool book) {
        HashMap<String, String> pars = new HashMap<>();
        if (book != null) {
            pars.put("to", book.getEmail());
            pars.put("#fullName", book.getName());
            pars.put("#salutation", "Mr/Mme");
            pars.put("#visiteDate", book.getDateVisit().toString() + " " + book.getTimeVisit());
            pars.put("#status", book.getStatus().name());
        }
        return pars;
    }


    private HashMap<String, String> prepareParameters(BookingStand stand) {
        HashMap<String, String> pars = new HashMap<>();
        if (stand != null) {
            Exhibitor find = exhibitorService.findExhibitorByRefExhibitor(stand.getRefExhibitor());
            pars.put("refObject", find.getRefExhibitor());
            pars.put("to", find.getEmail());
            pars.put("#fullName", find.getResponsibleManagerName());
            pars.put("#salutation", "Mr/Mme");
            pars.put("#volumeStand", stand.getVolumeInCubicMeter());
            pars.put("#status", stand.getStatus().name());
        }

        return pars;
    }
}
