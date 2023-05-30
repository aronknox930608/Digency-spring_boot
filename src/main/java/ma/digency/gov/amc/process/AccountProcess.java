package ma.digency.gov.amc.process;

import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.dto.shared.ResetDoneRequest;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountProcess {

    void createPersonAccount(AccountRequest accountDto);

    void generateOTPCode(AccountRequest authRequest);

    String getInformation(String user);

    void updateAccountStatusByVkey(String vkey, AccountEnum status);

    void sendResetRequest(String email,String redirectTo);

    void resetPassword(ResetDoneRequest resetDoneRequest);

    boolean checkRequest(String vkey);

    UpdateProfileResponse updateProfile(UpdateProfileRequest request);

    String uploadProfilePhoto(MultipartFile photo);

    void updatePassword(ChangePasswordRequest request);

    void updateAccountStatus(String refAccount,AccountEnum status);

    void blockAccount(String refAccount);

    void updateAccount(AccountRequest request,String refAccount);

    void assignAccountToRoles(AssignRolesRequest request);

    PageableResponse<AccountAdminResponse> findAllAccountPageable(Pageable pageable);

    AccountAdminResponse findAccountByRef(String refAccount);

    List<String> getRolesByUser(String refAccount);

}
