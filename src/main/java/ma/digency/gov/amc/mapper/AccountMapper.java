package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.dto.card.AccountResponse;
import ma.digency.gov.amc.repository.entity.shared.Account;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface AccountMapper {
    @Maps(withIgnoreFields = {"roles"})
    Account accountRequestDtoToAccount(AccountRequest accountDto);
    AccountResponse accountToAccountResponse(Account account);

    @Maps(withIgnoreFields = {"roles"})
    Account updateAccountRequest(AccountRequest request,Account old);

    Account updateAccountDto(UpdateProfileRequest request, Account find);

    @Maps(withIgnoreFields = {"token"})
    UpdateProfileResponse accountToUpdateProfileDto(Account account);

    AccountAdminResponse accountToAccountAdminDto(Account account);

}
