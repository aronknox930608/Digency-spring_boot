package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.AccountRequest;
import ma.digency.gov.amc.dto.UserDtoRequest;
import ma.digency.gov.amc.utils.enumeration.AccountType;

import java.util.Date;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public abstract class AuthentificationMapper {
    public final UserDtoRequest thirdPartyRegister(AccountRequest accountRequest)
    {
        UserDtoRequest out = new UserDtoRequest();
        out.set_minor(false);
        out.set_resident(true);
        out.setBirthday(new Date());
        out.setEmail(accountRequest.getEmail());
        out.setFirstName(accountRequest.getFirstname());
        out.setLastName(accountRequest.getLastname());
        out.setCompany_name("");
        out.setParent_id_card("");
        out.setPhone(accountRequest.getPhoneNumber());
        out.setPassword(accountRequest.getPassword());
        out.setType(accountRequest.getAccountType().getLabel());
        if(accountRequest.getAccountType().equals(AccountType.PERSON))
        {
            out.setId_card(accountRequest.getCin());
            out.setTax_id("");
        }
        else
        {
            out.setTax_id(accountRequest.getCin());
            out.setId_card("");
        }
        return out;
    }
}
