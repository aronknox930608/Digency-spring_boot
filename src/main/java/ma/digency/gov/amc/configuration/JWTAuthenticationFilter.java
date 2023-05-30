package ma.digency.gov.amc.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.digency.gov.amc.dto.LoginRequest;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.service.AccountService;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final AccountService accountService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,AccountService accountService) {
        super();
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        LoginRequest user = new LoginRequest();
        try {
            user.setPassword(request.getParameter("password"));
            user.setUsername(request.getParameter("username"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("hhddddddddddddddd");
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        /****************************successfulAuthentication*******************************/

        User sprinUser = (User) auth.getPrincipal();
        List<String> roles = new ArrayList<>();
        auth.getAuthorities().forEach(a ->
                roles.add(a.getAuthority())
        );

        String[] rolesString = roles.toArray(new String[roles.size()]);

        String jwtToken = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(sprinUser.getUsername())
                .withArrayClaim("roles", rolesString)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("token",jwtToken);
        Optional<Account> account = this.accountService.findAccountByEmail(sprinUser.getUsername());
        Account accountAuth = account.orElseThrow(()->{
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_ACCOUNT_NOT_FOUND);
        });
        Boolean isError = false;
        if(account.get().getStatus()== AccountEnum.UNVERIFIED)
        {
            data.put("messageTemplate","Votre compte n'est pas encore verifié, consulter votre email");
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            new ObjectMapper().writeValue(response.getOutputStream(),data);
            return;
        }
        data.put("firstname",accountAuth.getFirstname());
        data.put("lastname",accountAuth.getLastname());
        data.put("user",accountAuth.getEmail());
        data.put("roles",rolesString);
        data.put("type",accountAuth.getAccountType());
        data.put("img",accountService.getPhoto(accountAuth));
        data.put("cin",accountAuth.getCin());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),data);

    }

}
