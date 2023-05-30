package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoResponse {

    private UserInfoDto user;

    private String status;

    private String message;

    @Override
    public String toString() {
        return "UserDtoResponse{" +
                "user=" + user +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
