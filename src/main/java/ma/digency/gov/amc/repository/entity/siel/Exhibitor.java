package ma.digency.gov.amc.repository.entity.siel;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Exhibitor extends AcmAbstractAuditEntity {
    protected static final String EXHIBITOR_REF_EXHIBITOR = "ref_exhibitor";


    @Column(name = "ref_exhibitor")
    private String refExhibitor;

    private String country; //

    private String email; //

    private String cin; //

    private String city; //

    private String phoneNumber; //

    private String fax; //

    private String address;

    private String hallClass;

    private String specialization;

    private String numberArabicVersion;

    private String personalPhoneNumber; //

    private String numberForeignVersion;

    private String companyRepresentative;

    @Type(type = "list-array")
    @Column(name = "PRESENTED_MATERIEL", columnDefinition = "varchar[]", nullable = false)
    private List<String> presentedMateriels;

    @Type(type = "list-array")
    @Column(name = "ACTIVITY_BRANCHE", columnDefinition = "varchar[]", nullable = false)
    private List<String> activityBranches;

    private String wingAreaSquare;

    private String publishingHouseName;

    private String responsibleManagerName;

    private LocalDate passportExpiration;

    private String passportNumber;

    private String passportType;

    private String personName;

    private LocalDate birthDay;

    private String birthCountry;

    private String birthNationality;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private boolean hasMultipleRepresented;

    private boolean hasMultipleForiegen;


    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = EXHIBITOR_REF_EXHIBITOR, referencedColumnName = EXHIBITOR_REF_EXHIBITOR, updatable = false)
    private List<Publication> publications;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = EXHIBITOR_REF_EXHIBITOR, referencedColumnName = EXHIBITOR_REF_EXHIBITOR, updatable = false)
    private List<ActivityProposal> activities;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = EXHIBITOR_REF_EXHIBITOR, referencedColumnName = EXHIBITOR_REF_EXHIBITOR, updatable = false)
    private List<PublisherRepresented> publishers;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = EXHIBITOR_REF_EXHIBITOR, referencedColumnName = EXHIBITOR_REF_EXHIBITOR, updatable = false)
    private List<ForeignRepresented> foreignPublishers;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = EXHIBITOR_REF_EXHIBITOR, referencedColumnName = EXHIBITOR_REF_EXHIBITOR, updatable = false)
    private List<BookingStand> bookingStands;




}
