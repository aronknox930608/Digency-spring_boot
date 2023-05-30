package ma.digency.gov.amc.repository.entity.shared;

import lombok.Getter;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "app_setting")
@Getter
@Setter
public class Setting extends AcmAbstractEntity {

    private String refSetting;

    private String keySetting;

    private String valueSetting;

    private String descriptionSetting;

    private LocalDate endDate;

    private LocalDate startedDate;

    private boolean isDate;
}
