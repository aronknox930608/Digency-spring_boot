package ma.digency.gov.amc.dto.attributionsprix;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Validated

public class DocumentAwardHassan2Request {

   private String uploadType;

   private String refObject;

   private String refParent;

   private MultipartFile multipartFile;

}
