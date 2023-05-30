package ma.digency.gov.amc.dto.shared;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DocumentRequest {
    String refDocument;

    @NotEmpty
    String documentType;

    String refObject;

    String refParent;

    @NotNull
    MultipartFile file;
}
