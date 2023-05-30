package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataDto {

    private UserInfoDto user;

    private List<String> roles;

    private String token;
}
