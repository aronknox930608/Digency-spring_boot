package ma.digency.gov.amc.utils;

import com.fasterxml.jackson.databind.SerializationFeature;
import ma.digency.gov.amc.dto.AuthenticationLoginRequest;
import ma.digency.gov.amc.dto.AuthenticationLoginResponse;
import ma.digency.gov.amc.dto.UserDtoRequest;
import ma.digency.gov.amc.dto.UserDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AuthenticationThirdParty {

    @Value("${authentication.host-url}")
    private String hostUrl;

    @Value("${authentication.base-path}")
    private String basePath;

    @Value("${authentication.login-path}")
    private String loginPath;

    @Value("${authentication.user-path}")
    private String userPath;

    @Value("${authentication.role-path}")
    private String rolePath;

    @Autowired
    private RestTemplate restTemplate;

    public AuthenticationLoginResponse login(AuthenticationLoginRequest authenticationLoginRequest) {

        UriComponentsBuilder authenticationURI = UriComponentsBuilder
                .fromHttpUrl(hostUrl)
                .path(basePath)
                .path(loginPath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthenticationLoginRequest> request = new HttpEntity(authenticationLoginRequest, headers);

        return restTemplate.postForObject(authenticationURI.toUriString(), request, AuthenticationLoginResponse.class);


    }

    public UserDtoResponse createUser(UserDtoRequest userDtoRequest, String token) {

        UriComponentsBuilder userURI = UriComponentsBuilder
                .fromHttpUrl(hostUrl)
                .path(basePath)
                .path(userPath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<AuthenticationLoginRequest> request = new HttpEntity(userURI, headers);

        return restTemplate.postForObject(userURI.toUriString(), request, UserDtoResponse.class);


    }

    public UserDtoResponse createUser(UserDtoRequest userDtoRequest) {

        UriComponentsBuilder userURI = UriComponentsBuilder
                .fromHttpUrl(hostUrl)
                .path(basePath)
                .path(userPath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthenticationLoginRequest> request = new HttpEntity(userURI, headers);
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        return restTemplate.postForObject(userURI.toUriString(), request, UserDtoResponse.class);


    }
}
