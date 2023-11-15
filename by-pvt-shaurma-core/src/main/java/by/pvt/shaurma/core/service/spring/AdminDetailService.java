package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.AdminApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {
    private final AdminApi adminApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return adminApi.loadUserByUserName(username);
    }
}
