package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.PriceRequest;
import ma.digency.gov.amc.dto.attributionsprix.PriceResponse;
import ma.digency.gov.amc.process.PriceProcess;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.PricePlanning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "price/")
public class PriceControllerImpl implements PriceController{

    @Autowired
    private PriceProcess priceProcess;

    @Override
    public PriceResponse createPrice(PriceRequest request, MultipartFile picture) throws IOException {
        return priceProcess.createPrice(request,picture);
    }

    @Override
    public PriceResponse updatePrice(PriceResponse request, MultipartFile picture) throws IOException {
        return priceProcess.updatePrice(request,picture);
    }

    @Override
    public ResponseEntity<Void> deletePrice(String refPrice) {
        priceProcess.deletePrice(refPrice);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

   /* @Override
    public ResponseEntity<PriceResponse> getPrice(String refPrice) {
        return ResponseEntity.status(HttpStatus.OK).body(priceProcess.getPrice(refPrice));
    }*/

    //
    @Override
    public AwardsResponse addAward(AwardTypeRequest request, MultipartFile picture) throws IOException {
        return priceProcess.addPrice(request,picture);
    }

    @Override
    public List<AwardCategories> addCategory(AwardCategoryRequest request) {
        return priceProcess.addCategory(request);
    }

    @Override
    public ResponseEntity<AwardsResponse> getAward(String refAward) {
        return ResponseEntity.status(HttpStatus.OK).body(priceProcess.getAward(refAward));
    }

    @Override
    public AwardsResponse updateAward(AwardsResponse awardsResponse) {
        return priceProcess.updateAward(awardsResponse);
    }

    @Override
    public ResponseEntity<AwardCategoryResponse> getCategory(String refCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(priceProcess.getCategory(refCategory));
    }

    @Override
    public AwardCategoryResponse updateCategory(AwardCategoryResponse awardCategoryResponse) {
        return priceProcess.updateCategory(awardCategoryResponse);
    }

    @Override
    public ResponseEntity<Void> deleteAward(String refAward) {
         priceProcess.deleteAward(refAward);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deleteAwardCategory(String refAwardCategory) {
        priceProcess.deleteAwardCategory(refAwardCategory);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<AwardCategoryResponse>> getListAwardCategory(String refAwardType) {
        return ResponseEntity.status(HttpStatus.OK).body(priceProcess.getListAwardCategory(refAwardType));
    }

    @Override
    public startAwardResponse startAward(startAwardRequest request) {
        return priceProcess.startAward(request);
    }

    @Override
    public ResponseEntity<List<Award>> getListAward() {
        return ResponseEntity.status(HttpStatus.OK).body(priceProcess.getListAward());
    }

    @Override
    public ResponseEntity<startAwardResponse> getAwardPlanning(String refAwardPlanning) {
        return ResponseEntity.status(HttpStatus.OK).body(priceProcess.getAwardPlanning(refAwardPlanning));
    }

    @Override
    public ResponseEntity<PricePlanning> getPricePlanning(String refAwardPlanning) {
        return ResponseEntity.status(HttpStatus.OK).body(priceProcess.getPricePlanning(refAwardPlanning));
    }

    @Override
    public startAwardResponse updateAwardPlanning(startAwardResponse startAwardResponse) {
        return priceProcess.updateAwardPlanning(startAwardResponse);
    }

    @Override
    public ResponseEntity<Void> deleteAwardPlanning(String refAwardPlanning) {
        priceProcess.deleteAwardPlanning(refAwardPlanning);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
