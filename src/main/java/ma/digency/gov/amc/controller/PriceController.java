package ma.digency.gov.amc.controller;


import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.PriceRequest;
import ma.digency.gov.amc.dto.attributionsprix.PriceResponse;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.PricePlanning;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@Validated
@RequestMapping("price/")
public interface PriceController {

    @PostMapping("create-price/")
    @ResponseBody
    PriceResponse createPrice(@RequestPart("json") PriceRequest request, @RequestPart("picture") MultipartFile picture) throws IOException;

    @PutMapping("update-price")
    @ResponseBody
    PriceResponse updatePrice(@RequestPart("json") PriceResponse request, @RequestPart("picture") MultipartFile picture) throws IOException;

    @DeleteMapping("delete-price")
    ResponseEntity<Void> deletePrice(@RequestParam("refPrice") @NotBlank String refPrice);

    @PostMapping("add-price")
    AwardsResponse addAward(@RequestPart("json") AwardTypeRequest request, @RequestPart("picture") MultipartFile picture) throws IOException;

    @PostMapping("add-category/{refAwardType}")
    List<AwardCategories> addCategory(@RequestBody AwardCategoryRequest request);

    @GetMapping("/{refAward}/getAward")
    ResponseEntity<AwardsResponse> getAward(@PathVariable("refAward") @NotBlank String refAward);

    @PutMapping("{refAward}/update-award")
    AwardsResponse updateAward(@RequestBody AwardsResponse awardsResponse);

    @GetMapping("/{refCategory}")
    ResponseEntity<AwardCategoryResponse> getCategory(@PathVariable("refCategory")  @NotBlank String refCategory);

    @PutMapping("{refCategory}/update-category")
    AwardCategoryResponse updateCategory(@RequestBody AwardCategoryResponse awardCategoryResponse);

    @DeleteMapping("delete-award")
    ResponseEntity<Void> deleteAward(@RequestParam("refAward") @NotBlank String refAward);

    @DeleteMapping("delete-award-Category")
    ResponseEntity<Void> deleteAwardCategory(@RequestParam("refAwardCategory") @NotBlank String refAwardCategory);

    @GetMapping("{refAwardType}/list-category")
    ResponseEntity<List<AwardCategoryResponse>> getListAwardCategory(@PathVariable("refAwardType")  @NotBlank String refAwardType);

    @PostMapping("start-award")
    startAwardResponse startAward(@RequestBody startAwardRequest request);

    @GetMapping("list-Award")
    ResponseEntity<List<Award>> getListAward();

    @GetMapping("award-Planning")
    ResponseEntity<startAwardResponse> getAwardPlanning(@RequestParam("refAwardPlanning") String refAwardPlanning);

    @GetMapping("price-Planning")
    ResponseEntity<PricePlanning> getPricePlanning(@RequestParam("refAwardPlanning") String refAwardPlanning);

    @PutMapping("award-Planning/update")
    startAwardResponse updateAwardPlanning(@RequestBody startAwardResponse startAwardResponse);

    @DeleteMapping("delete-award-planning")
    ResponseEntity<Void> deleteAwardPlanning(@RequestParam("refAwardPlanning") @NotBlank String refAwardPlanning);

}
