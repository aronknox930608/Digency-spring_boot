package ma.digency.gov.amc.repository.entity.siel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.digency.gov.amc.repository.entity.AcmAbstractAuditEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityProposal extends AcmAbstractAuditEntity {

    @Column(name = "ref_activity_proposal")
    private String refActivityProposal;

    private LocalDate proposedDate;

    private String topic;

    private String participants;

    private String activityPlace;

    private String bookTitle;

    private String author;

    private String publishingHouse;

    private String publishingYear;

    private String editeur;

    @Column(name = "ref_exhibitor")
    private String refExhibitor;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] procuration;

}
