package ma.digency.gov.amc.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        /****************************doFilterInternal*******************************/
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
            if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX) || request.getRequestURI().contains("/authentication")) {
                filterChain.doFilter(request, response);
                return;
            }
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET)).build();
                String jwt = jwtToken.substring(SecurityConstants.TOKEN_PREFIX.length());
                DecodedJWT decodedJWT = verifier.verify(jwt);
                String username = decodedJWT.getSubject();
                List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                roles.forEach(rn -> authorities.add(new SimpleGrantedAuthority(rn)));
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(user);
            }
            catch(Exception e)
            {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String,String> error = new HashMap<String,String>();
                error.put("messageTemplate","Votre session est expiré, essaye de se connecter à nouveau");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
            filterChain.doFilter(request, response);
        }
    }

}
