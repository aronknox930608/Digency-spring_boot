package ma.digency.gov.amc.process;


import ma.digency.gov.amc.dto.attributionsPrix1.*;
import ma.digency.gov.amc.dto.attributionsprix.PriceRequest;
import ma.digency.gov.amc.dto.attributionsprix.PriceResponse;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.Award;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.AwardCategories;
import ma.digency.gov.amc.repository.entity.attributionsPrix1.PricePlanning;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PriceProcess {

    PriceResponse createPrice(PriceRequest request, MultipartFile picture) throws IOException;

    PriceResponse updatePrice(PriceResponse request, MultipartFile picture) throws IOException;

    Void deletePrice(String refPrice);

    PriceResponse getPrice(String refPrice);

    AwardsResponse addPrice(AwardTypeRequest request, MultipartFile picture);

    List<AwardCategories> addCategory(AwardCategoryRequest request);

    AwardsResponse getAward(String refAward);

    AwardsResponse updateAward(AwardsResponse awardsResponse);

    AwardCategoryResponse getCategory(String refCategory);

    AwardCategoryResponse updateCategory(AwardCategoryResponse awardCategoryResponse);

    Void deleteAward(String refAward);

    Void deleteAwardCategory(String refAwardCategory);

    List<AwardCategoryResponse> getListAwardCategory(String refAwardType);

    List<Award> getListAward();

    startAwardResponse startAward(startAwardRequest request);

    startAwardResponse getAwardPlanning(String refAwardPlanning);

    PricePlanning getPricePlanning(String refAwardPlanning);

    startAwardResponse updateAwardPlanning(startAwardResponse startAwardResponse);

    Void deleteAwardPlanning(String refAwardPlanning);
}
