package ma.digency.gov.amc.repository.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractEntity;
import ma.digency.gov.amc.utils.enumeration.AuthActionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthIssue extends AcmAbstractEntity {
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private AuthActionType authActionType;

    private String error;
}
