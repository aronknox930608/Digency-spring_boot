package ma.digency.gov.amc.service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.AccountRepository;
import ma.digency.gov.amc.repository.entity.shared.Account;
import ma.digency.gov.amc.utils.enumeration.AccountEnum;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findPersonByEmail(username);
        if(account.isEmpty())
            throw new UsernameNotFoundException("Account not found");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.get().getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getRole().getCodeRole()));
        });
        return new User(account.get().getEmail(),account.get().getPassword(),authorities);
    }
}
