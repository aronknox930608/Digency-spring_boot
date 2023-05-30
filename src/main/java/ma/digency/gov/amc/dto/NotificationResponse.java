package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationResponse {

    private List<String> content;
}
