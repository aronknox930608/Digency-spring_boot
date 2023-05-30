package ma.digency.gov.amc.dto.attributionsprix;

import lombok.*;
import ma.digency.gov.amc.repository.entity.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDemandResponse {

    private String refDocument;

    private String type;

    private String name;

    private String nature;

    private byte[] data;

}
