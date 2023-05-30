package ma.digency.gov.amc.utils.service;

import ma.digency.gov.amc.utils.enumeration.SequenceEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ReferenceSequenceServiceImpl implements ReferenceSequenceService {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("AMC")
    private String reference;

    /**
     * Public constructor.
     * <p>
     * To construct a {@link ReferenceSequenceServiceImpl} with :
     * </p>
     *
     * @param entityManager
     */
    @Autowired
    public ReferenceSequenceServiceImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    /**
     * @param sequenceName
     * @return
     */
    public Object getNextRefSequence(String sequenceName) {
        return entityManager.createNativeQuery("SELECT nextval('" + sequenceName + "')")
                .getSingleResult();
    }

    public String generateReference(SequenceEnum sequenceEnum) {
        String prefix = sequenceEnum.getPrefix() + "_" + reference + "_";
        return prefix + StringUtils.leftPad(String.valueOf(getNextRefSequence(sequenceEnum.getSequence())), 9, '0');
    }

    @Override
    public String generateRefAccount() {
        return generateReference(SequenceEnum.ACCOUNT);
    }

    @Override
    public String generateRefTemplateNotification() {
        return generateReference(SequenceEnum.TEMPLATE_NOTIFICATION);
    }

    @Override
    public String generateRefArtistAccount() {
        return generateReference(SequenceEnum.ARTIST_ACCOUNT);
    }

    @Override
    public String generateRefGeneralInfo() {
        return generateReference(SequenceEnum.GENERAL_INFORMATION);
    }

    @Override
    public String generateRefCooperativeAccount() {
        return generateReference(SequenceEnum.COOPERATIVE_ACCOUNT);
    }

    @Override
    public String generateRefCooperativeBookAccount() {
        return generateReference(SequenceEnum.COOPERATIVE_ACCOUNT_BOOK);
    }

    @Override
    public String generateRefCompanyAccount() {
        return generateReference(SequenceEnum.COMPANY_ACCOUNT);
    }

    @Override
    public String generateRefInternalAccount() {
        return generateReference(SequenceEnum.INTERNAL_ACCOUNT);
    }

    @Override
    public String generateRefCommission() {
        return generateReference(SequenceEnum.COMMISSION);
    }

    @Override
    public String generateRefPlanningCommission() {
        return generateReference(SequenceEnum.PLANNING_COMMISSION);
    }

    @Override
    public String generateRefVoteCommission() {
        return generateReference(SequenceEnum.VOTE_COMMISSION);
    }

    @Override
    public String generateRefHandWritten() {
        return generateReference(SequenceEnum.HAND_WRITTEN);
    }

    @Override
    public String generateRefBookInformation() {
        return generateReference(SequenceEnum.BOOK_INFORMATION);
    }

    @Override
    public String generateRefDocument() {
        return generateReference(SequenceEnum.DOCUMENT);
    }

    @Override
    public String generateRefExportCollection() {
        return generateReference(SequenceEnum.EXPORT_COLLECTION);
    }

    @Override
    public String generateRefExportCollectionTemporary() {
        return generateReference(SequenceEnum.EXPORT_COLLECTION_TEMPORARY);
    }

    @Override
    public String generateRefExportCollectionDefinitive() {
        return generateReference(SequenceEnum.EXPORT_COLLECTION_DEFINITIVE);
    }

    @Override
    public String generateRefArtisticProfession() {
        return generateReference(SequenceEnum.ARTISTIC_PROFESSION);
    }

    @Override
    public String generateRefArtisticProfessionDomain() {
        return generateReference(SequenceEnum.ARTISTIC_PROFESSION_DOMAIN);
    }

    @Override
    public String generateRefArtisticProfessionCategory() {
        return generateReference(SequenceEnum.ARTISTIC_PROFESSION_CATEGORY);
    }


    @Override
    public String generateRefRegion() {
        return generateReference(SequenceEnum.REGION);
    }

    @Override
    public String generateRefProvince() {
        return generateReference(SequenceEnum.PROVINCE);
    }

    @Override
    public String generateRefCard() {
        return generateReference(SequenceEnum.CARD);
    }

    @Override
    public String generateRefProject() {
        return generateReference(SequenceEnum.PROJECT);
    }

    @Override
    public String generateRefProjectDomain() {
        return generateReference(SequenceEnum.PROJECT_DOMAIN);
    }

    @Override
    public String generateRefProjectSubDomain() {
        return generateReference(SequenceEnum.PROJECT_SUB_DOMAIN);
    }

    @Override
    public String generateRefEventGuest() {
        return generateReference(SequenceEnum.EVENT_GUEST);
    }

    @Override
    public String generateRefEvent() {
        return generateReference(SequenceEnum.EVENT);
    }

    @Override
    public String generateRefBudget() {
        return generateReference(SequenceEnum.BUDGET);
    }

    @Override
    public String generateRefBudgetProject() {
        return generateReference(SequenceEnum.BUDGET_PROJECT);
    }

    @Override
    public String generateRefProgram() {
        return generateReference(SequenceEnum.PROGRAM);
    }

    @Override
    public String generateRefNotice() {
        return generateReference(SequenceEnum.NOTICE);
    }

    @Override
    public String generateRefFinancial() {
        return generateReference(SequenceEnum.FINANCIAL);
    }

    @Override
    public String generateRefPartner() {
        return generateReference(SequenceEnum.PARTNER);
    }

    @Override
    public String generateRefConvention() {
        return generateReference(SequenceEnum.CONVENTION);
    }

    @Override
    public String generateRefPublicLibrary() {
        return generateReference(SequenceEnum.PUBLIC_LIBRARY);
    }

    @Override
    public String generateRefBorrow() {
        return generateReference(SequenceEnum.BORROW);
    }

    @Override
    public String generateRefReader() {
        return generateReference(SequenceEnum.READER);
    }

    @Override
    public String generateRefBeneficiary() {
        return generateReference(SequenceEnum.BENEFICIARY);
    }

    @Override
    public String generateRefRole() {
        return generateReference(SequenceEnum.ROLE);
    }

    @Override
    public String generateRefLibrary() {
        return generateReference(SequenceEnum.LIBRARY);
    }

    @Override
    public String generateRefPublishingHouse() {
        return generateReference(SequenceEnum.PUBLISHING_HOUSE);
    }

    @Override
    public String generateRefDistributor() {
        return generateReference(SequenceEnum.DISTRIBUTOR);
    }

    @Override
    public String generateRefPrinter() {
        return generateReference(SequenceEnum.PRINTER);
    }

    @Override
    public String generateRefBookAmazigh() {
        return generateReference(SequenceEnum.BOOK_AMAZIGH);
    }

    @Override
    public String generateRefPublication() {
        return generateReference(SequenceEnum.PUBLICATION);
    }

    @Override
    public String generateRefExhibitor() {
        return generateReference(SequenceEnum.EXHIBITOR);
    }

    @Override
    public String generateRefAuthor() {return generateReference(SequenceEnum.AUTHOR);}

    @Override
    public String generateRefBook() {return generateReference(SequenceEnum.BOOK);}

    @Override
    public String generateRefBookingSchool() {
        return generateReference(SequenceEnum.BOOKING_SCHOOL);
    }

    @Override
    public String generateRefCommissionAccount() {
        return generateReference(SequenceEnum.COMMISSION_ACCOUNT);
    }

    @Override
    public String generateRefPublisherRepresented() {
        return generateReference(SequenceEnum.PUBLISHER_REPRESENTED);
    }

    @Override
    public String generateRefActivityProposal() {
        return generateReference(SequenceEnum.ACTIVITY_PROPOSAL);
    }

    @Override
    public String generateRefBookingStand() {
        return generateReference(SequenceEnum.BOOKING_STAND);
    }

    @Override
    public String generateRefEdition() {
        return generateReference(SequenceEnum.EDITION);
    }

    @Override
    public String generateRefNotification() {
        return generateReference(SequenceEnum.NOTIFICATION);
    }
    @Override
    public String generateRefDemand()
    {
        return generateReference(SequenceEnum.DEMAND);
    }

    @Override
    public String generateRefForeignRepresented() {
        return generateReference(SequenceEnum.FOREIGN_REP);
    }

    @Override
    public String generateRefFacilitiesServices() {
        return generateReference(SequenceEnum.FACILITIES_SERVICES);
    }

    @Override
    public String generateRefSellingPoints() {
        return generateReference(SequenceEnum.SELLING_POINTS);
    }

    @Override
    public String generateRefWritingLanguage() {
        return generateReference(SequenceEnum.WRITING_LANGUAGE);
    }

    @Override
    public String generateRefFieldsOfWriting() {
        return generateReference(SequenceEnum.FIELDS_OF_WRITING);
    }

    @Override
    public String generateRefBookPromotionForm() {
        return generateReference(SequenceEnum.BOOK_PROMOTION_FORM);
    }

    @Override
    public String generateRefPublishingOtherProduct() {
        return generateReference(SequenceEnum.PUBLISHING_OTHER_PRODUCT);
    }

    @Override
    public String generateRefPersonnel() {
        return generateReference(SequenceEnum.PERSONNEL);
    }

    @Override
    public String generateRefBriefcaseBooks() {
        return generateReference(SequenceEnum.BRIEFCASE_BOOKS);
    }

    @Override
    public String generateRefDocumentaryCollection() {
        return generateReference(SequenceEnum.DOCUMENTARY_COLLECTION);
    }

    @Override
    public String generateRefEquipment() {
        return generateReference(SequenceEnum.EQUIPMENT);
    }

    @Override
    public String generateRefItEquipment() {
        return generateReference(SequenceEnum.IT_EQUIPMENT);
    }

    @Override
    public String generateRefSpaces() {
        return generateReference(SequenceEnum.SPACES);
    }

    @Override
    public String generateRefBookPerson() {
        return generateReference(SequenceEnum.BOOK_PERSON);
    }

    @Override
    public String generateRefAward() {
        return generateReference(SequenceEnum.AWARD);
    }

    @Override
    public String generateRefAwardType() {
        return generateReference(SequenceEnum.AWARDTYPE);
    }

    @Override
    public String generateRefAwardCategories() {
        return generateReference(SequenceEnum.AWARDCATEGORIES);
    }

    @Override
    public String generateRefCategoriesPlanning() {
        return generateReference(SequenceEnum.CATEGORIESPLANNING);
    }

    @Override
    public String generateRefPricePlanning() {
        return generateReference(SequenceEnum.PRICEPLANNING);
    }

    @Override
    public String generateRefDemandPrice() {
        return generateReference(SequenceEnum.DEMANDPRICE);
    }

    @Override
    public String generateRefGeneralInformation() {
        return  generateReference(SequenceEnum.GENERAL_INFORMATION);
    }

    @Override
    public String generateRefDemandPlanning() {return generateReference(SequenceEnum.DEMAND_PLANNING);}

    @Override
    public String generateRefGeneralMember() {
        return  generateReference(SequenceEnum.GENERAL_MEMBER);
    }

    @Override
    public String generateRefDemandCard() {
        return  generateReference(SequenceEnum.DEMAND);
    }


    @Override
    public  String generateRefManuscriptType(){return generateReference(SequenceEnum.MANUSCRIPTSINFORMATION);}

    @Override
    public  String generateRefOwnerHandwritten(){return  generateReference(SequenceEnum.HANDWRITTEN);}

    @Override
    public String generateRefCandidateHonoraryAward() {
        return generateReference(SequenceEnum.CANDIDATEHONORARYAWARD);
    }

    @Override
    public String generateRefBookPrice() {
        return generateReference(SequenceEnum.BOOKPRICE);
    }

    @Override
    public String generateRefAwardObtained() {
        return generateReference(SequenceEnum.AWARDOBTAINED);
    }

    @Override
    public String generateRefAwardHassan2() {
        return generateReference(SequenceEnum.PRIXHASSAN);
    }

    @Override
    public String generateRefAwardHonorary() {
        return generateReference(SequenceEnum.AWARDHONORARY);
    }

    @Override
    public String generateRefAwardBook() {
        return generateReference(SequenceEnum.AWARDBOOK);
    }

    @Override
    public String generateRefTheaterPiece() {
        return generateReference(SequenceEnum.THEATERPIECE);
    }

    @Override
    public String generateRefParticipantsTheater() {
        return generateReference(SequenceEnum.PARTICIPANTSTHEATER);
    }

    @Override
    public String generateRefAwardTheater() {
        return generateReference(SequenceEnum.AWARDTHEATER);
    }

    @Override
    public String generateRefPrice() {
        return generateReference(SequenceEnum.PRICE);
    }


}
