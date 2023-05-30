package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.dto.shared.ResetDoneRequest;
import ma.digency.gov.amc.dto.shared.ResetRequest;
import ma.digency.gov.amc.process.AccountProcess;
import ma.digency.gov.amc.utils.SearchUtils;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import org.apache.catalina.util.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

@RestController
@Tag(name = "Account")
public class AccountControllerImpl implements AccountController {

    @Autowired
    AccountProcess accountProcess;

    @Override
    public ResponseEntity<Void> createAccount(AccountRequest authRequest) {

        accountProcess.createPersonAccount(authRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> generateOtp(AccountRequest authRequest) {
        accountProcess.generateOTPCode(authRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> authentificated() {
        return ResponseEntity.status(HttpStatus.OK).build();//TODO
    }

    @Override
    public ResponseEntity<AccountResponse> getUserExposition(String user) {
        AccountResponse re = new AccountResponse();
        re.setLogin(accountProcess.getInformation(user));

        return ResponseEntity.status(HttpStatus.OK).body(re);
    }

    @Override
    public ResponseEntity<Void> verify(String vkey,String redirectTo) {
        String url = null;
        try {
            url = URLDecoder.decode(redirectTo,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try{
            this.accountProcess.updateAccountStatusByVkey(vkey,AccountEnum.VERIFIED);
            return ResponseEntity.status(HttpStatus.FOUND).location(
                    URI.create(url)
            ).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.FOUND).location(
                    URI.create(url+"/error")
            ).build();
        }
    }

    @Override
    public ResponseEntity<Void> resetRequest(ResetRequest resetReques) {
        this.accountProcess.sendResetRequest(resetReques.getEmail(), resetReques.getRedirectTo());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> resetChange(ResetDoneRequest resetDoneRequest) {
        this.accountProcess.resetPassword(resetDoneRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> resetRequestCheck(String vkey, String redirectTo) {
        String url = null;
        try {
            url = URLDecoder.decode(redirectTo,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(this.accountProcess.checkRequest(vkey))
            return ResponseEntity.status(HttpStatus.FOUND).location(
                    URI.create(url+"/"+vkey)
            ).build();
        return ResponseEntity.status(HttpStatus.FOUND).location(
                URI.create(url+"/error/expired")
        ).build();
    }

    @Override
    public ResponseEntity<UpdateProfileResponse> updateProfile(UpdateProfileRequest updateProfileRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                accountProcess.updateProfile(updateProfileRequest)
        );
    }

    @Override
    public ResponseEntity<String> uploadPhoto(MultipartFile multipartFile) {
        return ResponseEntity.status(HttpStatus.OK).body(
                accountProcess.uploadProfilePhoto(multipartFile)
        );
    }

    @Override
    public ResponseEntity<Void> updatePassword(ChangePasswordRequest request) {
        accountProcess.updatePassword(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> updateAccountByAdmin(AccountRequest accountRequest, String ref) {
        accountProcess.updateAccount(accountRequest,ref);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deleteAccountByAdmin(String ref) {
        accountProcess.blockAccount(ref);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> AssignRolesByAdmin(AssignRolesRequest assignRolesRequest) {
        accountProcess.assignAccountToRoles(assignRolesRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<AccountAdminResponse> getAccountByAdmin(String ref) {
        return ResponseEntity.status(HttpStatus.OK).body(accountProcess.findAccountByRef(ref));
    }

    @Override
    public ResponseEntity<PageableResponse<AccountAdminResponse>> getAccountsByAdmin(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(accountProcess.findAllAccountPageable(
                SearchUtils.createPageable(page,size)
        ));
    }

    @Override
    public ResponseEntity<List<String>> getAccountRoles(String ref) {
        return ResponseEntity.status(HttpStatus.OK).body(
                accountProcess.getRolesByUser(ref)
        );
    }


}
