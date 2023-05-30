package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.RolesRequest;
import ma.digency.gov.amc.dto.SettingRequest;
import ma.digency.gov.amc.dto.SettingResponse;
import ma.digency.gov.amc.dto.TemplateNotificationDto;
import ma.digency.gov.amc.dto.shared.NomenclatureResponse;
import ma.digency.gov.amc.process.SettingProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Tag(name = "Setting")
public class SettingControllerImpl implements SettingController {

    @Autowired
    SettingProcess settingProcess;


    @Override
    public ResponseEntity<TemplateNotificationDto> findTemplateNotificationByNatureAndType(String nature, String type) {
        settingProcess.findTemplateNotificationByNatureAndType(type, nature);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> addRolesToAccount(String refAccount, RolesRequest roles) {
        //find role and add to Account
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<SettingResponse>> getAllParameters() {
        return ResponseEntity.status(HttpStatus.CREATED).body(settingProcess.getAllParameters());
    }

    @Override
    public ResponseEntity<SettingResponse> updateParameter(String paramName, SettingRequest request) {
        settingProcess.updateParameter(paramName, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> sendMail(String refEntity, String refObject) {
        settingProcess.sendMailAndNotification(refEntity,refObject);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<NomenclatureResponse>> getAllNomenclature() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(settingProcess.getAllNomenclature());
    }

    @Override
    public ResponseEntity<NomenclatureResponse> getNomenclatureByType(String typeNomenclature) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(settingProcess.getNomenclatureByType(typeNomenclature));
    }

    @Override
    public ResponseEntity<TemplateNotificationDto> findTemplateNotification(String refObject) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(settingProcess.findTemplateNotification(refObject));
    }

    @Override
    public ResponseEntity<Void> updateTemplateNotification(String refObject, TemplateNotificationDto notificationDto) {
        settingProcess.updateTemplateNotification(refObject, notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
