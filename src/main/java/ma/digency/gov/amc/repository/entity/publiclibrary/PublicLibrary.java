package ma.digency.gov.amc.repository.entity.publiclibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;
import ma.digency.gov.amc.repository.entity.author.Book;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublicLibrary extends AcmAbstractAuditEntity {
    protected static final String PUBLIC_LIBRARY_REF_PUBLIC_LIBRARY= "ref_public_library";


    @Column(name = "ref_public_library")
    private String refPublicLibrary;

    private String libraryName;

    private String nameOfTheResponsible;

    private String Partner;

    @Type(type = "list-array")
    @Column(name = "PARTNER_TYPE", columnDefinition = "varchar[]")
    private List<String> partnerType;

    private String libraryStatus;

    private String libraryType;

    private LocalDate creationDate;

    private String space;

    private Integer employeesNumber;

    private String openingTime;

    private String address;

    private String region;

    private String provincePrefecture;

    private String office_Phone;

    private String fax;

    private String email;

    private String website;

    private String internetConnection;

    private String updateDateDocumentaryFund;

    private Boolean publication;

    @Lob
    private byte[] libraryPicture;


}
