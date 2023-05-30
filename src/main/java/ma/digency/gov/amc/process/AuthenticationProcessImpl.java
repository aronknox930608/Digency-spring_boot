package ma.digency.gov.amc.process;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.mapper.AccountMapper;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.repository.entity.shared.AccountRoleId;
import ma.digency.gov.amc.repository.entity.shared.Role;
import ma.digency.gov.amc.repository.entity.shared.RoleAccount;
import ma.digency.gov.amc.service.AuthenticationService;
import ma.digency.gov.amc.service.shared.RoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationProcessImpl implements AuthenticationProcess {

    private final AuthenticationService authenticationService;

    private final AccountMapper accountMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleService roleService;

    @Value("${mail.login}")
    private String email;

    @Value("${mail.password}")
    private String password;

    @Override
    public AuthenticationLoginResponse login(AuthenticationLoginRequest authRequest) {
        return authenticationService.login(authRequest);
    }

    @Override
    public AccountResponse createUser(AccountRequest userDtoRequest) {
        userDtoRequest.setLogin(userDtoRequest.getEmail());
        userDtoRequest.setPassword(bCryptPasswordEncoder.encode(userDtoRequest.getPassword()));
        Account account = accountMapper.accountRequestDtoToAccount(userDtoRequest);
        Set<RoleAccount> roles = new HashSet<>();
        for(String s : userDtoRequest.getRoles())
        {
            Role tmp = roleService.findRoleByCodeRole(s);
            RoleAccount accountRole= new RoleAccount(new AccountRoleId(tmp.getRefRole(),account.getRefAccount()),tmp);
            if(CollectionUtils.isEmpty(account.getRoles())){
                account.setRoles(Set.of(accountRole));
            }else{
                account.getRoles().add(accountRole);}
        }
        account.setRoles(roles);
        return authenticationService.createUser(account);
    }

    @Override
    public UserDtoResponse updateUser(UserUpdateDtoRequest userUpdateDtoRequest) {
        return null;
    }

    @Override
    public AssignRoleResponse assignRoleToUser(AssignRoleRequest assignRoleRequest) {
        return null;
    }
}
