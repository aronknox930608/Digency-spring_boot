package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.process.AccountProcess;
import ma.digency.gov.amc.process.AccountProcessImpl;
import ma.digency.gov.amc.process.AuthenticationProcess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationProcess authenticationProcess;

    private final AccountProcess accountProcess;

    @Override
    @Deprecated
    public ResponseEntity<LoginResponse> login(AuthenticationLoginRequest authRequest) {
        AuthenticationLoginResponse data = authenticationProcess.login(authRequest);
        if(data.getData()==null)
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BAD_CREDENTIALS);
        LoginResponse out = new LoginResponse();
        String fname=data.getData().getUser().getFirstname()==null?"Not provided":data.getData().getUser().getFirstname();
        String lname=data.getData().getUser().getLastname()==null?"Not provided":data.getData().getUser().getLastname();
        out.setFullname(lname.toUpperCase()+" "+fname);
        out.setImg("https://fastify-jwt-auth-api.herokuapp.com/profile-pics/admin.png");
        out.setRoles(data.getData().getRoles().toArray(new String[0]));
        out.setUser(data.getData().getUser().getEmail());
        out.setToken(data.getData().getToken());
        out.setCin(data.getData().getUser().getCin());
        return ResponseEntity.status(HttpStatus.OK)
                .body(out);
    }

    @Override
    public ResponseEntity<Void> login() {
        throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_UNAUTHENTIFIED);
    }

    @Override
    public ResponseEntity<Void> createUser(AccountRequest userDtoRequest) {
        userDtoRequest.setRoles(new String[]{"USER"});
        accountProcess.createPersonAccount(userDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public ResponseEntity<UserDtoResponse> updateUser(UserUpdateDtoRequest userUpdateDtoRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationProcess.updateUser(userUpdateDtoRequest));
    }

    @Override
    public ResponseEntity<AssignRoleResponse> assignRoleToUser(AssignRoleRequest assignRoleRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationProcess.assignRoleToUser(assignRoleRequest));
    }
}
