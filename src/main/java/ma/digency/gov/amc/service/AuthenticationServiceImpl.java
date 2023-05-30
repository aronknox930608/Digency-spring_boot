package ma.digency.gov.amc.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.configuration.SecurityConstants;
import ma.digency.gov.amc.dto.*;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.mapper.AccountMapper;
import ma.digency.gov.amc.repository.AccountRepository;
import ma.digency.gov.amc.repository.RoleRepository;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.repository.entity.shared.AccountRoleId;
import ma.digency.gov.amc.repository.entity.shared.Role;
import ma.digency.gov.amc.repository.entity.shared.RoleAccount;
import ma.digency.gov.amc.utils.AuthenticationThirdParty;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletContext;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;

    private final AuthenticationThirdParty authenticationThirdParty;

    private final ReferenceSequenceService referenceSequenceService;

    private final RoleRepository roleRepository;

    /*@Override*/
    public UserDtoResponse createUser(UserDtoRequest userDtoRequest, String token) {
        return authenticationThirdParty.createUser(userDtoRequest,token);
    }


    @Override
    public AuthenticationLoginResponse login(AuthenticationLoginRequest authRequest) {
        AuthenticationLoginResponse data = authenticationThirdParty.login(authRequest);
        DataDto dataDto = data.getData();
        Date exp = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        if(dataDto==null)
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BAD_CREDENTIALS);
        try
        {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET)).build();
            String jwt = dataDto.getToken().substring(SecurityConstants.TOKEN_PREFIX.length());
            DecodedJWT decodedJWT = verifier.verify(jwt);
            exp=decodedJWT.getExpiresAt();
        }catch (Exception e)
        {

        }

        String jwtToken = JWT.create()
                .withIssuer("/authentification/login")
                .withSubject(dataDto.getUser().getEmail())
                .withArrayClaim("roles", dataDto.getRoles().toArray(new String[0]))
                .withExpiresAt(exp)
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));
        dataDto.setToken(jwtToken);
        data.setData(dataDto);
        return data;
    }

    @Override
    public AccountResponse createUser(Account account)  {
        account.setRefAccount(referenceSequenceService.generateRefAccount());
        accountRepository.save(account);
        AccountResponse out = new AccountResponse();
        out.setLogin(account.getLogin());
        return out;
    }

    @Override
    public UserDtoResponse updateUser(UserUpdateDtoRequest userUpdateDtoRequest) {
        return null;
    }

    @Override
    public AssignRoleResponse assignRoleToUser(AssignRoleRequest assignRoleRequest) {
        Account account = accountRepository.findById(assignRoleRequest.getId()).orElseThrow(()->{
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        });
        List<RoleAccount> roles = new ArrayList<RoleAccount>();
        for(String s : assignRoleRequest.getRoles())
        {
            Role tmp = roleRepository.findRoleByCodeRole(s).orElseThrow(()->{
                throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ROLE_NOT_FOUND);
            });
            RoleAccount accountRole= new RoleAccount(new AccountRoleId(tmp.getRefRole(),account.getRefAccount()),tmp);
            if(CollectionUtils.isEmpty(account.getRoles())){
                account.setRoles(Set.of(accountRole));
            }else{
                account.getRoles().add(accountRole);}
        }
        accountRepository.save(account);
        return new AssignRoleResponse("success","Asigner avec succès");
    }
}
