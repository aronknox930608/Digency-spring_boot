package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.PriceRequest;
import ma.digency.gov.amc.dto.attributionsprix.PriceResponse;
import ma.digency.gov.amc.mapper.AttributionsPrixMapper;
import ma.digency.gov.amc.repository.entity.Price;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.*;
import ma.digency.gov.amc.service.attributionsPrix1.*;
import ma.digency.gov.amc.service.attributionsprix.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class PriceProcessImpl implements PriceProcess{

    private final AttributionsPrixMapper attributionsPrixMapper;

    private final DocumentProcess documentProcess;

    @Autowired
    private final PriceService priceService;

    @Autowired
    private final AwardTypeService awardTypeService;

    @Autowired
    private final AwardService awardService;

    @Autowired
    private final AwardCategoriesService awardCategoriesService;

    @Autowired
    private final PricePlanningService pricePlanningService;

    @Autowired
    private final CategoriesPlanningService categoriesPlanningService;

    @Override
    public PriceResponse createPrice(PriceRequest request, MultipartFile picture) throws IOException {
        var price=attributionsPrixMapper.priceRequestToPrice(request);
        price.setPicture(picture.getBytes());
        price=priceService.createOrUpdatePrice(price);
        return attributionsPrixMapper.priceToPriceResponse(price);
    }

    @Override
    public PriceResponse updatePrice(PriceResponse request, MultipartFile picture) throws IOException {
        Price price=priceService.getPriceByRef(request.getRefPrice());
        request.setPicture(picture.getBytes());
        price=priceService.createOrUpdatePrice(attributionsPrixMapper.updatePriceResponseToPrice(request,price));
        return attributionsPrixMapper.priceToPriceResponse(price);
    }

    @Override
    public Void deletePrice(String refPrice) {
        priceService.deletePrice(priceService.getPriceByRef(refPrice));
        return null;
    }

    @Override
    public PriceResponse getPrice(String refPrice) {
        return attributionsPrixMapper.priceToPriceResponse(priceService.getPriceByRef(refPrice));
    }

    @Override
    public AwardsResponse addPrice(AwardTypeRequest request, MultipartFile picture) {
        AwardType awardType=awardTypeService.createOrUpdateAwardType(attributionsPrixMapper.awardRequestToAwardType(request));
        Award award=new Award();
        award.setAwardType(awardType);
        award=awardService.createOrUpdateAward(award);
        documentProcess.saveDocument("picture",picture,awardType.getRefAwardType(), awardType.getRefAwardType());
        return attributionsPrixMapper.awardToAwardsResponse(award);
    }

    @Override
    public List<AwardCategories> addCategory(AwardCategoryRequest request) {
        AwardType awardType=awardTypeService.findAwardTypeByRef(request.getRefAwardType());
        AwardCategories awardCategories=attributionsPrixMapper.awardCategoryRequestToAwardCategories(request);
        awardCategories.setAwardType(awardType);
        awardCategoriesService.createOrUpdateAwardCategory(awardCategories);
        return awardCategoriesService.findAwardCategoriesByRefAwardType(awardType);
    }

    @Override
    public AwardsResponse getAward(String refAward) {
        return attributionsPrixMapper.awardToAwardsResponse(awardService.getAward(refAward));
    }

    @Override
    public AwardsResponse updateAward(AwardsResponse awardsResponse) {
        Award awardFound=awardService.getAward(awardsResponse.getRefAward());
        Award award=attributionsPrixMapper.updateAwardResponseToAward(awardsResponse,awardFound);
        awardTypeService.createOrUpdateAwardType(award.getAwardType());
        award=awardService.createOrUpdateAward(award);
        return attributionsPrixMapper.awardToAwardsResponse(award);
    }

    @Override
    public AwardCategoryResponse getCategory(String refCategory) {
        return attributionsPrixMapper.awardCategoryToAwardCategoryResponse(awardCategoriesService.getCategory(refCategory));
    }

    @Override
    public AwardCategoryResponse updateCategory(AwardCategoryResponse awardCategoryResponse) {
        AwardType awardType=awardTypeService.findAwardTypeByRef(awardCategoryResponse.getAwardType().getRefAwardType());
        AwardCategories awardCategoriesFound=awardCategoriesService.getCategory(awardCategoryResponse.getRefAwardCategory());
        awardCategoriesFound.setAwardType(awardType);
        awardCategoriesFound.setTitleAr(awardCategoryResponse.getTitleAr());
        awardCategoriesFound.setTitleFr(awardCategoryResponse.getTitleFr());
        awardCategoriesFound=awardCategoriesService.createOrUpdateAwardCategory(awardCategoriesFound);
        return attributionsPrixMapper.awardCategoryToAwardCategoryResponse(awardCategoriesFound);
    }

    @Override
    public Void deleteAward(String refAward) {
        Award award=awardService.getAward(refAward);
        AwardType awardType=awardTypeService.findAwardTypeByRef(award.getAwardType().getRefAwardType());
        List<AwardCategories> awardCategories=awardTypeService.getListAwardCategories(awardType);
        for (AwardCategories awardCategory:awardCategories) {
            awardCategoriesService.deleteAwardCategories(awardCategory.getRefAwardCategory());
        }
        awardTypeService.deleteAwardType(awardType.getRefAwardType());
        awardService.deleteAward(refAward);
        return null;
    }

    @Override
    public Void deleteAwardCategory(String refAwardCategory) {
        return awardCategoriesService.deleteAwardCategories(refAwardCategory);
    }

    @Override
    public List<AwardCategoryResponse> getListAwardCategory(String refAwardType) {

        List<AwardCategories> awardCategories=awardTypeService.findAwardTypeByRef(refAwardType).getAwardCategories();
        List<AwardCategoryResponse> awardCategoryResponses=new ArrayList<>();
        for (AwardCategories awardCategory:awardCategories) {
            AwardCategoryResponse awardCategoryResponse=new AwardCategoryResponse();
            awardCategoryResponse.setRefAwardCategory(awardCategory.getRefAwardCategory());
            awardCategoryResponse.setTitleAr(awardCategory.getRefAwardCategory());
            awardCategoryResponse.setTitleFr(awardCategory.getTitleFr());
            awardCategoryResponse.setAwardType(awardCategory.getAwardType());
            awardCategoryResponses.add(awardCategoryResponse);
        }
        return awardCategoryResponses;
    }

    @Override
    public List<Award> getListAward() {
        return awardService.getListAward();
    }

    @Override
    public startAwardResponse startAward(startAwardRequest request) {
        PricePlanning pricePlanning=new PricePlanning();
        pricePlanning.setStartDate(request.getStartDate());
        pricePlanning.setEndDate(request.getEndDate());
        pricePlanning.setAnnouncementDate(request.getAnnouncementDate());
        pricePlanning.setAward(awardService.getAward(request.getAward()));
        pricePlanning=pricePlanningService.createOrUpdatePricePlanning(pricePlanning);

        for (String awardCategory:request.getAwardCategoriesList()) {
            CategoriesPlanning categoriesPlanning=new CategoriesPlanning();
            categoriesPlanning.setRefPricePlanning(pricePlanning.getRefPricePlaning());
            categoriesPlanning.setRefCategories(awardCategory);
            categoriesPlanningService.createOrUpdateCategoriesPlanning(categoriesPlanning);
        }

        startAwardResponse startAwardResponse=new startAwardResponse();
        startAwardResponse.setRefPricePlaning(pricePlanning.getRefPricePlaning());
        startAwardResponse.setStartDate(request.getStartDate());
        startAwardResponse.setEndDate(request.getEndDate());
        startAwardResponse.setAnnouncementDate(request.getAnnouncementDate());
        startAwardResponse.setAward(pricePlanning.getAward().getRefAward());
        startAwardResponse.setAwardType(attributionsPrixMapper.awardTypeToAwardTypeResponse(pricePlanning.getAward().getAwardType()));

        List<AwardCategoriesResponse> awardCategoriesResponses=new ArrayList<>();
        for (String awardCategory:request.getAwardCategoriesList()) {
            AwardCategories awardCategories=awardCategoriesService.getAwardCategory(awardCategory);
            AwardCategoriesResponse awardCategoriesResponse=new AwardCategoriesResponse();
            awardCategoriesResponse.setRefAwardCategory(awardCategories.getRefAwardCategory());
            awardCategoriesResponse.setTitleFr(awardCategories.getTitleFr());
            awardCategoriesResponse.setTitleAr(awardCategories.getTitleAr());
            awardCategoriesResponses.add(awardCategoriesResponse);

        }
        startAwardResponse.setAwardCategoriesList(awardCategoriesResponses);
        return startAwardResponse;

    }

    @Override
    public startAwardResponse getAwardPlanning(String refAwardPlanning) {
        PricePlanning pricePlanning=pricePlanningService.getPricePlanningByRef(refAwardPlanning);

        startAwardResponse startAwardResponse=new startAwardResponse();
        startAwardResponse.setRefPricePlaning(pricePlanning.getRefPricePlaning());
        startAwardResponse.setStartDate(pricePlanning.getStartDate());
        startAwardResponse.setEndDate(pricePlanning.getEndDate());
        startAwardResponse.setAnnouncementDate(pricePlanning.getAnnouncementDate());
        startAwardResponse.setAward(pricePlanning.getAward().getRefAward());

        startAwardResponse.setAwardType(attributionsPrixMapper.awardTypeToAwardTypeResponse(pricePlanning.getAward().getAwardType()));

        List<AwardCategoriesResponse> awardCategoriesResponses=new ArrayList<>();
        List<CategoriesPlanning> categoriesPlannings=categoriesPlanningService.getListCategoriesPlanningByRefPricePlanning(refAwardPlanning);

        for (CategoriesPlanning categoriesPlanning:categoriesPlannings) {
            AwardCategories awardCategories=awardCategoriesService.getAwardCategory(categoriesPlanning.getRefCategories());
            AwardCategoriesResponse awardCategoriesResponse=new AwardCategoriesResponse();
            awardCategoriesResponse.setRefAwardCategory(awardCategories.getRefAwardCategory());
            awardCategoriesResponse.setTitleFr(awardCategories.getTitleFr());
            awardCategoriesResponse.setTitleAr(awardCategories.getTitleAr());
            awardCategoriesResponses.add(awardCategoriesResponse);

        }
        startAwardResponse.setAwardCategoriesList(awardCategoriesResponses);
        return startAwardResponse;
    }

    @Override
    public PricePlanning getPricePlanning(String refAwardPlanning) {
        return pricePlanningService.getPricePlanningByRef(refAwardPlanning);
    }

    @Override
    public startAwardResponse updateAwardPlanning(startAwardResponse startAwardResponse) {

        Boolean found=FALSE;
        Boolean exist=FALSE;
        PricePlanning pricePlanningFound=pricePlanningService.getPricePlanningByRef(startAwardResponse.getRefPricePlaning());
        if(pricePlanningFound.getStartDate()!=startAwardResponse.getStartDate()){
            pricePlanningFound.setStartDate(startAwardResponse.getStartDate());
        }

        if(pricePlanningFound.getEndDate()!=startAwardResponse.getEndDate()){
            pricePlanningFound.setEndDate(startAwardResponse.getEndDate());
        }

        if(pricePlanningFound.getAnnouncementDate()!=startAwardResponse.getAnnouncementDate()){
            pricePlanningFound.setAnnouncementDate(startAwardResponse.getAnnouncementDate());
        }

        List<CategoriesPlanning> categoriesPlannings=categoriesPlanningService.getListCategoriesPlanningByRefPricePlanning(startAwardResponse.getRefPricePlaning());

        if(pricePlanningFound.getAward().getRefAward().equals(startAwardResponse.getAward())){
            for (AwardCategoriesResponse awardCategoryResponse:startAwardResponse.getAwardCategoriesList()) {

                for (CategoriesPlanning categoryPlanning:categoriesPlannings) {
                    if(awardCategoryResponse.getRefAwardCategory().equals(categoryPlanning.getRefCategories())){
                        exist=TRUE;
                        break;
                    }
                }
                if(exist==FALSE){
                    CategoriesPlanning categoriesPlanning=new CategoriesPlanning();
                    categoriesPlanning.setRefPricePlanning(startAwardResponse.getRefPricePlaning());
                    categoriesPlanning.setRefCategories(awardCategoryResponse.getRefAwardCategory());
                    categoriesPlanningService.createOrUpdateCategoriesPlanning(categoriesPlanning);
                }
                exist=FALSE;
            }

           for (CategoriesPlanning categoryPlanning:categoriesPlannings) {
                for (AwardCategoriesResponse awardCategoryResponse:startAwardResponse.getAwardCategoriesList()) {
                    if(categoryPlanning.getRefCategories().equals(awardCategoryResponse.getRefAwardCategory())){
                        found=TRUE;
                    }
                }
                if(found==FALSE){
                  categoriesPlanningService.deleteCategoriesPlanning(categoryPlanning.getRefCategoriesPlanning());
                }
                found=FALSE;
            }

        }
        else{
           pricePlanningFound.setAward(awardService.getAward(startAwardResponse.getAward()));

           //supprimer les anciens categories
            for (CategoriesPlanning categoryPlanning:categoriesPlannings){
                categoriesPlanningService.deleteCategoriesPlanning(categoryPlanning.getRefCategoriesPlanning());
            }
            for (AwardCategoriesResponse awardCategoryResponse:startAwardResponse.getAwardCategoriesList()) {
                        CategoriesPlanning categoriesPlanning=new CategoriesPlanning();
                        categoriesPlanning.setRefPricePlanning(startAwardResponse.getRefPricePlaning());
                        categoriesPlanning.setRefCategories(awardCategoryResponse.getRefAwardCategory());
                        categoriesPlanningService.createOrUpdateCategoriesPlanning(categoriesPlanning);
                    }

            }

        pricePlanningFound=pricePlanningService.createOrUpdatePricePlanning(pricePlanningFound);

        return this.getAwardPlanning(pricePlanningFound.getRefPricePlaning());
    }

    @Override
    public Void deleteAwardPlanning(String refAwardPlanning) {
        List<CategoriesPlanning> categoriesPlannings=categoriesPlanningService.getListCategoriesPlanningByRefPricePlanning(refAwardPlanning);
        for (CategoriesPlanning categoryPlanning:categoriesPlannings) {
            categoriesPlanningService.deleteCategoriesPlanning(categoryPlanning.getRefCategoriesPlanning());
        }
        pricePlanningService.deletePricePlanning(pricePlanningService.getPricePlanningByRef(refAwardPlanning));
        return null;
    }
}

