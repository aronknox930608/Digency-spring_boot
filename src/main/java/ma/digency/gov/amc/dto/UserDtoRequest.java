package ma.digency.gov.amc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class UserDtoRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String id_card;

    private String phone;

    private String type;

    private String company_name;

    private String tax_id;

    private boolean is_minor;

    private boolean is_resident;

    private String parent_id_card;

    private Date birthday;

}
