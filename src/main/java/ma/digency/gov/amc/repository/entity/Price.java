package ma.digency.gov.amc.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

@Entity
@Getter
@Setter
public class Price extends AcmAbstractAuditEntity {

    @Column(name = "ref_price")
    private String refPrice;

    private String title; //arabe fr

    private String description;

    private String price;  //min max                  award(ref, type,

    private Date startDate;

    private Date closingDate;

    private Date deadlineFiling;

    private Date resultsAnnouncementDate;

    //list catégories

    @Lob
    private byte[] picture;

}

//AwardHassan2 + AwardBook + AwardHonorary + AwardTheatre ===> Award entity ( refAward + typeAward + list(Price)
//
//typeAwar ===> entity ( refTypeAward + titleFr + titleAr + description + listCategories)
//
//Category ==> entity(refCategorie + titleAr +titleFr)
//
//Price (refprice + prix startDate + endDate +announcementDate + listCategories)