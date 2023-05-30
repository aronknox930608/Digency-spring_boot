package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.*;
import ma.digency.gov.amc.dto.AccountRequest;
import ma.digency.gov.amc.dto.UserDtoRequest;
import ma.digency.gov.amc.dto.shared.*;
import ma.digency.gov.amc.repository.entity.shared.*;
import ma.digency.gov.amc.utils.enumeration.AccountType;

import java.util.Date;
import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true,
        withCustomFields = {
                @Field(value = {"startedTime", "endTime"}, withCustom = CustomDateMapper.class)
        })
public abstract class CommissionMapper {

    public abstract CommissionResponse commissionToCommissionResponse(Commission commission);


    public abstract List<PlanningResponse> planningsToPlanningsResponse(List<Planning> planning);

    public abstract Commission commissionRequestToCommission(CommissionRequest request);

    public abstract PlanningResponse planningToPlanningResponse(Planning planning);

    public abstract Planning planningResponseToPlanning(PlanningResponse planning);

    public abstract Planning planningRequestToPlanning(PlanningRequest request);


    public abstract Planning updatePlanning(PlanningResponse request, Planning planning);

    @Maps(withIgnoreFields = "commissionPlanning")
    public abstract Commission updateCommission(CommissionResponse update, Commission commission);


    public final CommissionAccount commissionMemberToCommissionAccount(CommissionMemberRequest response) {
        CommissionAccount commissionMember = new CommissionAccount();
        if (response != null) {
            Account account = new Account();
            account.setLogin(response.getLogin());
            account.setEmail(response.getEmail());
            account.setPhoneNumber(response.getPhoneNumber());

            commissionMember.setAccount(account);
            commissionMember.setCin(response.getCin());
            commissionMember.setFirstName(response.getFirstName());
            commissionMember.setGender(response.getGender());
            commissionMember.setLastName(response.getLastName());
        }
        return commissionMember;
    }

    public final CommissionMemberResponse commissionMemberToCommissionMemberResponse(CommissionMember member) {
        CommissionMemberResponse response = new CommissionMemberResponse();
        if (member != null) {
            response.setRefCommissionMember(member.getCommissionAccount().getRefCommissionAccount());
            response.setCin(member.getCommissionAccount().getCin());
            response.setFirstName(member.getCommissionAccount().getFirstName());
            response.setLastName(member.getCommissionAccount().getLastName());
            response.setGender(member.getCommissionAccount().getGender());
        }

        return response;
    }

    public final UserDtoRequest userDtoRequestToAccountRequest(AccountRequest accountRequest)
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
