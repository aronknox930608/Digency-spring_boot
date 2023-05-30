package ma.digency.gov.amc.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Configuration
public class AuthenticationConfiguration {

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();

    }
}
