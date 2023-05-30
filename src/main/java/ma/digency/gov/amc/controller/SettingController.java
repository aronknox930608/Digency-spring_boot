package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.RolesRequest;
import ma.digency.gov.amc.dto.SettingRequest;
import ma.digency.gov.amc.dto.SettingResponse;
import ma.digency.gov.amc.dto.TemplateNotificationDto;
import ma.digency.gov.amc.dto.shared.NomenclatureResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
import java.util.List;

@Validated
@RequestMapping("setting/")
public interface            SettingController {

    @GetMapping(path = "template-notification/{refObject}")
    ResponseEntity<TemplateNotificationDto> findTemplateNotification(@PathVariable("refObject") @NotBlank String refObject);

    @PutMapping(path = "template-notification/{refObject}")
    ResponseEntity<Void> updateTemplateNotification(@PathVariable("refObject") @NotBlank String refObject,
                                                    @RequestBody TemplateNotificationDto notificationDto);

    @GetMapping(path = "template-notification")
    ResponseEntity<TemplateNotificationDto> findTemplateNotificationByNatureAndType(@Param("nature") @NotBlank String nature,
                                                                                    @Param("type") @NotBlank String type);

    @PostMapping(path = "add-roles/{refAccount}")
    ResponseEntity<Void> addRolesToAccount(@PathVariable("refAccouunt") String refAccount, @RequestBody
            RolesRequest roles);

    @GetMapping(path = "parameters")
    ResponseEntity<List<SettingResponse>> getAllParameters();

    @PutMapping(path = "parameters/{paramName}")
    ResponseEntity<SettingResponse> updateParameter(@PathVariable("paramName") String paramName,
                                                    @RequestBody SettingRequest request);

    @PostMapping(path ="template-notification/{refEntity}/send-mail/{refObject}")
    ResponseEntity<Void> sendMail(@PathVariable("refEntity") String refEntity,
                                  @PathVariable("refObject") String refObject);

    @GetMapping(path="nomenclatures")
    ResponseEntity<List<NomenclatureResponse>> getAllNomenclature();

    @GetMapping("nomenclatures/{typeNomenclature}")
    ResponseEntity<NomenclatureResponse> getNomenclatureByType(@PathVariable("typeNomenclature") String typeNomenclature);
}
