package by.pvt.shaurma.core.service.spring;

import by.pvt.shaurma.api.contract.ClientApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDetailService implements UserDetailsService {
    private final ClientApi clientApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientApi.loadUserByUserName(username);
    }
}
