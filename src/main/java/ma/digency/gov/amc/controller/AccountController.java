package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.dto.shared.ResetDoneRequest;
import ma.digency.gov.amc.dto.shared.ResetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RequestMapping("account/")
public interface AccountController {

    @PostMapping(path = "subscription")
    ResponseEntity<Void> createAccount(@RequestBody AccountRequest authRequest);

    @PostMapping(path = "generate-otp")
    ResponseEntity<Void> generateOtp(@RequestBody AccountRequest authRequest);

    @PostMapping(path = "authentificated")
    ResponseEntity<Void> authentificated();

    @GetMapping(path="get-user-data/{emailUser}")
    ResponseEntity<AccountResponse> getUserExposition(@PathVariable("emailUser") @NotBlank String emailUser);

    @GetMapping(path = "verify/{vkey}")
    ResponseEntity<Void> verify(@PathVariable("vkey") @NotBlank String vkey,@RequestParam(required = true) String redirectTo);

    @PostMapping(path = "reset")
    ResponseEntity<Void> resetRequest(@RequestBody ResetRequest resetRequest);

    @PutMapping(path = "reset")
    ResponseEntity<Void> resetChange(@RequestBody ResetDoneRequest resetDoneRequest);

    @GetMapping(path = "reset/{vkey}")
    ResponseEntity<Void> resetRequestCheck(@PathVariable("vkey") @NotBlank String vkey,@RequestParam(required = true) String redirectTo);

    @PutMapping(path = "profile/update")
    ResponseEntity<UpdateProfileResponse> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest);

    @PostMapping(path = "profile/photo/update")
    ResponseEntity<String> uploadPhoto( @RequestPart(name = "photo", required = true) MultipartFile multipartFile);

    @PutMapping(path = "profile/password/update")
    ResponseEntity<Void> updatePassword(@RequestBody ChangePasswordRequest request);

    @PutMapping(path = "admin/{ref}")
    ResponseEntity<Void> updateAccountByAdmin(@RequestBody AccountRequest accountRequest,@PathVariable("ref") @NotBlank String ref);

    @DeleteMapping(path = "admin/{ref}")
    ResponseEntity<Void> deleteAccountByAdmin(@PathVariable("ref") @NotBlank String ref);

    @PutMapping(path = "admin/assign")
    ResponseEntity<Void> AssignRolesByAdmin(@RequestBody AssignRolesRequest assignRolesRequest);

    @GetMapping(path = "admin/{ref}")
    ResponseEntity<AccountAdminResponse> getAccountByAdmin(@PathVariable("ref") @NotBlank String ref);

    @GetMapping(path = "admin")
    ResponseEntity<PageableResponse<AccountAdminResponse>> getAccountsByAdmin(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size);

    @GetMapping(path = "admin/{ref}/roles")
    ResponseEntity<List<String>> getAccountRoles(@PathVariable("ref") @NotBlank String ref);
}
